package com.example.genisys.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="CART_PRODUCT")
@Data
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name="PRICE", nullable = false)
    private double price;

    @ElementCollection(targetClass=Integer.class)
    @Column(name="WIDTH", nullable=false)
    private Set<Integer> width;

    @ElementCollection(targetClass = String.class)
    @Column(name = "SIZES", nullable = false)
    private Set<String> sizes;

    @ElementCollection(targetClass = String.class)
    @Column(name = "COLORS", nullable = false)
    private Set<String> colors;

    @ElementCollection(targetClass=String.class)
    @Column(name="IMAGES", nullable = false)
    private Set<String> images;
}
