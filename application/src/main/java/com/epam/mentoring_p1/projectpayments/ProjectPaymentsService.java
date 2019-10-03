package com.epam.mentoring_p1.projectpayments;

import com.epam.mentoring_p1.repomodels.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ProjectPaymentsService {
    ResponseEntity<Set<Payment>> getProjectPayments(Long projectId);

    ResponseEntity<Payment> makePayment(Long projectId, String paymentValue);
}
