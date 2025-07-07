package com.example.spring04_mybatis.ex01;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeMapper employeeMapper;
	
	public List<Department> getDepartList(){
		return employeeMapper.selectDepartmentList();
	}

}
