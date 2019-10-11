package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.projects.ProjectRepository;
import com.epam.mentoring_p1.repomodels.Payment;
import com.epam.mentoring_p1.repomodels.Project;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ClientPaymentsProviderImpl implements ClientPaymentsProvider {

  private final ProjectRepository projectRepository;

  public ClientPaymentsProviderImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public BigDecimal getClientPaymentsSum(String projectId, LocalDate fromDate, LocalDate toDate) {
    Project project = projectRepository
        .findById(Long.valueOf(projectId))
        .orElseThrow(() -> new ProjectNotFoundException("Project cannot be found"));

    return project.getPayments()
        .stream()
        .filter(payment -> payment.getPaymentDate().isBefore(toDate)
            && payment.getPaymentDate().isAfter(fromDate))
        .map(Payment::getPaymentAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
