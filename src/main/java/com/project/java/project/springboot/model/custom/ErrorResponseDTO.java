package com.project.java.project.springboot.model.custom;

public class ErrorResponseDTO {
        private String errorMessage;

        public ErrorResponseDTO(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

