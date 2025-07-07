package com.example.spring04_mybatis.ex01;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
	List<Department> selectDepartmentList();

}
