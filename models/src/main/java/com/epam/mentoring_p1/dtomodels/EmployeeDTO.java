package com.epam.mentoring_p1.dtomodels;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public final class EmployeeDTO implements Serializable {

  private Long id;
  @NotNull(message = "First cannot be null")
  private String firstName;
  @NotNull(message = "Last name cannot be null")
  private String lastName;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @NotNull(message = "Date of birth cannot be null")
  @Past
  private Date dateOfBirth;
  @NotNull(message = "Address cannot be null")
  private AddressDTO address;
  @NotNull(message = "Salary cannot be null")
  private BigDecimal salary;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public AddressDTO getAddress() {
    return address;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setAddress(AddressDTO address) {
    this.address = address;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }
}
