package com.cloudyoung.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudyoung.cache.impl.RedisService;
import com.cloudyoung.dao.PoJoMapper;

public class TestDemo extends BaseTest {
	
	@Autowired
	private PoJoMapper baseDao;
	
	@Autowired
	private RedisService redisService;
	
	@Before
	public void checkBeanIsNull(){
		Assert.assertNotNull(baseDao);
		Assert.assertNotNull(redisService);
	}
	
	@Test
	public void test(){
		System.out.println("Hello World!");
	}
	
	@Test
	public void testRedisSet(){
		boolean result = redisService.set("test_redis_set", "test_redis_set");
		Assert.assertTrue(result);
	}
	
}
