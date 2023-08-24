package com.example.genisys.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CartProductDto {
    private Long id;

    @NotBlank(message = "Price cannot be blank")
    private double price;

    @NotBlank(message = "Colors cannot be blank")
    private Set<String> colors;

    @NotBlank(message = "Sizes cannot be empty")
    private Set<String> sizes;

    @NotBlank(message = "Images cannot be empty")
    private Set<String> images;

    @NotBlank(message = "Width cannot be empty")
    private Set<Integer> width;
}
