package com.example.genisys.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SizeDto {
    private Long id;

    @NotBlank(message = "Size cannot be empty")
    private String size;

    public SizeDto(){};

}
