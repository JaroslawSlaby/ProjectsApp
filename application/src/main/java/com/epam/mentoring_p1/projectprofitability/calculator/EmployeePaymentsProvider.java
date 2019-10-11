package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ContractDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

interface EmployeePaymentsProvider {

  BigDecimal getEmployeesPayments(List<ContractDTO> contractList);
}
