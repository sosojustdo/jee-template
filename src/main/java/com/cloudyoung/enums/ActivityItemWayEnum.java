package com.cloudyoung.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Description:活动明细举办方式枚举 
 * All Rights Reserved.
 * @version 1.0  2016年7月4日 上午10:55:35  by 代鹏（daipeng.456@gmail.com）创建
 */
public enum ActivityItemWayEnum {
	
	DISNEY_DRAW(1, "disney_prize", "一键领取获得"),
	DISNEY_DRIVE_SIGNUP(5, "disney_drive_signup", "报名试驾获得"),
	DISNEY_DOWNLOAD_APP(3, "disney_download_app", "下载菱菱邦获得"),
	DISNEY_SHARE(1, "disney_share", "分享邀请好友获得");
	
	private ActivityItemWayEnum(int giveNum, String giveCode, String giveDesc) {
		this.giveNum = giveNum;
		this.giveCode = giveCode;
		this.giveDesc = giveDesc;
	}

	private int giveNum;//赠送奖券个数
	
	private String giveCode;//赠送奖券业务类型code
	
	private String giveDesc;//赠送奖券业务描述

	public int getGiveNum() {
		return giveNum;
	}

	public void setGiveNum(int giveNum) {
		this.giveNum = giveNum;
	}

	public String getGiveCode() {
		return giveCode;
	}

	public void setGiveCode(String giveCode) {
		this.giveCode = giveCode;
	}

	public String getGiveDesc() {
		return giveDesc;
	}

	public void setGiveDesc(String giveDesc) {
		this.giveDesc = giveDesc;
	}
	
	public static ActivityItemWayEnum getLotteryGiveEnumByCode(String code){
		if(StringUtils.isNotBlank(code)){
			for(ActivityItemWayEnum item:ActivityItemWayEnum.values()){
				if(code.equals(item.getGiveCode())){
					return item;
				}
			}
		}
		return null;
	}
	

}
