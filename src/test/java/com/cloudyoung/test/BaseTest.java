package com.cloudyoung.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/*.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@Transactional
public abstract class BaseTest {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected void sql(String sql){
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * Description: params transfer to map
	 * Format:"custId", 123, "status", 2, "domain", "www.baidu.com", "postId", 123456
	 * @Version1.0 2016年6月17日 下午4:38:16 by 代鹏（daipeng.456@gmail.com）创建
	 * @param params
	 * @return
	 */
	protected Map<String, Object> builderParams(Object ...params) {
		if(params.length <= 0){
			new IllegalArgumentException("builderParams params is empty!");
		}
		if(params.length % 2 !=0){
			new IllegalArgumentException("builderParams params length must be even numbers!");
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Object> objectList = Arrays.asList(params);
		int size = objectList.size();
		for(int i=0; i<size; i=i+2){
			Object k = objectList.get(i);
			if(k instanceof String){
				Object v = objectList.get(i+1);
				paramsMap.put((String)k, v);
			}
		}
		return paramsMap;
	}
	
}
