package com.cloudyoung.web.controller.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cloudyoung.service.ActivityTakeRecordService;
import com.cloudyoung.web.view.JaxbJsonView;
import com.cloudyoung.xxx.vo.ActivityResultVo;
import com.llb.cloudyoung.framework.tools.lock.RedissonDistributedLock;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	private Integer count = 0;
	private static final String test_lock = "test_lock";
	
	@Autowired
	private RedissonDistributedLock redissonDistributedLock;
	
	@Autowired
	private ActivityTakeRecordService activityTakeRecordService;
	
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
	
	@RequestMapping(value="/sum")
	public ModelAndView sum(HttpServletRequest request, HttpServletResponse response){
		System.out.println(Thread.currentThread().getName());
		redissonDistributedLock.lock(test_lock);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sum", count++);
		//logger.info("sum value is:" + count);
		redissonDistributedLock.unlock(test_lock);
		return new ModelAndView(new JaxbJsonView(map));
	}
	
	@RequestMapping(value = "/recevieActivityLottery", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView recevieActivityLottery(@RequestParam(value="activityId", required=true) Long activityId,
			@RequestParam(value="lotteryGiveType", required=true) String lotteryGiveType,
			@RequestParam(value="token", required=false) String token, 
			@RequestParam(value="referralUserId", required=false) String referralUserId,
			@RequestParam(value="referralMobile", required=false) String referralMobile,
			@RequestParam(value="mobile", required=false) String mobile,
			@RequestParam(value="deviceNo", required=false) String deviceNo,
			@RequestParam(value="platform", required=false) String platform){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("activityId", activityId);
		paramsMap.put("lotteryGiveType", lotteryGiveType);
		paramsMap.put("mobile", mobile);
		StringBuffer sb = new StringBuffer(test_lock).append(":").append(activityId).append(":").append(mobile);
		redissonDistributedLock.lock(sb.toString());
		ActivityResultVo resultVo = activityTakeRecordService.recevieActivityLottery(paramsMap);
		redissonDistributedLock.unlock(sb.toString());
		return new ModelAndView(new JaxbJsonView(resultVo));
	}
	
	
}
