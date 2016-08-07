package com.cloudyoung.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Description: 活动明细类型枚举
 * All Rights Reserved.
 * @version 1.0  2016年7月4日 上午11:42:56  by 代鹏（daipeng.456@gmail.com）创建
 */
public enum ActivityItemTypeEnum {
	
	LOTTERY("lottery", "活动赠送奖券");
	
	private String type;
	
	private String typeDesc;
	
	private ActivityItemTypeEnum(String type, String typeDesc) {
		this.type = type;
		this.typeDesc = typeDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	public static final ActivityItemTypeEnum getActivityTypeEnumByType(String type){
		if(StringUtils.isNotBlank(type)){
			for(ActivityItemTypeEnum item:ActivityItemTypeEnum.values()){
				if(type.equals(item.getType())){
					return item;
				}
			}
		}
		return null;
	}
	
}
