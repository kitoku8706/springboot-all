package com.example.spring04_mybatis.ex01;

import java.util.Date;

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
public class Employee {
	private long employee_id;
	private String first_name;
	private long salary;
	private Date hire_date;
	private long department_id;
	
	private Department dept;

}
