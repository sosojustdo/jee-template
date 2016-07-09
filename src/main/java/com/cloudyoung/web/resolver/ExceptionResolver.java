package com.cloudyoung.web.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cloudyoung.exceptions.PermissionDeniedException;
import com.cloudyoung.exceptions.UserNotExistException;
import com.cloudyoung.web.view.JaxbJsonView;

/**
 * Description: 根据异常类型，处理返回结果
 * All Rights Reserved.
 * @version 1.0  2016年6月17日 下午3:25:39  by 代鹏（daipeng.456@gmail.com）创建
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	
	private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.info(String.format("Exception Message is:%s, Cause is:%s", ex.getMessage(), ex.getCause()));
		if (ex instanceof UserNotExistException) {
			return new ModelAndView(new RedirectView());
		}else if (ex instanceof PermissionDeniedException) {
			ModelAndView mv = new ModelAndView("/404");
			mv.addObject("commonVO", new Object());
			return mv;
		}else{
			
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", "failed");
		return new ModelAndView(new JaxbJsonView(new HashMap<String, Object>().put("result", "failed")));
	}
}
