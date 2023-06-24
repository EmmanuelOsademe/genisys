package com.example.genisys.dto;

import com.example.genisys.entity.Brand;
import com.example.genisys.entity.Color;
import com.example.genisys.entity.Size;
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

    @NotBlank(message = "Product image may not be blank")
    private String productImage;

    @NotBlank(message = "Product size may not be blank")
    private Set<SizeDto> productSizes;

    @NotBlank(message = "Product color may not be blank")
    private Set<ColorDto> productColors;

    @NotBlank(message = "Product gender category may not be blank")
    private GenderCategory genderCategory;

    @NotBlank(message = "Brand may not be blank")
    private BrandDto productBrand;

    @NotBlank(message = "Product quantity may not be blank")
    private int productQuantity;

    public ProductDto(){}

    public ProductDto(String productName, double productPrice, double discountedPrice, String productImage, Set<SizeDto> productSizes, Set<ColorDto> productColors, GenderCategory genderCategory, BrandDto productBrand, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountedPrice = discountedPrice;
        this.productImage = productImage;
        this.productSizes = productSizes;
        this.productColors = productColors;
        this.genderCategory = genderCategory;
        this.productBrand = productBrand;
        this.productQuantity = productQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    public void setProductSizes(Set<SizeDto> productSizes) {
        this.productSizes = productSizes;
    }

    public BrandDto getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(BrandDto productBrand) {
        this.productBrand = productBrand;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Set<SizeDto> getProductSizes() {
        return productSizes;
    }

    public void setProductSize(Set<SizeDto> productSizes) {
        this.productSizes = productSizes;
    }

    public Set<ColorDto> getProductColors() {
        return productColors;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductColors(Set<ColorDto> productColors) {
        this.productColors = productColors;
    }
}
