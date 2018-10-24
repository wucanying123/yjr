package com.pt1002.modules.pojo;

public class BlackList {
    private Integer id;

    private String name;

    private String idcard;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BlackList() {

    }

    public BlackList(Integer id, String name, String idcard, String remark) {
        this.id = id;
        this.name = name;
        this.idcard = idcard;
        this.remark = remark;
    }
}