package com.epam.mentoring_p1.projectpayments;

import com.epam.mentoring_p1.models.Payment;

import java.util.Set;

public interface ProjectPaymentsOperations {
    Set<Payment> getProjectPayments(Long projectId);

    Payment makePayment(Long projectId, String paymentValue);
}
