package com.cloudyoung.xxx.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Description: 活动处理结果返回Vo
 * All Rights Reserved.
 * @version 1.0  2016年7月6日 下午2:23:38  by 代鹏（daipeng.456@gmail.com）创建
 */
public class ActivityResultVo implements Serializable {
	
	private static final long serialVersionUID = 7026286724377503796L;
	
	/**
	 * 成功
	 */
	public static final boolean SUCCESS = true;
	
	/**
	 * 失败
	 */
	public static final boolean FAILED = false;

	/**
	 * 操作返回状态
	 */
	private boolean ret;

	/**
	 * 返回错误消息
	 */
	private String errmsg;

	/**
	 * 错误状态码
	 */
	private Integer errcode;

	/**
	 * 返回具体对象
	 */
	private Object data;

	/**
	 * 返回Map<String, Object>
	 */
	private Map<String, ?> dataMap;

	/**
	 * 返回List集合
	 */
	private List<?> dataList;

	public ActivityResultVo() {
		super();
	}
	
	public ActivityResultVo(boolean ret){
		super();
		this.ret = ret;
	}

	public ActivityResultVo(boolean ret, String errmsg, Integer errcode) {
		super();
		this.ret = ret;
		this.errmsg = errmsg;
		this.errcode = errcode;
	}

	public boolean getRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, ?> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, ?> dataMap) {
		this.dataMap = dataMap;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}
