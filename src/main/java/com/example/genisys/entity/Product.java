package com.example.genisys.entity;

import com.example.genisys.enums.GenderCategory;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private double productPrice;

    @Column(name = "DISCOUNTED_PRICE", nullable = false)
    private double discountedPrice;

    @ElementCollection(targetClass = String.class)
    @Column(name = "PRODUCT_IMAGE", nullable = false)
    private Set<String> productImage;

    @ManyToMany
    @JoinTable(name = "PRODUCT_SIZES",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> productSizes;

    @ManyToMany
    @JoinTable(name = "PRODUCT_COLORS",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> productColors;

    @Column(name = "GENDER_CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderCategory genderCategory;

    @ManyToOne
    private Brand productBrand; // Nike

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private int productQuantity;
}
