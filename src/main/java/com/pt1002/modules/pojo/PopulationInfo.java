package com.pt1002.modules.pojo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pt1002.common.json.LongJsonDeserializer;
import com.pt1002.common.json.LongJsonSerializer;

import java.util.Date;


public class PopulationInfo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    private String name;

    private String sex;

    private String nation;

    private Date birthday;

    private String address;

    private String identityCard;

    private String issue;

    private Date expireDateStart;

    private Date expireDateEnd;

    private String remark;

    private String score;

    private Date lastUpdateTime;


    //record的信息
    private byte proveSuccessful;

    private byte collectSuccessful;

    private Date createTime;

    public byte getProveSuccessful() {
        return proveSuccessful;
    }

    public void setProveSuccessful(byte proveSuccessful) {
        this.proveSuccessful = proveSuccessful;
    }

    public byte getCollectSuccessful() {
        return collectSuccessful;
    }

    public void setCollectSuccessful(byte collectSuccessful) {
        this.collectSuccessful = collectSuccessful;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Date getBirthday() {

        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public Date getExpireDateStart() {

        return expireDateStart;
    }

    public void setExpireDateStart(Date expireDateStart) {
        this.expireDateStart = expireDateStart;
    }

    public Date getExpireDateEnd() {

        return expireDateEnd;
    }

    public void setExpireDateEnd(Date expireDateEnd) {
        this.expireDateEnd = expireDateEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLastUpdateTime() {


        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}