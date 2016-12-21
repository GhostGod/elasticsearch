package com.liuyang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 我的控制器
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@RestController
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	@RequestMapping("")
	public String test(@RequestHeader("user-agent") String userAgent) {
		logger.info("userAgent = {}", userAgent);
		return "媳妇儿~ I love you!!!";
	}
}
