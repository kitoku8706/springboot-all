package com.example.spring04_mybatis.ex01;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Test01 {
	private final EmployeeService service;

	@GetMapping(value = "/deptlist")
	public void departmentList() {
		log.info("{}", service.getDepartList());
		for (Department dept : service.getDepartList())
			System.out.printf("%d %s @d %d\n", dept.getDepartment_id(), dept.getDepartment_name(),
					dept.getLocation_id(), dept.getManager_id());
	}

	@GetMapping(value = "/empdeptlist")
	public void empdeptList() {
		log.info("{}", service.getJoinList());

		List<Employee> aList = service.getJoinList();
		for (Employee employee : aList) {
			Department department = employee.getDept();
			System.out.printf("%d %s %d %s %d %s\n", employee.getEmployee_id(), employee.getFirst_name(),
					employee.getSalary(), employee.getHire_date(), department.getDepartment_id(),
					department.getDepartment_name());
		}

		
	}
	@GetMapping(value="/joinlist")
	public void collList() {
		log.info("{}", service.getCollList());
		
		List<Department> aList = service.getCollList();
		 for(Department dept : aList) {
			 System.out.printf("%d %s\n", dept.getDepartment_id(), dept.getDepartment_name());
			 for(Employee emp : dept.getListEmp() ) {
				 System.out.printf("%20s %d %s %d %s\n", " ", emp.getEmployee_id(), emp.getFirst_name(),
						 emp.getSalary(), emp.getHire_date());
			 }
		 }
	}




}
