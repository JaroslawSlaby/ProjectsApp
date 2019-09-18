package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.models.Employee;
import com.epam.mentoring_p1.exceptions.EmployeeNotFoundException;
import com.epam.mentoring_p1.exceptions.EmployeeSavingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String USER_CANNOT_BE_FOUND = "User cannot be found: ";
    private static final String EMPLOYEE_CANNOT_BE_SAVED = "Employee cannot be saved";

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        return createResponse(Optional.of(saved).orElseThrow(getEmployeeSavingException()));
    }

    @Override
    public ResponseEntity<Employee> getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return createResponse(employee.orElseThrow(getUserNotFoundException(employeeId)));
    }

    @Override
    public ResponseEntity<Employee> removeEmployee(Long employeeId) {
        Optional<Employee> tempEmployee = employeeRepository.findById(employeeId);
        Employee employeeToDelete = tempEmployee.orElseThrow(getUserNotFoundException(employeeId));
        employeeRepository.delete(employeeToDelete);
        return createResponse(employeeToDelete);
    }

    private ResponseEntity<Employee> createResponse(Employee employee) {
        return ResponseEntity.ok().body(employee);
    }

    private Supplier<EmployeeNotFoundException> getUserNotFoundException(Long employeeId) {
        return () -> new EmployeeNotFoundException(USER_CANNOT_BE_FOUND + employeeId);
    }

    private Supplier<EmployeeSavingException> getEmployeeSavingException() {
        return () -> new EmployeeSavingException(EMPLOYEE_CANNOT_BE_SAVED);
    }
}
