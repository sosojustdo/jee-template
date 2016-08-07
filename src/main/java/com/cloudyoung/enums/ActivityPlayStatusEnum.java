package com.cloudyoung.enums;

/**
 * Description:活动进行状态枚举 
 * All Rights Reserved.
 * @version 1.0  2016年7月7日 下午3:02:47  by 代鹏（daipeng.456@gmail.com）创建
 */
public enum ActivityPlayStatusEnum {
	
	UNSTART(0, "活动未开始"),
	DOING(1, "活动进行中"),
	END(2, "活动已完结");
	
	private int status;
	private String desc;
	
	private ActivityPlayStatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
