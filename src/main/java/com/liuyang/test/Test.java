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
/**
 * 测试elasticsearch的HTTP请求
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		boolean found = false;
		//查询是否存在
		logger.info("简单查询");
		ResponseEntity<Body1> responseEntity = restTemplate.exchange("http://192.168.59.103:9200/megacorp/employee/1", HttpMethod.GET, null, Body1.class);
		if(responseEntity.getStatusCodeValue() == HttpStatus.OK.value()){
			Body1 body = responseEntity.getBody();
			found = body.isFound();
		}
		
		String body1 = "{\"query\" : {\"match\" : {\"last_name\" : \"Smith\"}}}";
		HttpEntity<String> requestEntity1 = new HttpEntity<String>(body1);
		ResponseEntity<String> responseEntity1 = restTemplate.exchange("http://192.168.59.103:9200/megacorp/employee/_search", HttpMethod.GET, requestEntity1, String.class);
		if(responseEntity1.getStatusCodeValue() == HttpStatus.OK.value()){
			logger.debug(responseEntity1.getBody());
		}
		
		if(found){
			logger.debug("found /megacorp/employee/1");
			logger.debug("stop put");
			return;
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
