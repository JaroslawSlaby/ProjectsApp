package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    void getEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Jarek");
        System.out.println(employee);
    }

    @PostMapping("/employees")
    void addEmployee(@RequestBody @Valid Employee employee) {
        service.addEmployee(employee);
        System.out.println(employee);
    }
}
