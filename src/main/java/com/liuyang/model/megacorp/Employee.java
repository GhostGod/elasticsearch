package com.liuyang.model.megacorp;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * 员工
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@Document(indexName = "enterprise", type = "employee")
@Mapping(mappingPath = "a.json")
public class Employee implements Serializable {
	private static final long serialVersionUID = 2185299528325285395L;
	private String id;
	private String last_name;
	//private List<String> interests;
	private String first_name;
	private int age;
	@CreatedDate
	private Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/*public List<String> getInterests() {
		return interests;
	}
	
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}*/

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", last_name=" + last_name + ", first_name=" + first_name + ", age=" + age + ", createdDate="
				+ createdDate + "]";
	}

}
