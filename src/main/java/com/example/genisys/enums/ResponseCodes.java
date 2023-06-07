package com.example.genisys.enums;

public enum ResponseCodes {
    SUCCESS(0, "Success"),
    ERROR(-1, "Error"),
    INVALID_INPUT(-2, "Invalid input");

    int code;
    String description;

    ResponseCodes(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
