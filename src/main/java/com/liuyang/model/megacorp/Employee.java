package com.liuyang.model.megacorp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 员工
 * @author liuyang
 * @email y_liu@hiersun.com | 745089707@qq.com
 */
@Document(indexName = "enterprise", type = "emloyee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 2185299528325285395L;
	@Id
	private String id;
	private String last_name;
	private List<String> interests;
	private String first_name;
	private int age;

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

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

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

	@Override
	public String toString() {
		return "Employee [last_name=" + last_name + ", interests=" + interests + ", first_name=" + first_name
				+ ", age=" + age + "]";
	}
}
