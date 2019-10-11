package com.epam.mentoring_p1.projectprofitability.calculator;

import java.math.BigDecimal;
import java.time.LocalDate;

interface ClientPaymentsProvider {

  BigDecimal getClientPaymentsSum(String projectId, LocalDate fromDate, LocalDate toDate);
}
