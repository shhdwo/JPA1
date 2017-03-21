package com.capgemini.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectEntity extends AbstractEntity {

	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	private EmployeeEntity manager;
	
	@ManyToOne
	private ProjectTypeEntity type;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmployeeProjectEntity> employeeAssignment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

	public ProjectTypeEntity getType() {
		return type;
	}

	public void setType(ProjectTypeEntity type) {
		this.type = type;
	}
	
	
	
}
