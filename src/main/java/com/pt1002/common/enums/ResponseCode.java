package com.pt1002.common.enums;

public enum ResponseCode {

    SUCCESS(1,"操作成功"),

    FALLURE(2,"操作失败"),

    UNKNOWN_EXCEPTION(-1,"未知异常");

    private int code;
    private String message;

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

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
