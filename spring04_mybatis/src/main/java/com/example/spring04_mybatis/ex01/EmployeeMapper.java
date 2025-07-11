package com.example.spring04_mybatis.ex01;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
	//@Select(value = "SELECT * FROM departments")
	public List<Department> selectDepartmentList();
	
	public List<Employee> selectJoinList();
	
	public List<Department> selectCollList();

}
