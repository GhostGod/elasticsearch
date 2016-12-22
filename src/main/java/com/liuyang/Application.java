package com.liuyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Spring boot 入口
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@SpringBootApplication
@EnableElasticsearchRepositories("com.liuyang.repository.es")
@EnableJpaRepositories("com.liuyang.repository.jpa")
@EnableJpaAuditing
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
