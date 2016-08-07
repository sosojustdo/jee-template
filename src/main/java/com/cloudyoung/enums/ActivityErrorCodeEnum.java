package com.cloudyoung.enums;

/**
 * Description: 活动错误状态码枚举, 错误状态码范围：10000-20000
 * All Rights Reserved.
 * @version 1.0  2016年7月4日 下午2:49:10  by 代鹏（daipeng.456@gmail.com）创建
 */
public enum ActivityErrorCodeEnum {
	
    ACTIVITY_ERR_10000(10000, "参数非法"),
    ACTIVITY_ERR_10001(10001, "该活动不存在"),
	ACTIVITY_ERR_10002(10002, "当前无正在进行的活动"),
	ACTIVITY_ERR_10003(10003, "用户id和手机号不能同时为空"),
	ACTIVITY_ERR_10004(10004, "奖券赠送类型不合法"),
	ACTIVITY_ERR_10005(10005, "您已领取该奖券"),
	ACTIVITY_ERR_10006(10006, "活动还未开始"),
	ACTIVITY_ERR_10007(10007, "活动已结束"),
	ACTIVITY_ERR_10008(10008, "活动还未发布"),
	ACTIVITY_ERR_10009(10009, "活动明细后台配置不正确"),
	ACTIVITY_ERR_10010(10010, "活动卷码生成失败"),
	ACTIVITY_ERR_10011(10011, "活动领取奖券失败，请重试"),
	ACTIVITY_ERR_10012(10012, "活动领取奖券成功"),
	ACTIVITY_ERR_10013(10013, "手机号不合法"),
	ACTIVITY_ERR_10014(10014, "您访问的频率太快，请稍后再试!");
	
	private int code;
	private String msg;

	private ActivityErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static ActivityErrorCodeEnum getByErrorCode(int code){
		for(ActivityErrorCodeEnum errorCodeEnum : ActivityErrorCodeEnum.values()){
			if(errorCodeEnum.getCode() == code){
				return errorCodeEnum;
			}
		}
		return null;
	}
	
}
