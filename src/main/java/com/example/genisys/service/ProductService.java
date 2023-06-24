package com.example.genisys.service;

import com.example.genisys.dto.*;
import com.example.genisys.entity.Brand;
import com.example.genisys.entity.Color;
import com.example.genisys.entity.Product;
import com.example.genisys.entity.Size;
import com.example.genisys.enums.ResponseCodes;
import com.example.genisys.repository.BrandRepository;
import com.example.genisys.repository.ColorRepository;
import com.example.genisys.repository.ProductRepository;
import com.example.genisys.repository.SizeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final BrandRepository brandRepository;

    public ProductService(
                ProductRepository productRepository,
                SizeRepository sizeRepository,
                ColorRepository colorRepository,
                BrandRepository brandRepository
            )
        {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.colorRepository = colorRepository;
        this.brandRepository = brandRepository;
    }

    public ResponseDto createProduct(ProductDto productRequest){

        Optional<Product> existingProduct =  productRepository.findByProductName(productRequest.getProductName());
        if(existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product already exists");
        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductColors(getColors(productRequest.getProductColors()));
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductImage(productRequest.getProductImage());
        product.setProductSizes(getSizes(productRequest.getProductSizes()));
        product.setGenderCategory(productRequest.getGenderCategory());
        product.setProductBrand(getBrand(productRequest.getProductBrand()));
        product.setProductQuantity(productRequest.getProductQuantity());
        productRepository.save(product);

        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product successfully created");
    }

//    public ResponseDto updateProductById(Long id, ProductDto productRequest){
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if(!existingProduct.isPresent()){
//            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
//        }
//
//        Product dbProduct = existingProduct.get();
//
//        dbProduct.setProductName(productRequest.getProductName());
//        dbProduct.setProductPrice(productRequest.getProductPrice());
//        dbProduct.setProductColors(productRequest.getProductColors());
//        dbProduct.setProductSizes(productRequest.getProductSizes());
//        dbProduct.setProductImage(productRequest.getProductImage());
//        dbProduct.setGenderCategory(productRequest.getGenderCategory());
//        dbProduct.setProductBrand(productRequest.getProductBrand());
//        dbProduct.setProductQuantity(productRequest.getProductQuantity());
//        productRepository.save(dbProduct);
//
//        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product successfully updated");
//    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> returnedProducts = new ArrayList<>();

        for(Product product: products){
            returnedProducts.add(createProductRequest(product));
        }
        return returnedProducts;
    }

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return createProductRequest(product.get());
        }

        return new ProductDto();
    }

    public ResponseDto deleteProductById(Long id){
        Optional<Product> existingProduct = productRepository.findById(id);

        if(!existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
        }

        productRepository.deleteById(id);
        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product has been deleted");
    }

    private ProductDto createProductRequest(Product product){
        ProductDto productRequest = new ProductDto();
        Set<ColorDto> colorDtos = product.getProductColors().stream()
                .map(color -> {
                    ColorDto colorDto = new ColorDto();
                    colorDto.setColor(color.getColor());
                    return colorDto;
                }).collect(Collectors.toSet());

        Set<SizeDto> sizeDtos = product.getProductSizes().stream()
                .map(size -> {
                    SizeDto sizeDto = new SizeDto();
                    sizeDto.setSize(size.getSize());
                    return sizeDto;
                }).collect(Collectors.toSet());

        BrandDto brandDto = new BrandDto();
        brandDto.setName(product.getProductBrand().getBrandName());
        productRequest.setId(product.getId());
        productRequest.setProductName(product.getProductName());
        productRequest.setProductPrice(product.getProductPrice());
        productRequest.setProductColors(colorDtos);
        productRequest.setProductSize(sizeDtos);
        productRequest.setProductImage(product.getProductImage());
        productRequest.setGenderCategory(product.getGenderCategory());
        productRequest.setProductBrand(brandDto);
        productRequest.setProductQuantity(product.getProductQuantity());
        return productRequest;
    }


    private Set<Size> getSizes(Set<SizeDto> sizes){
        Set<Size> newSizes = new HashSet<>();
        for(SizeDto size: sizes){
            Optional<Size> existingSize = sizeRepository.findBySize(size.getSize().toLowerCase());
            if(!existingSize.isPresent()){
                Size newSize = new Size();
                newSize.setSize(size.getSize().toLowerCase());
                sizeRepository.save(newSize);
            }else{
                newSizes.add(existingSize.get());
            }
        }
        return newSizes;
    }

    private Set<Color> getColors(Set<ColorDto> colors){
        Set<Color> newColors = new HashSet<>();
        for(ColorDto color: colors){
            Optional<Color> existingColor = colorRepository.findByColor(color.getColor().toLowerCase());
            if(!existingColor.isPresent()){
                Color newColor = new Color();
                newColor.setColor(color.getColor().toLowerCase());
                colorRepository.save(newColor);
                newColors.add(newColor);
            }else{
                newColors.add(existingColor.get());
            }
        }
        return newColors;
    }

    private Brand getBrand(BrandDto brand){
        Optional<Brand> existingBrand = brandRepository.findByBrandName(brand.getName());
        if(!existingBrand.isPresent()){
            Brand newBrand = new Brand();
            newBrand.setBrandName(brand.getName().toLowerCase());
            brandRepository.save(newBrand);
            return newBrand;
        }
        return existingBrand.get();
    }

}
