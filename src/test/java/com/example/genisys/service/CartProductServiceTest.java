package com.example.genisys.service;


import com.example.genisys.dto.CartProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.entity.CartProduct;
import com.example.genisys.repository.CartProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
public class CartProductServiceTest {
    private  CartProductService cartProductService;

    @Mock
    private CartProductRepository cartProductRepository;

    @BeforeEach
    public  void setUp(){
        cartProductService = new CartProductService(cartProductRepository);
    }

    @Test
    public void testCreateCartProduct_ShouldReturnSuccess_WhenGivenTheRightArguments(){
        CartProductDto product = new CartProductDto();

        Set<Integer> width = new HashSet<>();
        width.add(12);

        Set<String> colors = new HashSet<>();
        colors.add("Blue");

        Set<String> sizes = new HashSet<>();
        sizes.add("12");

        Set<String> images = new HashSet<>();
        images.add("images1");

        product.setWidth(width);
        product.setPrice(250);
        product.setColors(colors);
        product.setSizes(sizes);
        product.setImages(images);

        CartProductDto[] products = new CartProductDto[1];
        products[0] = product;

        ResponseDto response = cartProductService.createCartProducts(products);
        cartProductService.getAllProducts();
        assertEquals(0, response.getCode());
        assertEquals("Products successfully created", response.getDescription());

    }

    @Test
    public void testConvertEntityToDto_ShouldReturnInstanceofDto(){
        CartProduct product = new CartProduct();

        Set<Integer> width = new HashSet<>();
        width.add(12);

        Set<String> colors = new HashSet<>();
        colors.add("Blue");

        Set<String> sizes = new HashSet<>();
        sizes.add("12");

        Set<String> images = new HashSet<>();
        images.add("images1");

        product.setPrice(500);
        product.setImages(images);
        product.setSizes(sizes);
        product.setColors(colors);
        product.setWidth(width);

        assertInstanceOf(CartProductDto.class, cartProductService.convertEntityToDto(product));
    }
}
