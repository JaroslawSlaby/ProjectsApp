package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{employeeId}")
    ResponseEntity<Employee> getEmployee(@PathVariable(value = "employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    ResponseEntity<Employee> removeEmployee(@PathVariable(value = "employeeId") Long employeeId) {
        return employeeService.removeEmployee(employeeId);
    }
}
