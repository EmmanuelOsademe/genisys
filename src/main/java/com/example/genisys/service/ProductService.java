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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final BrandRepository brandRepository;

    public ResponseDto createProduct(ProductDto[] productRequest){

        for(ProductDto productDto: productRequest){
            if(!(productDto instanceof ProductDto)){
                return new ResponseDto(ResponseCodes.INVALID_INPUT.getCode(), "Invalid product provided");
            }
        }

        boolean alreadyExists = false;

        for(ProductDto productDto: productRequest){
            Optional<Product> existingProduct =  productRepository.findByProductName(productDto.getProductName());
            if(existingProduct.isPresent()){
                alreadyExists = true;
                return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product already exists");
            }

            Product product = new Product();
            product.setProductName(productDto.getProductName());
            product.setProductColors(getColors(productDto.getProductColors()));
            product.setProductPrice(productDto.getProductPrice());
            product.setProductImage(getImages(productDto.getProductColors()));
            product.setProductSizes(getSizes(productDto.getProductSizes()));
            product.setGenderCategory(productDto.getGenderCategory());
            product.setProductBrand(getBrand(productDto.getProductBrand()));
            product.setProductQuantity(productDto.getProductQuantity());
            product.setDiscountedPrice(productDto.getDiscountedPrice());
            productRepository.save(product);
        }

        if(alreadyExists){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "One or more products already exist");
        }else{
            return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Products successfully created");
        }
    }

    public ResponseDto updateProductById(Long id, ProductDto productRequest){
        Optional<Product> existingProduct = productRepository.findById(id);
        if(!existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
        }

        Product dbProduct = existingProduct.get();

        dbProduct.setProductName(productRequest.getProductName());
        dbProduct.setProductPrice(productRequest.getProductPrice());
        dbProduct.setDiscountedPrice(productRequest.getDiscountedPrice());
        dbProduct.setProductColors(getColors(productRequest.getProductColors()));
        dbProduct.setProductSizes(getSizes(productRequest.getProductSizes()));
        dbProduct.setProductImage(getImages(productRequest.getProductColors()));
        dbProduct.setGenderCategory(productRequest.getGenderCategory());
        dbProduct.setProductBrand(getBrand(productRequest.getProductBrand()));
        dbProduct.setProductQuantity(productRequest.getProductQuantity());
        productRepository.save(dbProduct);

        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Products successfully updated");
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> returnedProducts = new ArrayList<>();

        for(Product product: products){
            ProductDto productDto = convertEntityToDto(product);
            returnedProducts.add(productDto);
        }

        return returnedProducts;
    }

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return convertEntityToDto(product.get());
        }

        return new ProductDto();
    }

    public ResponseDto deleteProductById(Long id){
        Optional<Product> existingProduct = productRepository.findById(id);

        if(!existingProduct.isPresent()){
            return new ResponseDto(ResponseCodes.ERROR.getCode(), "Product does not exist");
        }

        productRepository.delete(existingProduct.get());
        return new ResponseDto(ResponseCodes.SUCCESS.getCode(), "Product has been deleted");
    }

    public ProductDto convertEntityToDto(Product product){
        ProductDto productRequest = new ProductDto();

        List<String> imageList = new ArrayList<>(product.getProductImage());
        List<Color> colorList = new ArrayList<>(product.getProductColors());

        Set<ColorDto> colors = new HashSet<>();

        for(int i = 0; i < imageList.size(); i++){
            ColorDto colorDto = new ColorDto();
            colorDto.setImage(imageList.get(i));
            colorDto.setColor(colorList.get(i).getColor());
            colors.add(colorDto);
        }



        Set<String> sizeDtos = product.getProductSizes().stream()
                .map(size -> {
                    return size.getSize();
                }).collect(Collectors.toSet());

        BrandDto brandDto = new BrandDto();
        brandDto.setName(product.getProductBrand().getBrandName());

        productRequest.setId(product.getId());
        productRequest.setProductName(product.getProductName());
        productRequest.setProductPrice(product.getProductPrice());
        productRequest.setProductColors(colors);
        productRequest.setProductSizes(sizeDtos);
        productRequest.setGenderCategory(product.getGenderCategory());
        productRequest.setProductBrand(brandDto);
        productRequest.setProductQuantity(product.getProductQuantity());
        productRequest.setDiscountedPrice(product.getDiscountedPrice());
        return productRequest;
    }


    public Set<Size> getSizes(Set<String> sizes){
        Set<Size> newSizes = new HashSet<>();
        for(String size: sizes){
            Optional<Size> existingSize = sizeRepository.findBySize(size.toLowerCase());
            if(!existingSize.isPresent()){
                Size newSize = new Size();
                newSize.setSize(size.toLowerCase());
                sizeRepository.save(newSize);
            }else{
                newSizes.add(existingSize.get());
            }
        }
        return newSizes;
    }

    public Set<Color> getColors(Set<ColorDto> colors){
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

    private Set<String> getImages(Set<ColorDto> colors){
        Set<String> images = new HashSet<>();
        for(ColorDto color: colors){
            images.add(color.getImage());
        }
        return images;
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
