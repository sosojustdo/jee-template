package com.cloudyoung.xxx.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityTakeRecord implements Serializable{
	
	private static final long serialVersionUID = -4380915451022704226L;

	private Long activityTakeRecordId;

    private Long activityId;

    private String activityCode;

    private Long activityItemId;

    private String userId;

    private String mobile;

    private String obtainUnicode;

    private Integer prizeFlag;

    private String platform;

    private String deviceNo;

    private Date obtainDate;

    public Long getActivityTakeRecordId() {
        return activityTakeRecordId;
    }

    public void setActivityTakeRecordId(Long activityTakeRecordId) {
        this.activityTakeRecordId = activityTakeRecordId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    public Long getActivityItemId() {
        return activityItemId;
    }

    public void setActivityItemId(Long activityItemId) {
        this.activityItemId = activityItemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getObtainUnicode() {
        return obtainUnicode;
    }

    public void setObtainUnicode(String obtainUnicode) {
        this.obtainUnicode = obtainUnicode == null ? null : obtainUnicode.trim();
    }

    public Integer getPrizeFlag() {
        return prizeFlag;
    }

    public void setPrizeFlag(Integer prizeFlag) {
        this.prizeFlag = prizeFlag;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public Date getObtainDate() {
        return obtainDate;
    }

    public void setObtainDate(Date obtainDate) {
        this.obtainDate = obtainDate;
    }
}