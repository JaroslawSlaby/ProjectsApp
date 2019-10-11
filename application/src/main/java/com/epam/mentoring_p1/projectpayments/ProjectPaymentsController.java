package com.epam.mentoring_p1.projectpayments;

import com.epam.mentoring_p1.repomodels.Payment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProjectPaymentsController {

    private final ProjectPaymentsService projectPaymentsService;

    public ProjectPaymentsController(ProjectPaymentsService projectPaymentsService) {
        this.projectPaymentsService = projectPaymentsService;
    }

    @GetMapping(value = "/projectPayments/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Payment>> getProjectPayments(@PathVariable(value = "projectId") Long projectId) {
        return projectPaymentsService.getProjectPayments(projectId);
    }

    @PostMapping("/projectPayments/{projectId}/makePayment/{paymentValue}")
    ResponseEntity<Payment> makePayment
            (@PathVariable(value = "projectId") Long projectId,
             @PathVariable(value = "paymentValue") String paymentValue) {
        return projectPaymentsService.makePayment(projectId, paymentValue);
    }
}
