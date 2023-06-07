package com.example.genisys.controller;

import com.example.genisys.dto.ProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("create-product")
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductDto product){
        return  new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("update-product")
    public ResponseEntity<ResponseDto> updateProductById(@PathVariable int id, @Valid @RequestBody ProductDto productRequest){
        return new ResponseEntity<>(productService.updateProductById(id, productRequest), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDto> deleteProductById(int id){
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }
}
