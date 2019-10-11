package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ReportDTO;

import java.time.LocalDate;

public interface ProjectProfitability {

  ReportDTO generateReport(String projectId, LocalDate fromDate, LocalDate toDate);
}
