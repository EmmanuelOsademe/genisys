package com.example.genisys.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ColorDto {

    private Long id;

    @NotBlank(message = "Color cannot be empty")
    private String color;

    @NotBlank(message="Image cannot be empty")
    private String image;

}
