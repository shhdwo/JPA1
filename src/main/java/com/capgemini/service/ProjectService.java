package com.capgemini.service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.domain.ProjectTypeEntity;
import com.capgemini.domain.RoleEntity;

public interface ProjectService {
	
	public ProjectEntity add(ProjectEntity project, ProjectTypeEntity type, EmployeeEntity manager);
	
	public void delete(ProjectEntity project);
	
	public boolean isManager(EmployeeEntity employee);
	
	public void updateName(ProjectEntity project);
	
	public void updateManager(ProjectEntity project);
	
	public void updateType(ProjectEntity project);
	
	public List<EmployeeEntity> findActualEmployees(ProjectEntity project);
	
	public EmployeeProjectEntity assignEmployee(EmployeeEntity employee, ProjectEntity project, RoleEntity role, BigDecimal salary);
	
	public void withdrawEmployee(EmployeeProjectEntity projectAssignment);


}
