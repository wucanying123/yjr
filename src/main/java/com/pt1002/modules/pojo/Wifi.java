package com.pt1002.modules.pojo;

public class Wifi {
    private Integer id;

    private String devmac;

    private String climac;

    private String ssid;

    private String bssid;

    private String time;

    private String signall;

    private String channel;

    private Long recordId;

    private String oui;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevmac() {
        return devmac;
    }

    public void setDevmac(String devmac) {
        this.devmac = devmac == null ? null : devmac.trim();
    }

    public String getClimac() {
        return climac;
    }

    public void setClimac(String climac) {
        this.climac = climac == null ? null : climac.trim();
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid == null ? null : ssid.trim();
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid == null ? null : bssid.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getSignall() {
        return signall;
    }

    public void setSignall(String signall) {
        this.signall = signall == null ? null : signall.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getOui() {
        return oui;
    }

    public void setOui(String oui) {
        this.oui = oui == null ? null : oui.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}