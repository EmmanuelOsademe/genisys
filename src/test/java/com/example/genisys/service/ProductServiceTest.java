package com.example.genisys.service;

import com.example.genisys.dto.BrandDto;
import com.example.genisys.dto.ColorDto;
import com.example.genisys.dto.ProductDto;
import com.example.genisys.dto.ResponseDto;
import com.example.genisys.entity.Brand;
import com.example.genisys.entity.Color;
import com.example.genisys.entity.Product;
import com.example.genisys.entity.Size;
import com.example.genisys.enums.GenderCategory;
import com.example.genisys.repository.BrandRepository;
import com.example.genisys.repository.ColorRepository;
import com.example.genisys.repository.ProductRepository;
import com.example.genisys.repository.SizeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private BrandRepository brandRepository;

    @BeforeEach
    public void setUp(){
        productService = new ProductService(productRepository, sizeRepository, colorRepository, brandRepository);
    }

    @Test
    public void testCreateProduct_ShouldReturnSuccess_WhenAllConditionsAreMet(){
        ProductDto product = new ProductDto();

        Set<String> sizes = new HashSet<>();
        sizes.add("12");
        sizes.add("13");

        Set<ColorDto> colors = new HashSet<>();
        ColorDto color = new ColorDto();
        color.setColor("blue");
        color.setImage("image1");

        BrandDto brand = new BrandDto();
        brand.setName("Nike");

        product.setProductName("Best Shoe");
        product.setProductPrice(100);
        product.setDiscountedPrice(90);
        product.setProductSizes(sizes);
        product.setProductColors(colors);
        product.setGenderCategory(GenderCategory.FEMALE);
        product.setProductBrand(brand);
        product.setProductQuantity(4);

        ProductDto[] products = new ProductDto[1];
        products[0] = product;

        ResponseDto response = productService.createProduct(products);
        productService.getAllProducts();
        assertEquals(0, response.getCode());
        assertEquals("Products successfully created", response.getDescription());
    }

    @Test
    public void testDeleteProduct_ShouldReturnSuccess_WhenProductExists(){
        Long id = 1L;
        Optional<Product> product = productRepository.findById(id);

        ResponseDto response = productService.deleteProductById(id);
        if(product.isEmpty()){
            assertEquals(-1, response.getCode());
            assertEquals("Product does not exist", response.getDescription());
        }else {
            assertEquals(0, response.getCode());
            assertEquals("Product has been deleted", response.getDescription());
        }
    }

    @Test
    public void testGetAllProduct_ShouldReturnAnInstanceOfProductDto(){
        Long id = 1L;

        assertInstanceOf(ProductDto.class, productService.getProductById(id));
    }

    @Test
    public void testUpdateProduct_ShouldReturnErrorIfProductDoesNotExists(){
        ProductDto product = new ProductDto();
        Set<String> sizes = new HashSet<>();
        sizes.add("12");
        sizes.add("13");

        Set<ColorDto> colors = new HashSet<>();
        ColorDto color = new ColorDto();
        color.setColor("blue");
        color.setImage("image1");

        BrandDto brand = new BrandDto();
        brand.setName("Nike");

        product.setProductName("Best Shoe");
        product.setProductPrice(100);
        product.setDiscountedPrice(90);
        product.setProductSizes(sizes);
        product.setProductColors(colors);
        product.setGenderCategory(GenderCategory.FEMALE);
        product.setProductBrand(brand);
        product.setProductQuantity(4);

        Long id = 1L;

        Optional<Product> existingProduct = productRepository.findById(id);

        ResponseDto response = productService.updateProductById(id, product);

        if(existingProduct.isEmpty()){
            assertEquals(-1, response.getCode());
            assertEquals("Product does not exist", response.getDescription());
        }else{
            assertEquals(0, response.getCode());
            assertEquals("Products successfully updated", response.getDescription());
        }

    }

    @Test
    public void testConvertEntityToDto_ShouldReturnProductDTO(){
        Product product = new Product();

        Set<String> images = new HashSet<>();
        images.add("d");

        Set<Color> colors = new HashSet<>();
        Color color = new Color();
        color.setColor("color");
        colors.add(color);

        Brand brand = new Brand();
        brand.setBrandName("Nike");

        Set<Size> sizes = new HashSet<>();
        Size size = new Size();
        size.setSize("io");
        sizes.add(size);

        product.setProductName("d");
        product.setProductImage(images);
        product.setProductQuantity(90);
        product.setProductPrice(250);
        product.setDiscountedPrice(25);
        product.setGenderCategory(GenderCategory.MALE);
        product.setProductColors(colors);
        product.setProductBrand(brand);
        product.setProductSizes(sizes);

        assertInstanceOf(ProductDto.class, productService.convertEntityToDto(product));
    }

}
