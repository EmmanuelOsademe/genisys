package com.example.genisys.dto;

import javax.validation.constraints.NotBlank;

public class SizeDto {
    private Long id;

    @NotBlank(message = "Size cannot be empty")
    private String size;

    public SizeDto(){};

    public SizeDto(String size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
