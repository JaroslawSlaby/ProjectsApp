package com.epam.mentoring_p1.projectpayments;

import com.epam.mentoring_p1.exceptions.InvalidPaymentException;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.models.Payment;
import com.epam.mentoring_p1.models.Project;
import com.epam.mentoring_p1.projects.ProjectRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectPaymentsOperationsImpl implements ProjectPaymentsOperations {

    private final ProjectRepository projectRepository;

    public ProjectPaymentsOperationsImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Set<Payment> getProjectPayments(Long projectId) {
        Project project = getProject(projectId);
        return project.getPayments();
    }

    @Override
    public Payment makePayment(Long projectId, String paymentValue) {
        BigDecimal paymentAmount = new BigDecimal(paymentValue);

        if(paymentAmount.doubleValue() < 0d)
            throw new InvalidPaymentException("Payment value cannot be less than 0: " + paymentValue);

        Payment payment = new Payment(LocalDate.now(), paymentAmount);
        Project project = getProject(projectId);
        project.makePayment(payment);
        projectRepository.save(project);
        return payment;
    }

    private Project getProject(Long projectId) {
        Optional<Project> projectFromDB = getProjectFromDB(projectId);
        return projectFromDB.orElseThrow(() -> new ProjectNotFoundException("Project cannot be found: " + projectId));
    }

    private Optional<Project> getProjectFromDB(Long projectId) {
        return projectRepository.findById(projectId);
    }
}
