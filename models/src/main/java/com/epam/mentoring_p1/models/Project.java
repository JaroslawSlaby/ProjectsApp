package com.epam.mentoring_p1.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long projectId;

    private String projectName;
    @OneToOne
    private Employee manager;
    @OneToMany
    private Set<Employee> developers;
    @OneToMany
    private Set<Payment> payments;

    public boolean addDeveloper(Employee developer) {
        return developers.add(developer);
    }

    public boolean makePayment(Payment payment) {
        return payment.isValidPayment() && payments.add(payment);
    }

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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Employee> developers) {
        this.developers = developers;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
