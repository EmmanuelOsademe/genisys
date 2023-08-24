package com.example.genisys.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class BrandDto {

    private Long id;

    @NotBlank(message = "Brand cannot be empty")
    private String name;

    public BrandDto(){};
}
