package com.pt1002.modules.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Templates {
    private Integer id;

    private String uid;

    private Integer pid;

    private Date date;

    private String deviceSn;

    private Integer mark;

    private Float enrDist;

    private Short quality;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn == null ? null : deviceSn.trim();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Float getEnrDist() {
        return enrDist;
    }

    public void setEnrDist(Float enrDist) {
        this.enrDist = enrDist;
    }

    public Short getQuality() {
        return quality;
    }

    public void setQuality(Short quality) {
        this.quality = quality;
    }


}