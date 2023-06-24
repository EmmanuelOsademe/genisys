package com.example.genisys.dto;

import javax.validation.constraints.NotBlank;

public class ColorDto {

    private Long id;

    @NotBlank(message = "Color cannot be empty")
    private String color;

    public ColorDto(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ColorDto(String color) {
        this.color = color;
    }
}
