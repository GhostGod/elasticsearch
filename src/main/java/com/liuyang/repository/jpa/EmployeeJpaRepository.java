package com.liuyang.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liuyang.model.megacorp.Employee;

//@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, String> {

}
