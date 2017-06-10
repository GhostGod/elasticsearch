package com.liuyang.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.liuyang.model.megacorp.Employee;
@Repository
public interface EmployeeEsRepository extends ElasticsearchRepository<Employee, String> {

}
