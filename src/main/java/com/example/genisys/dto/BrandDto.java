package com.example.genisys.dto;

import com.example.genisys.entity.Brand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class BrandDto {

    private Long id;

    @NotBlank(message = "Brand cannot be empty")
    private String name;

    public BrandDto(){};


}
