package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.repomodels.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

  private final EmployeeService employeeService;

  EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/employees/{employeeId}")
  ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(value = "employeeId") Long employeeId) {
    return employeeService.getEmployee(employeeId);
  }

  @PostMapping("/employees")
  ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
    return employeeService.addEmployee(employee);
  }

  @DeleteMapping("/employees/{employeeId}")
  ResponseEntity<EmployeeDTO> removeEmployee(@PathVariable(value = "employeeId") Long employeeId) {
    return employeeService.removeEmployee(employeeId);
  }
}
