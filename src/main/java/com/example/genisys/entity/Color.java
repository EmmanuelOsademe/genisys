package com.example.genisys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COLORS")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR")
    private String color;

    @JsonIgnore
    @ManyToMany(mappedBy = "productColors")
    private Set<Product> products;
}
