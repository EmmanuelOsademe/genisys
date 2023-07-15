//package com.example.genisys.service;
//
//import com.example.genisys.dto.BrandDto;
//import com.example.genisys.dto.ColorDto;
//import com.example.genisys.dto.ProductDto;
//import com.example.genisys.dto.ResponseDto;
//import com.example.genisys.enums.GenderCategory;
//import com.example.genisys.repository.BrandRepository;
//import com.example.genisys.repository.ColorRepository;
//import com.example.genisys.repository.ProductRepository;
//import com.example.genisys.repository.SizeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//    private ProductService productService;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private ColorRepository colorRepository;
//
//    @Mock
//    private SizeRepository sizeRepository;
//
//    @Mock
//    private BrandRepository brandRepository;
//
//    @BeforeEach
//    public void setUp(){
//        productService = new ProductService(productRepository, sizeRepository, colorRepository, brandRepository);
//    }
//
//    @Test
//    public void testCreateProduct_ShouldReturnSuccess_WhenAllConditionsAreMet(){
//        ProductDto product = new ProductDto();
//
//        Set<String> sizes = new HashSet<>();
//        sizes.add("12");
//        sizes.add("13");
//
//        Set<ColorDto> colors = new HashSet<>();
//        ColorDto color = new ColorDto();
//        color.setColor("blue");
//        color.setImage("image1");
//
//        BrandDto brand = new BrandDto();
//        brand.setName("Nike");
//
//        product.setProductName("Best Shoe");
//        product.setProductPrice(100);
//        product.setDiscountedPrice(90);
//        product.setProductSizes(sizes);
//        product.setProductColors(colors);
//        product.setGenderCategory(GenderCategory.FEMALE);
//        product.setProductBrand(brand);
//        product.setProductQuantity(4);
//
//        ProductDto[] products = new ProductDto[1];
//        products[0] = product;
//
//        ResponseDto response = productService.createProduct(products);
//        assertEquals(0, response.getCode());
//        assertEquals("Products successfully created", response.getDescription());
//    }
//
//    @Test
//    public void testCreateProduct_ShouldReturnError_IfProductAlreadyExists(){
//        ProductDto product = new ProductDto();
//
//        Set<String> sizes = new HashSet<>();
//        sizes.add("12");
//        sizes.add("13");
//
//        Set<ColorDto> colors = new HashSet<>();
//        ColorDto color = new ColorDto();
//        color.setColor("blue");
//        color.setImage("image1");
//
//        BrandDto brand = new BrandDto();
//        brand.setName("Nike");
//
//        product.setProductName("Best Shoe");
//        product.setProductPrice(100);
//        product.setDiscountedPrice(90);
//        product.setProductSizes(sizes);
//        product.setProductColors(colors);
//        product.setGenderCategory(GenderCategory.FEMALE);
//        product.setProductBrand(brand);
//        product.setProductQuantity(4);
//
//        ProductDto[] products = new ProductDto[2];
//        products[0] = product;
//        products[1] = product;
//
//        ResponseDto response = productService.createProduct(products);
//        assertEquals(-1, response.getCode());
//        assertEquals("Product already exists", response.getDescription());
//    }
//}
