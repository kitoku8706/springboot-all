package com.example.spring04_mybatis.ex01;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeMapper employeeMapper;
	
	public List<Department> getDepartList(){
		return employeeMapper.selectDepartmentList();
	}
	
	public  List<Employee> getJoinList(){
		  return employeeMapper.selectJoinList();
	  }
	
	public List<Department> getCollList(){
		return employeeMapper.selectCollList();
	}






}
