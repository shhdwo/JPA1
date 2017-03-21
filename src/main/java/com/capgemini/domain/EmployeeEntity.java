package com.capgemini.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne(fetch=FetchType.LAZY)
	private DepartmentEntity department;
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmployeeProjectEntity> projectAssignment;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "email", column = @Column(name = "email")),
		@AttributeOverride(name = "phone", column = @Column(name = "phone")),
		@AttributeOverride(name = "mobile", column = @Column(name = "mobile"))
	})
	private ContactData contactData; 

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
