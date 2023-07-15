package com.example.genisys.dto;

import com.example.genisys.enums.GenderCategory;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class ProductDto {
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotBlank(message = "Product price cannot be blank")
    private double productPrice;

    @NotBlank(message = "Discounted price may not be blank")
    private double discountedPrice;

//    @NotBlank(message = "Product image may not be blank")
//    private String productImage;

    @NotBlank(message = "Product size may not be blank")
    private Set<String> productSizes;

    @NotBlank(message = "Product color may not be blank")
    private Set<ColorDto> productColors;

    @NotBlank(message = "Product gender category may not be blank")
    private GenderCategory genderCategory;

    @NotBlank(message = "Brand may not be blank")
    private BrandDto productBrand;

    @NotBlank(message = "Product quantity may not be blank")
    private int productQuantity;

    public ProductDto(){}

    public ProductDto(Long productId, String productName, double productPrice, double discountedPrice, Set<String> productSizes, Set<ColorDto> productColors, GenderCategory genderCategory, BrandDto productBrand, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountedPrice = discountedPrice;
        this.productSizes = productSizes;
        this.productColors = productColors;
        this.genderCategory = genderCategory;
        this.productBrand = productBrand;
        this.productQuantity = productQuantity;
        this.id = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Set<String> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<String> productSizes) {
        this.productSizes = productSizes;
    }

    public Set<ColorDto> getProductColors() {
        return productColors;
    }

    public void setProductColors(Set<ColorDto> productColors) {
        this.productColors = productColors;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }

    public BrandDto getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(BrandDto productBrand) {
        this.productBrand = productBrand;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
