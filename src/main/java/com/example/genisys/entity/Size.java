package com.example.genisys.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="SIZES")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String size;

    @JsonIgnore
    @ManyToMany(mappedBy = "productSizes", cascade = CascadeType.ALL)
    private Set<Product> products;

}
