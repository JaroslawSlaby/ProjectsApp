package com.epam.mentoring_p1.projectpayments;

import com.epam.mentoring_p1.models.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class ProjectPaymentsServiceImpl implements ProjectPaymentsService {

    private final ProjectPaymentsOperations projectPaymentsOperations;

    public ProjectPaymentsServiceImpl(ProjectPaymentsOperations projectPaymentsOperations) {
        this.projectPaymentsOperations = projectPaymentsOperations;
    }

    @Override
    public ResponseEntity<Set<Payment>> getProjectPayments(Long projectId) {
        Set<Payment> projectPayments = projectPaymentsOperations.getProjectPayments(projectId);
        return new ResponseEntity<>(projectPayments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Payment> makePayment(Long projectId, String paymentValue) {
        Payment payment = projectPaymentsOperations.makePayment(projectId, paymentValue);
        return new ResponseEntity<>(payment, HttpStatus.OK);


    }
}
