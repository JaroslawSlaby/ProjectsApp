package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ContractDTO;

import java.time.LocalDate;
import java.util.List;

interface ProjectContractsProvider {

  List<ContractDTO> getContractsList(String projectId, LocalDate fromDate, LocalDate toDate);
}
