package com.capgemini.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_project")
public class EmployeeProjectEntity extends AbstractEntity {
	
	@ManyToOne
	private EmployeeEntity employee;
	
	@ManyToOne
	private ProjectEntity project;
	
	@ManyToOne
	private RoleEntity role;
	
	@Column(nullable = false)
	private BigDecimal salary;
	
	@Column(nullable = false)
	private Date start_date;
	
	@Column(nullable = true)
	private Date end_date;

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}
	

}
