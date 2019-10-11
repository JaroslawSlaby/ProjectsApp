package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ContractDTO;
import com.epam.mentoring_p1.dtomodels.ReportDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectProfitabilityImpl implements ProjectProfitability {

  private final ClientPaymentsProvider clientPaymentsProvider;
  private final ProjectContractsProvider projectContractsProvider;
  private final EmployeePaymentsProvider employeePaymentsProvider;

  public ProjectProfitabilityImpl(ClientPaymentsProvider clientPaymentsProvider,
                                  ProjectContractsProvider projectContractsProvider,
                                  EmployeePaymentsProvider employeePaymentsProvider) {
    this.clientPaymentsProvider = clientPaymentsProvider;
    this.projectContractsProvider = projectContractsProvider;
    this.employeePaymentsProvider = employeePaymentsProvider;
  }

  @Override
  public ReportDTO generateReport(String projectId, LocalDate fromDate, LocalDate toDate) {
    BigDecimal clientPaymentsValue = clientPaymentsProvider.getClientPaymentsSum(projectId, fromDate, toDate);
    List<ContractDTO> contractsList = projectContractsProvider.getContractsList(projectId, fromDate, toDate);
    BigDecimal employeePayments = employeePaymentsProvider.getEmployeesPayments(contractsList);

    return new ReportDTO(clientPaymentsValue.subtract(employeePayments), fromDate, toDate);
  }
}
