package com.pt1002.modules.pojo;

import java.util.Date;

public class CertificationRecord {
    private Long id;

    private Long identityId;

    private Long devicesId;

    private String identity;

    private String devicesSn;

    private String scenePath;

    private String picturePath;

    private Float score;

    private String remark;

    private Date createTime;

    private String provevalue;

    private Byte provesuccessful;

    private Byte collectsuccessful;

    private String photoPath;

    private Byte state;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Long identityId) {
        this.identityId = identityId;
    }

    public Long getDevicesId() {
        return devicesId;
    }

    public void setDevicesId(Long devicesId) {
        this.devicesId = devicesId;
    }

    public String getScenePath() {
        return scenePath;
    }

    public void setScenePath(String scenePath) {
        this.scenePath = scenePath == null ? null : scenePath.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProvevalue() {
        return provevalue;
    }

    public void setProvevalue(String provevalue) {
        this.provevalue = provevalue == null ? null : provevalue.trim();
    }

    public Byte getProvesuccessful() {
        return provesuccessful;
    }

    public void setProvesuccessful(Byte provesuccessful) {
        this.provesuccessful = provesuccessful;
    }

    public Byte getCollectsuccessful() {
        return collectsuccessful;
    }

    public void setCollectsuccessful(Byte collectsuccessful) {
        this.collectsuccessful = collectsuccessful;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDevicesSn() {
        return devicesSn;
    }

    public void setDevicesSn(String devicesSn) {
        this.devicesSn = devicesSn;
    }
}