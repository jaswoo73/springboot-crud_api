package com.example.crud_api.dto;

public class ResponseDTO<T> {
    private Integer errorCode;
    private String errorMessage;
    private T body;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer errorCode, String errorMessage, T body) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.body = body;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
