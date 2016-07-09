package com.cloudyoung.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

@Service("redisService")
public class RedisService {
	
	@Autowired
	private RedisTemplate<?, ?> redisTemplate;
	
	public Boolean set(final String key, final String value){
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
				connection.set(stringSerializer.serialize(key), stringSerializer.serialize(value));
				return true;
			}
		});
	}
	
	public Boolean set(final String key, final String value, final long seconds){
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
				connection.setEx(stringSerializer.serialize(key), seconds, stringSerializer.serialize(value));
				return true;
			}
		});
	}
	
	public <T> T get(final String key, final Class<T> clazz){
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
				byte[] bytes = connection.get(stringSerializer.serialize(key));
				if(null != bytes && bytes.length > 0){
					String valueString = stringSerializer.deserialize(bytes);
					if(!Strings.isNullOrEmpty(valueString)){
						return JSON.parseObject(valueString, clazz);
					}
				}
				return null;
			}
		});
	} 
	
	public Boolean expire(final String key, final long seconds){
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
				connection.expire(stringSerializer.serialize(key), seconds);
				return true;
			}
		});
	}

}
