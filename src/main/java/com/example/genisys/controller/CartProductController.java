package com.example.genisys.controller;

import com.example.genisys.dto.CartProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart-products")
public class CartProductController {
    private final CartProductService cartProductService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createCartProducts(@RequestBody CartProductDto[] cartProducts){
        return new ResponseEntity<>(cartProductService.createCartProducts(cartProducts), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<CartProductDto>> getAllProducts(){
        return new ResponseEntity<>(cartProductService.getAllProducts(), HttpStatus.OK);
    }
}
