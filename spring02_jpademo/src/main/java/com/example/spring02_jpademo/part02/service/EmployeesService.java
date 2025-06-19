package com.example.spring02_jpademo.part02.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring02_jpademo.part02.dto.EmployeesDTO;
import com.example.spring02_jpademo.part02.entity.EmployeesEntity;
import com.example.spring02_jpademo.part02.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;

@Service

public class EmployeesService {

	@Autowired
	private EmployeesRepository employeesRepository;

	public EmployeesService() {
		// TODO Auto-generated constructor stub
	}

	// ✅ JPQL 방식: Entity + 부서명 포함
	public List<Map<String, Object>> getAllEmployeesWithDepartmentJPQL() {
		List<EmployeesEntity> entities = employeesRepository.findAllWithDepartmentJPQL();
		List<Map<String, Object>> result = new ArrayList<>();

		for (EmployeesEntity e : entities) {
			EmployeesDTO dto = EmployeesDTO.toDTO(e);
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("employee", dto);
			map.put("departmentName", e.getDepartment().getDepartmentName());
			result.add(map);
		}
		return result;
	}

	// ✅ Native 방식: Object[] → DTO + 부서명
	public List<Map<String, Object>> getAllEmployeesWithDepartmentNative() {
	        List<Object[]> rows = employeesRepository.findAllWithDepartmentNative();
	        List<Map<String, Object>> result = new ArrayList<>();

	        for (Object[] row : rows) {
	            EmployeesDTO dto = EmployeesDTO.builder()
	                    .employeeId(((Number) row[0]).longValue())
	                    .firstName((String) row[1])
	                    .lastName((String) row[2])
	                    .email((String) row[3])
	                    .phoneNumber((String) row[4])
	                    .hireDate((Date) row[5])
	                    .jobId((String) row[6])
	                    .salary(row[7] != null ? ((Number) row[7]).doubleValue() : null)
	                    .commissionPct(row[8] != null ? ((Number) row[8]).doubleValue() : null)
	                    .managerId(row[9] != null ? ((Number) row[9]).longValue() : null)
	                    .departmentId(row[10] != null ? ((Number) row[10]).longValue() : null)
	                    .build();

	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("employee", dto);
	            map.put("departmentName", row[11]);
	            result.add(map);
	        }

	        return result;
	    }
}
