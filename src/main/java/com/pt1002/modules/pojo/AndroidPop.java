package com.pt1002.modules.pojo;

import java.util.Date;

public class AndroidPop {
    private Long id;

    private String name;

    private String sex;

    private String nation;

    private String birthday;

    private String address;

    private String identityCard;

    private String issue;

    private String expire_date_start;

    private String expire_date_end;

    private String remark;

    private String score;

    private String proveSuccessful;

    private String collectSuccessful;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getExpire_date_start() {
        return expire_date_start;
    }

    public void setExpire_date_start(String expire_date_start) {
        this.expire_date_start = expire_date_start;
    }

    public String getExpire_date_end() {
        return expire_date_end;
    }

    public void setExpire_date_end(String expire_date_end) {
        this.expire_date_end = expire_date_end;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getProveSuccessful() {
        return proveSuccessful;
    }

    public void setProveSuccessful(String proveSuccessful) {
        this.proveSuccessful = proveSuccessful;
    }

    public String getCollectSuccessful() {
        return collectSuccessful;
    }

    public void setCollectSuccessful(String collectSuccessful) {
        this.collectSuccessful = collectSuccessful;
    }
}
