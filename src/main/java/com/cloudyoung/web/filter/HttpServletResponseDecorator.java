package com.cloudyoung.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpServletResponseDecorator extends HttpServletResponseWrapper {
	
	private PrintWriter wrappedWriter = null;
	private final ReentrantLock lock = new ReentrantLock();
	//为了方便拦截器获取最终结果值，添加一个临时对象
	private transient Map<String, String> transientHeader = new HashMap<String, String>();
	// 为了方便拦截器获取最终结果值，添加一个临时对象，http 状态码
	private transient int httpState = 200;
	
	/**
	 * @param response
	 */
	public HttpServletResponseDecorator(HttpServletResponse response) {
		super(response);		
	}

	/**
	 * header 临时对象
	 * @return the transientHeader
	 */
	public Map<String, String> getTransientHeader() {
		return transientHeader;
	}
	
	/**
	 * @return the httpState
	 */
	public int getTransientHttpState() {
		return httpState;
	}

	@Override
	public void setHeader(String name, String value) {
		transientHeader.put(name, value);
		super.setHeader(name, value);
	}
	
	@Override
	public void setStatus(int sc) {
		this.httpState = sc;
		super.setStatus(sc);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		lock.lock();
		try{
			if (wrappedWriter == null) {
				wrappedWriter = new PrintWriterWrapper(super.getWriter());
			}
		}finally{
			lock.unlock();
		}
		return wrappedWriter;
	}
}
