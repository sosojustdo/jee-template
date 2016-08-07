package com.cloudyoung.xxx.model;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable{
	
	private static final long serialVersionUID = 7247165256355451536L;

	private Long activityId;

    private String activityCode;

    private String activityName;

    private String activityDesc;

    private Integer activityEachNum;

    private String activityEachTitle;

    private String activityNotice;
    
    private String activityNoticeUrl;
    
    private String activityHomePageUrl;

    private String activityImgUrl;

    private Date activityStartDate;

    private Date activityEndDate;

    private Date activityPublishStartDate;

    private Date activityPublishEndDate;

    private Byte activityPublished;

    private Byte activityPlayStatus;

    private Byte activityStatus;

    private Long createUser;

    private Date createDate;

    private Date lastUpdateDate;

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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc == null ? null : activityDesc.trim();
    }

    public Integer getActivityEachNum() {
        return activityEachNum;
    }

    public void setActivityEachNum(Integer activityEachNum) {
        this.activityEachNum = activityEachNum;
    }

    public String getActivityEachTitle() {
        return activityEachTitle;
    }

    public void setActivityEachTitle(String activityEachTitle) {
        this.activityEachTitle = activityEachTitle == null ? null : activityEachTitle.trim();
    }

    public String getActivityNotice() {
        return activityNotice;
    }

    public void setActivityNotice(String activityNotice) {
        this.activityNotice = activityNotice == null ? null : activityNotice.trim();
    }

    public String getActivityNoticeUrl() {
		return activityNoticeUrl;
	}

	public void setActivityNoticeUrl(String activityNoticeUrl) {
		this.activityNoticeUrl = activityNoticeUrl == null ? null : activityNoticeUrl.trim();
	}

	public String getActivityHomePageUrl() {
		return activityHomePageUrl;
	}

	public void setActivityHomePageUrl(String activityHomePageUrl) {
		this.activityHomePageUrl = activityHomePageUrl == null ? null :activityHomePageUrl.trim();
	}

	public String getActivityImgUrl() {
        return activityImgUrl;
    }

    public void setActivityImgUrl(String activityImgUrl) {
        this.activityImgUrl = activityImgUrl == null ? null : activityImgUrl.trim();
    }

    public Date getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(Date activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public Date getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(Date activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public Date getActivityPublishStartDate() {
        return activityPublishStartDate;
    }

    public void setActivityPublishStartDate(Date activityPublishStartDate) {
        this.activityPublishStartDate = activityPublishStartDate;
    }

    public Date getActivityPublishEndDate() {
        return activityPublishEndDate;
    }

    public void setActivityPublishEndDate(Date activityPublishEndDate) {
        this.activityPublishEndDate = activityPublishEndDate;
    }

    public Byte getActivityPublished() {
        return activityPublished;
    }

    public void setActivityPublished(Byte activityPublished) {
        this.activityPublished = activityPublished;
    }

    public Byte getActivityPlayStatus() {
        return activityPlayStatus;
    }

    public void setActivityPlayStatus(Byte activityPlayStatus) {
        this.activityPlayStatus = activityPlayStatus;
    }

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}