package com.epam.mentoring_p1.projectprofitability;

import com.epam.mentoring_p1.dtomodels.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

interface ClientPaymentsSupplier {

  List<PaymentDTO> getPaymentList(String projectId, LocalDate fromDate, LocalDate toDate);
}
