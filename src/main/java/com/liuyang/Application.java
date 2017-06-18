package com.liuyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Spring boot 入口
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@SpringBootApplication
@EnableElasticsearchRepositories("com.liuyang.repository.es")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
