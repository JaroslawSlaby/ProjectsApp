package com.epam.mentoring_p1.employees;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.repomodels.Employee;
import com.epam.mentoring_p1.exceptions.EmployeeNotFoundException;
import com.epam.mentoring_p1.exceptions.EmployeeSavingException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String USER_CANNOT_BE_FOUND = "User cannot be found: ";
    private static final String EMPLOYEE_CANNOT_BE_SAVED = "Employee cannot be saved";

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO employee) {
        Employee toBeSaved = mapper.map(employee, Employee.class);
        Employee saved = employeeRepository.save(toBeSaved);
        EmployeeDTO dto = mapper.map(saved, EmployeeDTO.class);
        return createResponse(Optional.of(dto).orElseThrow(getEmployeeSavingException()));
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(Long employeeId) {
        Optional<Employee> employeeFromDB = employeeRepository.findById(employeeId);
        Employee employee = employeeFromDB.orElseThrow(getUserNotFoundException(employeeId));
        return createResponse(mapper.map(employee, EmployeeDTO.class));
    }

    @Override
    public ResponseEntity<EmployeeDTO> removeEmployee(Long employeeId) {
        Optional<Employee> tempEmployee = employeeRepository.findById(employeeId);
        Employee employeeToDelete = tempEmployee.orElseThrow(getUserNotFoundException(employeeId));
        employeeRepository.delete(employeeToDelete);
        return createResponse(mapper.map(employeeToDelete, EmployeeDTO.class));
    }

    private ResponseEntity<EmployeeDTO> createResponse(EmployeeDTO employee) {
        return ResponseEntity.ok().body(employee);
    }

    private Supplier<EmployeeNotFoundException> getUserNotFoundException(Long employeeId) {
        return () -> new EmployeeNotFoundException(USER_CANNOT_BE_FOUND + employeeId);
    }

    private Supplier<EmployeeSavingException> getEmployeeSavingException() {
        return () -> new EmployeeSavingException(EMPLOYEE_CANNOT_BE_SAVED);
    }
}
