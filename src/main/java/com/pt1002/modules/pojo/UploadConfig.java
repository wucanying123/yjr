package com.pt1002.modules.pojo;

import java.util.Date;

public class UploadConfig {
    private Integer id;

    private String ip;

    private Integer port;

    private Date lastUpdate;

    private String remark;

    private Integer macPort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getMacPort() {
        return macPort;
    }

    public void setMacPort(Integer macPort) {
        this.macPort = macPort;
    }
}