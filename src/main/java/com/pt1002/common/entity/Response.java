package com.pt1002.common.entity;


import com.pt1002.common.enums.ResponseCode;

public class Response<T> {

    private int code;

    private String message;

    private T data;

    public Response() {
    }

    public Response(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public Response(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //未知异常
    public static final Response UNKNOWN_EXCEPTION = new Response(ResponseCode.UNKNOWN_EXCEPTION);

    //操作成功
    public static final Response SUCCESS = new Response(ResponseCode.SUCCESS);
}
