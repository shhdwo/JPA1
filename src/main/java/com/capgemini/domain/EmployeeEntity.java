package com.capgemini.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class EmployeeEntity extends AbstractEntity {
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private long pesel;
	
	@Column(nullable=false)
	private Date birthdate;

	@ManyToOne(cascade=CascadeType.ALL)
	private DepartmentEntity department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getPesel() {
		return pesel;
	}

	public void setPesel(long pesel) {
		this.pesel = pesel;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}
	
	
}
