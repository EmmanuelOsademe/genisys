package com.example.genisys.dto;

import com.example.genisys.enums.GenderCategory;

import javax.validation.constraints.NotBlank;

public class ProductDto {
    private Integer Id;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotBlank(message = "Product price cannot be blank")
    private double productPrice;

    @NotBlank(message = "Discounted price may not be blank")
    private double discountedPrice;

    @NotBlank(message = "Product image may not be blank")
    private String productImage;

    @NotBlank(message = "Product size may not be blank")
    private String productSize;

    @NotBlank(message = "Product color may not be blank")
    private String productColor;

    @NotBlank(message = "Product gender category may not be blank")
    private GenderCategory genderCategory;

    @NotBlank(message = "Unique category may not be blank")
    private String uniqueCategory;

    @NotBlank(message = "Product quantity may not be blank")
    private int productQuantity;

    public ProductDto(){}

    public ProductDto(String productName, double productPrice, double discountedPrice, String productImage, String productSize, String productColor, GenderCategory genderCategory, String uniqueCategory, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountedPrice = discountedPrice;
        this.productImage = productImage;
        this.productSize = productSize;
        this.productColor = productColor;
        this.genderCategory = genderCategory;
        this.uniqueCategory = uniqueCategory;
        this.productQuantity = productQuantity;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }

    public String getUniqueCategory() {
        return uniqueCategory;
    }

    public void setUniqueCategory(String uniqueCategory) {
        this.uniqueCategory = uniqueCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
}
