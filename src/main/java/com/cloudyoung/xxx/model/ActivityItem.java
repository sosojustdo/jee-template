package com.cloudyoung.xxx.model;

import java.util.Date;

public class ActivityItem {
    private Long activityItemId;

    private String itemType;

    private String itemWayType;

    private Byte haveExpire;

    private Date expireDate;

    private Byte faceValueRandom;

    private Integer faceValue;

    private Byte haveUpperLimit;

    private Long maxNum;

    private Long remaindNum;

    private Date createDate;

    public Long getActivityItemId() {
        return activityItemId;
    }

    public void setActivityItemId(Long activityItemId) {
        this.activityItemId = activityItemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public String getItemWayType() {
        return itemWayType;
    }

    public void setItemWayType(String itemWayType) {
        this.itemWayType = itemWayType == null ? null : itemWayType.trim();
    }

    public Byte getHaveExpire() {
        return haveExpire;
    }

    public void setHaveExpire(Byte haveExpire) {
        this.haveExpire = haveExpire;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Byte getFaceValueRandom() {
        return faceValueRandom;
    }

    public void setFaceValueRandom(Byte faceValueRandom) {
        this.faceValueRandom = faceValueRandom;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public Byte getHaveUpperLimit() {
        return haveUpperLimit;
    }

    public void setHaveUpperLimit(Byte haveUpperLimit) {
        this.haveUpperLimit = haveUpperLimit;
    }

    public Long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Long maxNum) {
        this.maxNum = maxNum;
    }

    public Long getRemaindNum() {
        return remaindNum;
    }

    public void setRemaindNum(Long remaindNum) {
        this.remaindNum = remaindNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}