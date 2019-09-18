package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<Employee> addEmployee(Employee employee);

    ResponseEntity<Employee> getEmployee(Long employeeId);

    ResponseEntity<Employee> removeEmployee(Long employeeId);
}
