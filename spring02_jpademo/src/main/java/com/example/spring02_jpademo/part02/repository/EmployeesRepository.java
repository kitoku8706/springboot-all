package com.example.spring02_jpademo.part02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring02_jpademo.part02.entity.EmployeesEntity;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long> {

	// ✅ JPQL 조인: EmployeesEntity 반환 (부서도 가져옴)
    @Query("SELECT e FROM EmployeesEntity e JOIN FETCH e.department")
    List<EmployeesEntity> findAllWithDepartmentJPQL();

    // ✅ Native Query 조인: 필요한 필드만 직접 선택
    @Query(value = """
        SELECT 
            e.employee_id, e.first_name, e.last_name, e.email, e.phone_number,
            e.hire_date, e.job_id, e.salary, e.commission_pct, e.manager_id,
            e.department_id, d.department_name
        FROM employees e
        JOIN departments d ON e.department_id = d.department_id
    """, nativeQuery = true)
    List<Object[]> findAllWithDepartmentNative();
}