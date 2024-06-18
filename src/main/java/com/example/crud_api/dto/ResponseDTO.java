package com.example.crud_api.dto;

public class ResponseDTO<T> {
    private Integer errorCode;
    private String errorMessage;
    private T body;

    public ResponseDTO() {
    }

    public ResponseDTO(T body) {
        this.body = body;
    }

    public ResponseDTO(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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
