package com.example.genisys.service;

import com.example.genisys.dto.ProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.entity.Product;
import com.example.genisys.enums.ResponseCodes;
import com.example.genisys.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ResponseDto createProduct(ProductDto productRequest){

        Optional<Product> existingProduct =  productRepository.findByProductName(productRequest.getProductName());
        if(existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product already exists");
        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductColor(productRequest.getProductColor());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductImage(productRequest.getProductImage());
        product.setProductSize(productRequest.getProductSize());
        product.setGenderCategory(productRequest.getGenderCategory());
        product.setUniqueCategory(productRequest.getUniqueCategory());
        product.setProductQuantity(productRequest.getProductQuantity());
        productRepository.save(product);

        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product successfully created");
    }

    public ResponseDto updateProductById(int id, ProductDto productRequest){
        Optional<Product> existingProduct = productRepository.findById(id);
        if(!existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
        }

        Product dbProduct = existingProduct.get();

        dbProduct.setProductName(productRequest.getProductName());
        dbProduct.setProductPrice(productRequest.getProductPrice());
        dbProduct.setProductColor(productRequest.getProductColor());
        dbProduct.setProductSize(productRequest.getProductSize());
        dbProduct.setProductImage(productRequest.getProductImage());
        dbProduct.setGenderCategory(productRequest.getGenderCategory());
        dbProduct.setUniqueCategory(productRequest.getUniqueCategory());
        dbProduct.setProductQuantity(productRequest.getProductQuantity());
        productRepository.save(dbProduct);

        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product successfully updated");
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> returnedProducts = new ArrayList<>();

        for(Product product: products){
            returnedProducts.add(createProductRequest(product));
        }
        return returnedProducts;
    }

    public ProductDto getProductById(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return createProductRequest(product.get());
        }

        return new ProductDto();
    }

    public ResponseDto deleteProductById(int id){
        Optional<Product> existingProduct = productRepository.findById(id);

        if(!existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
        }

        productRepository.deleteById(id);
        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product has been deleted");
    }

    private ProductDto createProductRequest(Product product){
        ProductDto productRequest = new ProductDto();
        productRequest.setId(product.getId());
        productRequest.setProductName(product.getProductName());
        productRequest.setProductPrice(product.getProductPrice());
        productRequest.setProductColor(product.getProductColor());
        productRequest.setProductSize(product.getProductSize());
        productRequest.setProductImage(product.getProductImage());
        productRequest.setGenderCategory(product.getGenderCategory());
        productRequest.setUniqueCategory(product.getUniqueCategory());
        productRequest.setProductQuantity(product.getProductQuantity());
        return productRequest;
    }

}
