package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO employee);

    ResponseEntity<EmployeeDTO> getEmployee(Long employeeId);

    ResponseEntity<EmployeeDTO> removeEmployee(Long employeeId);
}
