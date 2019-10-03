package com.epam.mentoring_p1.dtomodels;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class ReportDTO {

  private final BigDecimal reportValue;
  private final LocalDate fromDate;
  private final LocalDate toDate;

  public ReportDTO(BigDecimal reportValue, LocalDate fromDate, LocalDate toDate) {
    this.reportValue = reportValue;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  public BigDecimal getReportValue() {
    return new BigDecimal(reportValue.toString());
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }
}
