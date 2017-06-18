package com.liuyang.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang.model.megacorp.Employee;
import com.liuyang.repository.es.EmployeeEsRepository;

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
	@RequestMapping("add")
	public void es() {
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setId(i + "");
			employee.setAge(10 + i);
			employee.setFirst_name("my name is " + i);
			employee.setLast_name("中华人民共和国");
			employee.setCreatedDate(new Date());
			//employee.setInterests(list);
			//保存到es
			//Employee employee2 = employeeJpaRepository.save(employee);
			employeeEsRepository.save(employee);
		}
		//employeeEsRepository.index(employee);
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
	}

	@RequestMapping("enterprise/employee/{id}")
	public List<Employee> get(@PathVariable String id, String a1) {
		BoolQueryBuilder a = new BoolQueryBuilder();
		QueryBuilder query1 = QueryBuilders.multiMatchQuery(id, "last_name");
		a.must(query1);
		if (StringUtils.hasText(a1)) {
			QueryBuilder query2 = QueryBuilders.termQuery("first_name", a1);
			a.must(query2);
		}
		Sort sort = new Sort(Direction.DESC, "age");
		//sort.and(new Sort(Direction.DESC, "createdDate"));
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

	@RequestMapping("d")
	public boolean d() {
		employeeEsRepository.deleteAll();
		return true;
	}
}
