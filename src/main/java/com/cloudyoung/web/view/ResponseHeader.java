package com.cloudyoung.web.view;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ResponseHeader")
public class ResponseHeader {

	@XmlAttribute(name = "version")
	protected String version;

	@XmlAttribute(name = "protocol-version")
	protected String protocolVersion;

	@XmlAttribute(name = "code")
	protected String code;

	@XmlAttribute(name = "detail")
	protected String detail;

	@XmlAttribute(name = "api")
	protected String api;

	@XmlAttribute(name = "message")
	protected String message;

	public String getVersion() {
		if (version == null) {
			return "1.0.0";
		} else {
			return version;
		}
	}

	public void setVersion(String value) {
		this.version = value;
	}

	public String getProtocolVersion() {
		if (protocolVersion == null) {
			return "1";
		} else {
			return protocolVersion;
		}
	}

	public void setProtocolVersion(String value) {
		this.protocolVersion = value;
	}

	public String getCode() {
		if (code == null) {
			return "200";
		} else {
			return code;
		}
	}

	public void setCode(String value) {
		this.code = value;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String value) {
		this.detail = value;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String value) {
		this.api = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String value) {
		this.message = value;
	}

}
