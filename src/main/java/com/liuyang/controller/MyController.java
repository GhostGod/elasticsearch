package com.liuyang.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang.model.megacorp.Employee;
import com.liuyang.repository.es.EmployeeEsRepository;
import com.liuyang.repository.jpa.EmployeeJpaRepository;

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
	private EmployeeJpaRepository employeeJpaRepository;
	@Autowired
	private EmployeeEsRepository employeeEsRepository;

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
		employee.setAge(10);
		employee.setFirst_name("hehe");
		employee.setLast_name("g MVC try to derive a Pageable ");
		List<String> list = new ArrayList<String>();
		list.add("dota");
		list.add("lol");
		//employee.setInterests(list);
		//保存到es
		//Employee employee2 = employeeJpaRepository.save(employee);
		employeeEsRepository.save(employee);
		employeeEsRepository.index(employee);
		//logger.info(employee2.toString());
		//IndexQuery indexQuery = new IndexQueryBuilder().withId("1").withIndexName("enterprise").withObject(employee).withType("employee")
		//		.build();
		//template.index(indexQuery);
		//查询全部
		//Iterable<Employee> iterable = employeeJpaRepository.findAll();
		//if (iterable != null) {
		//	Iterator<Employee> iterator = iterable.iterator();
		//	if (iterator.hasNext()) {
		//		employee = iterator.next();
		//	}
		//}
		return employee;
	}

	@RequestMapping("enterprise/employee/{id}")
	public List<Employee> get(@PathVariable String id, int age) {
		QueryBuilder query1 = QueryBuilders.multiMatchQuery(id, "last_name");
		QueryBuilder query2 = QueryBuilders.termQuery("age", age);
		BoolQueryBuilder a = new BoolQueryBuilder();
		a.must(query1);//.must(query2);
		Sort sort = new Sort(Direction.DESC, "age");

		Pageable page = new PageRequest(0, 10, sort);
		Page<Employee> employees = employeeEsRepository.search(a, page);
		return employees.getContent();
	}

	@RequestMapping("enterprise/employees")
	public List<Employee> get() {
		//查询全部
		Iterable<Employee> iterable = employeeEsRepository.findAll();
		List<Employee> list = new ArrayList<Employee>();
		if (iterable != null) {
			Iterator<Employee> iterator = iterable.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		}
		return list;
	}
}
