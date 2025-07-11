package com.example.spring04_mybatis.ex01;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Department {
	private long department_id;
	private String department_name;
	private long manager_id;
	private long location_id;
	private List<Employee> listEmp;
	
}
