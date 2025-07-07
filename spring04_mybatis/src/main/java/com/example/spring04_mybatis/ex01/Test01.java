package com.example.spring04_mybatis.ex01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Test01 {
	private final EmployeeService service;
	
	@GetMapping(value="/deptlist")
	public void departmentList() {
		log.info("{}",service.getDepartList());
	}
		

}
