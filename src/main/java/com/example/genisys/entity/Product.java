package com.example.genisys.entity;

import com.example.genisys.enums.GenderCategory;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Integer id;

    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private double productPrice;

    @Column(name = "DISCOUNTED_PRICE", nullable = false)
    private double discountedPrice;

    @Column(name = "PRODUCT_IMAGE", nullable = false)
    private String productImage;

    @Column(name = "PRODUCT_SIZE", nullable = false)
    private String productSize;

    @Column(name = "PRODUCT_COLOR", nullable = false)
    private String productColor;

    @Column(name = "GENDER_CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderCategory genderCategory;

    @Column(name = "UNIQUE_CATEGORY", nullable = false)
    private String uniqueCategory; // Nike

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private int productQuantity;
}
