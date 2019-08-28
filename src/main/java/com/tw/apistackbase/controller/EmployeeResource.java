package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	
	//查询所有
	@GetMapping(path ="/")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployee(){

		employees.add(new Employee("0001", "张三", 18, "男"));
		employees.add(new Employee("0002", "李四", 19, "女"));
		employees.add(new Employee("0003", "王五", 23, "男"));
		return employees;
	}
	
	//添加
	@PostMapping(path ="/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee creatEmployee(@RequestBody Employee employee) {
			employees.add(employee);
			return employee;
	}
	
	//查询某个ID
	@GetMapping(path = "/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable String id){
		for(Employee employee:employees) {
			if(employee.getID().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	//修改
	@PutMapping(path = "/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id,@RequestBody Employee changeEmployee){
		for(Employee employee:employees) {
			if(employee.getID().equals(id)) {
				employee.setID(changeEmployee.getID());
				employee.setName(changeEmployee.getName());
				employee.setAge(changeEmployee.getAge());
				employee.setGender(changeEmployee.getGender());
				return ResponseEntity.ok(employee);
			}
		}
		return ResponseEntity.notFound().build();
	}

	
	
	//删除
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deletEmployee(@PathVariable String id) {
		Iterator<Employee> iterator = employees.iterator();
		  while (iterator.hasNext()) {
			   if(iterator.next().getID() == id) {
			    iterator.remove();
			    return ResponseEntity.ok(id + "");
			   }

			}
		  return ResponseEntity.notFound().build();
	}
	
	
}
