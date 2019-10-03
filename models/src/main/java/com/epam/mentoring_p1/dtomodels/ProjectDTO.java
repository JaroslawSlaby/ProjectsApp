package com.epam.mentoring_p1.dtomodels;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public final class ProjectDTO implements Serializable {

  private Long projectId;
  @NotNull(message = "Project name cannot be null")
  private String projectName;
  @NotNull(message = "Manager cannot be null")
  private EmployeeDTO manager;
  private Set<ContractDTO> developers;

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public EmployeeDTO getManager() {
    return manager;
  }

  public void setManager(EmployeeDTO manager) {
    this.manager = manager;
  }

  public Set<ContractDTO> getDevelopers() {
    return developers;
  }

  public void setDevelopers(Set<ContractDTO> developers) {
    this.developers = developers;
  }
}
