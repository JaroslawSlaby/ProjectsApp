package com.epam.mentoring_p1.projectprofitability;

import com.epam.mentoring_p1.dtomodels.ContractDTO;

import java.util.List;

interface ProjectEmployeesSupplier {

  List<ContractDTO> getContractsList(String projectId);
}
