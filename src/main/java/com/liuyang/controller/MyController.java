package com.liuyang.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang.model.megacorp.Employee;
import com.liuyang.repository.EmployeeRepository;

/**
 * 我的控制器
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@RestController
public class MyController {
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	@Autowired
	private ElasticsearchTemplate template;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 你猜
	 * @param userAgent
	 * @return
	 */
	@RequestMapping("")
	public String test(@RequestHeader("user-agent") String userAgent) {
		logger.info("userAgent = {}", userAgent);
		return "媳妇儿~ I love you!!!";
	}

	/**
	 * 测试es
	 * @return
	 */
	@RequestMapping("es/{index}")
	public Object createIndex(@PathVariable String index) {
		return template.createIndex(index);
	}

	/**
	 * 测试es
	 * @return 
	 * @return
	 */
	@RequestMapping("es")
	public Employee es() {
		Employee employee = new Employee();
		employee.setAge(20);
		employee.setFirst_name("hehe");
		employee.setLast_name("haha");
		List<String> list = new ArrayList<String>();
		list.add("dota");
		list.add("lol");
		employee.setInterests(list);
		//保存到es
		Employee employee2 = employeeRepository.save(employee);
		logger.info(employee2.toString());
		IndexQuery indexQuery = new IndexQueryBuilder().withId("1").withIndexName("enterprise").withObject(employee)
				.withType("employee").build();
		template.index(indexQuery);
		//查询全部
		Iterable<Employee> iterable = employeeRepository.findAll();
		if(iterable != null){
			Iterator<Employee> iterator = iterable.iterator();
			if(iterator.hasNext()){
				employee = iterator.next();
			}
		}
		return employee;
	}
}
