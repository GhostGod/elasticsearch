package com.liuyang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.liuyang.model.megacorp.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
