package com.example.genisys.service;

import com.example.genisys.dto.CartProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.entity.CartProduct;
import com.example.genisys.enums.ResponseCodes;
import com.example.genisys.repository.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private  final CartProductRepository cartProductRepository;
    //private final ProductService productService;

    public ResponseDto createCartProducts(CartProductDto[] cartProductsRequest){

        for(CartProductDto product: cartProductsRequest){
            CartProduct cartProduct = new CartProduct();

            cartProduct.setPrice(product.getPrice());
            cartProduct.setColors(product.getColors());
            cartProduct.setSizes(product.getSizes());
            cartProduct.setWidth(product.getWidth());
            cartProduct.setImages(product.getImages());
            cartProductRepository.save(cartProduct);
        }

        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Products successfully created");
    }

    public List<CartProductDto> getAllProducts(){
        List<CartProductDto> returnedProducts = new ArrayList<>();

        List<CartProduct> products = cartProductRepository.findAll();
        for(CartProduct product: products){
            returnedProducts.add(convertEntityToDto(product));
        }
        return returnedProducts;
    }

    private CartProductDto convertEntityToDto(CartProduct product){
        CartProductDto cartProduct = new CartProductDto();

//        Set<ColorDto> colors = product.getColors().stream()
//                .map(color -> {
//                    ColorDto colorDto = new ColorDto();
//                    colorDto.setColor(color.getColor());
//                    return colorDto;
//                }).collect(Collectors.toSet());
//
//        Set<SizeDto> sizes = product.getSizes().stream()
//                .map(size -> {
//                    SizeDto sizeDto = new SizeDto();
//                    sizeDto.setSize(size.getSize());
//                    return sizeDto;
//                }).collect(Collectors.toSet());

        cartProduct.setPrice(product.getPrice());
        cartProduct.setColors(product.getColors());
        cartProduct.setSizes(product.getSizes());
        cartProduct.setImages(product.getImages());
        cartProduct.setWidth(product.getWidth());

        return cartProduct;
    }
}
