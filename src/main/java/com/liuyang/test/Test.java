package com.liuyang.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liuyang.core.Body1;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		//查询是否存在
		ResponseEntity<Body1> responseEntity = restTemplate.exchange("http://192.168.59.103:9200/megacorp/employee/1", HttpMethod.GET, null, Body1.class);
		if(responseEntity.getStatusCodeValue() == HttpStatus.OK.value()){
			Body1 body = responseEntity.getBody();
			if(body.isFound()){
				logger.debug("found /megacorp/employee/1.");
				logger.debug("stop put");
				return;
			}
		}
		
		
		JSONObject body = new JSONObject();
		body.put("first_name", "John");
		body.put("last_name", "Smith");
		body.put("age", 25);
		body.put("about", "I love to go rock climbing");
		JSONArray interests = new JSONArray();
		interests.add("sports");
		interests.add("music");
		body.put("interests", interests);
		HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(body);
		responseEntity = restTemplate.exchange("http://192.168.59.103:9200/megacorp/employee/1", HttpMethod.PUT, requestEntity, Body1.class);
		logger.debug(responseEntity.getBody().toString());
	}
}
