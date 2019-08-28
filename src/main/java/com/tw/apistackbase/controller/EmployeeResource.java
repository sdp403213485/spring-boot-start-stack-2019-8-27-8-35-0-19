package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;


	@RestController
	@RequestMapping("/employees")

public class EmployeeResource {
	
	
	List<Employee> employees = new ArrayList<Employee>();
	
	@GetMapping(path ="/")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployee(){

		employees.add(new Employee("0001", "张三", 18, "男"));
		employees.add(new Employee("0002", "李四", 19, "女"));
		employees.add(new Employee("0003", "王五", 23, "男"));
		return employees;
	}
	
	@PostMapping(path ="/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee creatEmployee(@RequestBody Employee employee) {
			employees.add(employee);
			return employee;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable String id){
		for(Employee employee:employees) {
			if(employee.getID().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
}
