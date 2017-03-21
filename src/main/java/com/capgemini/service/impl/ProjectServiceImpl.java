package com.capgemini.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.impl.EmployeeProjectDao;
import com.capgemini.dao.impl.ProjectDao;
import com.capgemini.domain.AbstractEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.domain.ProjectTypeEntity;
import com.capgemini.domain.RoleEntity;
import com.capgemini.exception.DoubleActualProjectAssignmentException;
import com.capgemini.exception.IdNotNullException;
import com.capgemini.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private EmployeeProjectDao employeeProjectDao;

	@Override
	public ProjectEntity add(ProjectEntity project, ProjectTypeEntity type, EmployeeEntity manager) {
		project.setType(type);
		project.setManager(manager);
		return projectDao.save(project);
	}

	@Override
	public void delete(ProjectEntity project) {
		projectDao.delete(project);
	}

	@Override
	public void updateName(ProjectEntity project) {
		checkIdExistance(project);
		ProjectEntity foundProject = projectDao.findOne(project.getId());
		foundProject.setName(project.getName());
	}


	@Override
	public void updateManager(ProjectEntity project) {
		checkIdExistance(project);
		ProjectEntity foundProject = projectDao.findOne(project.getId());
		foundProject.setManager(project.getManager());
	}

	@Override
	public void updateType(ProjectEntity project) {
		checkIdExistance(project);
		ProjectEntity foundProject = projectDao.findOne(project.getId());
		foundProject.setType(project.getType());
	}

	@Override
	public List<EmployeeEntity> findActualEmployees(ProjectEntity project) {
		return employeeProjectDao.findActualEmployees(project);
	}

	@Override
	public EmployeeProjectEntity assignEmployee(EmployeeEntity employee, ProjectEntity project, RoleEntity role, BigDecimal salary) {
		validate(employee, project);	
		EmployeeProjectEntity projectAssignment = new EmployeeProjectEntity();
		projectAssignment.setEmployee(employee);
		projectAssignment.setProject(project);
		projectAssignment.setRole(role);
		projectAssignment.setSalary(salary);
		projectAssignment.setStartDate(new Date());
		return employeeProjectDao.save(projectAssignment);
	}

	@Override
	public void withdrawEmployee(EmployeeProjectEntity projectAssignment) {
		EmployeeProjectEntity assignment = employeeProjectDao.findOne(projectAssignment.getId());
		assignment.setEndDate(new Date());
	}
	
	@Override
	public boolean isManager(EmployeeEntity employee) {
		int size = projectDao.findByManager_Id(employee.getId()).size();
		if (size == 0) {
			return false;
		}
		else return true;
	}
	
	private void checkIdExistance(AbstractEntity entity) {
		if (entity.getId() == null) {
			throw new IdNotNullException("Entity without id cannot be updated");
		}
	}
	
	private void validate(EmployeeEntity employee, ProjectEntity project) {
		List<EmployeeEntity> projectActualEmployees = employeeProjectDao.findActualEmployees(project);
		for (EmployeeEntity e : projectActualEmployees) {
			if (e.getId() == employee.getId()) {
				throw new DoubleActualProjectAssignmentException("Unable to assing employee to project in which he actually works");
			}
		}
	}

}
