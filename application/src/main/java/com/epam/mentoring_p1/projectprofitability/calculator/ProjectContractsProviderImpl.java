package com.epam.mentoring_p1.projectprofitability.calculator;

import com.epam.mentoring_p1.dtomodels.ContractDTO;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.projects.ProjectRepository;
import com.epam.mentoring_p1.repomodels.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectContractsProviderImpl implements ProjectContractsProvider {

  private final ProjectRepository projectRepository;
  private final ModelMapper mapper;

  public ProjectContractsProviderImpl(ProjectRepository projectRepository, ModelMapper mapper) {
    this.projectRepository = projectRepository;
    this.mapper = mapper;
  }

  @Override
  public List<ContractDTO> getContractsList(String projectId, LocalDate fromDate, LocalDate toDate) {
    Project project = projectRepository.findById(Long.valueOf(projectId)).orElseThrow(() -> new ProjectNotFoundException(""));
    return project.getDevelopers()
        .stream()
        .filter(contract -> contract.getStartDate().isBefore(toDate))
        .filter(contract -> contract.getEndDate().isAfter(fromDate))
        .peek(contract -> contract.setStartDate(fromDate))
        .map(contract -> mapper.map(contract, ContractDTO.class))
        .collect(Collectors.toList());
  }
}
