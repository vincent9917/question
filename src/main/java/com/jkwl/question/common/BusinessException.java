package com.jkwl.question.common;

public class BusinessException extends RuntimeException {
    private String error;

    public BusinessException(String error) {
        super(error);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
