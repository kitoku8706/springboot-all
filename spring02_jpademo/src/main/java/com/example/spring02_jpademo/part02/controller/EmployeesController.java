package com.example.spring02_jpademo.part02.controller;

import com.example.spring02_jpademo.part02.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;
    
    
//    @Autowired
//    private EmployeesService employeesService;

    // http://localhost:8090/api/employees/with-department-jpql
    // JPQL 방식
    @GetMapping("/with-department-jpql")
    public List<Map<String, Object>> getEmployeesWithDepartmentJPQL() {
        return employeesService.getAllEmployeesWithDepartmentJPQL();
    }

    
 // http://localhost:8090/api/employees/with-department-native
    //  Native SQL 방식
    @GetMapping("/with-department-native")
    public List<Map<String, Object>> getEmployeesWithDepartmentNative() {
        return employeesService.getAllEmployeesWithDepartmentNative();
    }
}
