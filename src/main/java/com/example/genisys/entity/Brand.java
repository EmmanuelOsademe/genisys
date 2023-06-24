package com.example.genisys.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "BRAND")
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND_NAME")
    private String brandName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "productBrand")
    private Set<Product> products;
}
