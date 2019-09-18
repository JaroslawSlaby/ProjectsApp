package com.epam.mentoring_p1.configuration;

import com.epam.mentoring_p1.exceptions.EmployeeNotFoundException;
import com.epam.mentoring_p1.exceptions.EmployeeSavingException;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.exceptions.ProjectSavingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({EmployeeNotFoundException.class,
            EmployeeSavingException.class,
            ProjectNotFoundException.class,
            ProjectSavingException.class
    })
    public ResponseEntity<Object> handleExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
