package com.example.spring02_jpademo.part02.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "departments")
public class DepartmentsEntity {
	@Id
	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "department_name", nullable = false, length = 30)
	private String departmentName;

	@Column(name = "manager_id")
	private Long managerId;

	@Column(name = "location_id")
	private Long locationId;
	
	@OneToMany(mappedBy = "department")
	private List<EmployeesEntity> employees;
}
