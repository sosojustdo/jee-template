package com.cloudyoung.web.view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlAttribute;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

public class JaxbJsonView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(JaxbJsonView.class);

	/** 服务器状态 */
	private Integer status;

	private ResponseHeader responseHeader;

	private Map<String, String> mapHeader;

	private Object view;

	private ObjectMapper objMapper;

	private JsonFactory jsonFactory;

	public JaxbJsonView(int status) {
		this((ResponseHeader) null, null, status);
	}

	public JaxbJsonView(ResponseHeader responseHeader) {
		this(responseHeader, null, null);
	}

	public JaxbJsonView(Object view) {
		this((ResponseHeader) null, view, null);
		ResponseHeader header = new ResponseHeader();
		header.setCode("200");
		header.setVersion("1.0.0");
		header.setProtocolVersion("1");
		this.responseHeader = header;
	}

	public JaxbJsonView(ResponseHeader responseHeader, Integer status) {
		this(responseHeader, null, status);
	}

	public JaxbJsonView(ResponseHeader responseHeader, Object view) {
		this(responseHeader, view, null);
	}

	public JaxbJsonView(ResponseHeader responseHeader, Object view, Integer status) {
		this.responseHeader = responseHeader;
		this.view = view;
		this.status = status;
		setBeanName("Default name by header with: " + getClass().getName());
	}

	public JaxbJsonView(Map<String, String> responseHeader, Object view, Integer status) {
		this.mapHeader = responseHeader;
		this.view = view;
		this.status = status;
		setBeanName("Default name by mapping with: " + getClass().getName());
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (this.status != null) {
			response.setStatus(this.status);
		}

		StringBuilder headerLog = new StringBuilder();
		if (mapHeader != null) {
			for (String key : mapHeader.keySet()) {
				response.setHeader(key, mapHeader.get(key));
				headerLog.append(key).append(":").append(mapHeader.get(key)).append(",");
			}
		}

		if (responseHeader != null) {
			if (responseHeader.getApi() == null) {
				responseHeader.setApi(request.getRequestURI());
			}

			Field[] fs = responseHeader.getClass().getDeclaredFields();
			for (Field field : fs) {
				field.setAccessible(true);
				Object val = field.get(responseHeader);
				XmlAttribute attribute = field.getAnnotation(XmlAttribute.class);
				response.setHeader(attribute.name(),
						val == null ? "" : new String(String.valueOf(val).getBytes("UTF-8"), "ISO-8859-1"));
				// response.setHeader(attribute.name(), val == null ? "" :
				// URLEncoder.encode(String.valueOf(val), "UTF-8"));

				headerLog.append(attribute.name()).append(":").append(val).append(",");
			}
		}

		logger.debug("Return header : {}", headerLog);

		if (view != null) {
			String json = toJSON(view);
			logger.debug("Return json:{}", json);
			PrintWriter out = response.getWriter();
			out.println(json);
		}
	}

	public void cusRender(HttpServletRequest request, HttpServletResponse response) throws Exception {
		renderMergedOutputModel(null, request, response);
	}

	private String toJSON(Object obj) throws IllegalArgumentException, IllegalAccessException, Exception {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = getJsonFactory().createJsonGenerator(sw);
		ObjectMapper objMapper = getObjMapper();
		objMapper.writeValue(jg, obj);
		return sw.toString();
	}

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public Map<String, String> getMapHeader() {
		return mapHeader;
	}

	public Object getView() {
		return view;
	}

	private ObjectMapper getObjMapper() {
		if (objMapper == null) {
			objMapper = new ObjectMapper();
			objMapper.registerModule(new XSDModule());
			//注册序列号协议：不输出null
			objMapper.setSerializationConfig(objMapper.getSerializationConfig().withSerializationInclusion(JsonSerialize.Inclusion.ALWAYS));
		}
		return objMapper;
	}

	private JsonFactory getJsonFactory() {
		if (jsonFactory == null) {
			jsonFactory = new JsonFactory();
		}
		return jsonFactory;
	}

}
