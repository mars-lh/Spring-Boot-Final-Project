package com.project.java.project.springboot.model.custom;

import lombok.Data;

@Data
public class CustomResponse {
    private String responseMessage;

    public CustomResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
