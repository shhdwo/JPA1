package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

public interface EmployeeService {
	
	public EmployeeEntity findById(Long id);
	
	public void delete(EmployeeEntity employee);
	
	public EmployeeEntity add(EmployeeEntity employee, DepartmentEntity department);
	
	public List<EmployeeEntity> findAll();
	
	public List<EmployeeEntity> findByDepartment(DepartmentEntity department);
	
	public void update(EmployeeEntity employee);
	
	public void updateDepartment(EmployeeEntity employee, DepartmentEntity department);

}
