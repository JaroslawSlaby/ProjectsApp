package com.epam.mentoring_p1.projectprofitability;

import com.epam.mentoring_p1.dtomodels.ContractDTO;
import com.epam.mentoring_p1.dtomodels.PaymentDTO;
import com.epam.mentoring_p1.dtomodels.ReportDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
public class ProjectProfitabilityImpl implements ProjectProfitability {

  private final ClientPaymentsSupplier clientPaymentsSupplier;
  private final ProjectEmployeesSupplier projectEmployeesSupplier;
  private final EmployeePaymentsSupplier employeePaymentsSupplier;

  public ProjectProfitabilityImpl(ClientPaymentsSupplier clientPaymentsSupplier,
                                  ProjectEmployeesSupplier projectEmployeesSupplier,
                                  EmployeePaymentsSupplier employeePaymentsSupplier) {
    this.clientPaymentsSupplier = clientPaymentsSupplier;
    this.projectEmployeesSupplier = projectEmployeesSupplier;
    this.employeePaymentsSupplier = employeePaymentsSupplier;
  }

  @Override
  public ReportDTO generateReport(String projectId, LocalDate fromDate, LocalDate toDate) {
    List<PaymentDTO> paymentList = clientPaymentsSupplier.getPaymentList(projectId, fromDate, toDate);
    List<ContractDTO> contractsList = projectEmployeesSupplier.getContractsList(projectId);

    BigDecimal paymentsSum = paymentList
        .stream()
        .map(PaymentDTO::getPaymentValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal employeesSalary = contractsList.
        stream()
        .filter(contractDTO -> contractDTO.getStartDate().isAfter(fromDate) && contractDTO.getEndDate().isBefore(toDate))
        .map(ContractDTO::getSalary)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return new ReportDTO(paymentsSum.subtract(employeesSalary), fromDate, toDate);
  }
}
