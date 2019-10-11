package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ContractDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeePaymentsProviderImpl implements EmployeePaymentsProvider {

  @Override
  public BigDecimal getEmployeesPayments(List<ContractDTO> contractList) {
    return contractList.stream()
        .map(this::calculateContractValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calculateContractValue(ContractDTO contract) {
    BigDecimal numberOfMonths = calculateMonthsBetweenDates(contract.getEndDate(), contract.getStartDate());
    return contract.getSalary().multiply(numberOfMonths);
  }

  private BigDecimal calculateMonthsBetweenDates(LocalDate firstDate, LocalDate secondDate) {
    return new BigDecimal(secondDate.getMonthValue() - firstDate.getMonthValue());
  }
}
