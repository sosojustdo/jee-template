package com.cloudyoung.web.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cloudyoung.web.view.JaxbJsonView;

@Controller
public class IndexController {
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/json")
	public ModelAndView json(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "test");
		map.put("passWord", "test123");
		return new ModelAndView(new JaxbJsonView(map));
	}
}
