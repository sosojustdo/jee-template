package com.cloudyoung.web.filter;

import java.io.PrintWriter;

public class PrintWriterWrapper extends PrintWriter{
	
	private transient StringBuilder result = new StringBuilder();
	
	PrintWriterWrapper(PrintWriter out) {
		super(out);
	}

	@Override
	public void write(char[] buf, int off, int len) {
		super.write(buf, off, len);
	}

	@Override
	public void write(String s, int off, int len) {
		//保留一份写入的str，非序列化
		result.append(s);
		super.write(s, 0, s.length());
	}

	/**
	 * @return the result
	 */
	public String getTransientResult() {
		return result.toString();
	}	
}