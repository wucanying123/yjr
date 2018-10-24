package com.pt1002.common.enums;

public enum  FaceType {
    GENERAL(1,"普通照"),
    IDEN(2,"证件照");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    FaceType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
