package com.jkwl.question.common;

public class Result<T> {
    private String msg;
    private int code;
    private T data;
    public static final int SUCCESS_CODE = 0;

    public Result() {
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.code = SUCCESS_CODE;
        result.msg = "成功";
        result.data = data;
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = SUCCESS_CODE;
        result.msg = "成功";
        result.data = null;
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.code = -1;
        result.msg = msg;
        return result;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }
}
