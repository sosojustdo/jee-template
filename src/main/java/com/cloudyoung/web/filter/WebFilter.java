package com.cloudyoung.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: filter implements
 * All Rights Reserved.
 * @version 1.0  2016年6月17日 下午3:10:35  by 代鹏（daipeng.456@gmail.com）创建
 */
public class WebFilter implements Filter {
	
	private static Logger logger = LoggerFactory.getLogger(WebFilter.class);
	
	public WebFilter() {}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Init web filter...");
		
		//TODO
		
		logger.info("Init web filter success!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("webFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try{
			//设置请求的编码
			//// 使用spring等的编码filter处理
			//对请求进行包装
			httpRequest = wrapRequest(httpRequest);
			//对应答进行包装
			httpResponse = wrapResponse(httpResponse);
			//将请求和应答交给下一个处理器处理
			filterChain.doFilter(httpRequest, httpResponse);
		}catch (Exception e){
            logger.error("WebFilter error", e);
        }
	}
	
	private HttpServletRequest wrapRequest(HttpServletRequest request){
		if (!(request instanceof HttpServletRequestDecorator)) {
			return new HttpServletRequestDecorator(request);
		}
		return request;
	}
	
	private HttpServletResponse wrapResponse(HttpServletResponse response){
		if (!(response instanceof HttpServletResponseDecorator)) {
			return new HttpServletResponseDecorator(response);
		}
		return response;
	}
	
	@Override
	public void destroy() {
		logger.info("Destroy web filter success!");
	}
	
}
