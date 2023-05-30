package com.project.java.project.springboot.model.custom;

import lombok.Data;

@Data
public class CustomResponse <T>{
    private T data;
    private String message;

    public CustomResponse(T userResponse, String message) {
    }
}
