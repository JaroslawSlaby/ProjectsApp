package com.epam.mentoring_p1.dtomodels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class ContractDTO implements Serializable {

  private EmployeeDTO employee;
  private LocalDate startDate;
  private LocalDate endDate;
  private BigDecimal salary;

  public EmployeeDTO getEmployee() {
    return employee;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setEmployee(EmployeeDTO employee) {
    this.employee = employee;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }
}
