package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.impl.EmployeeDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exception.ManagerDeletionException;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.ProjectService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ProjectService projectService;

	@Override
	public EmployeeEntity findById(Long id) {
		return employeeDao.findOne(id);
	}

	@Override
	public List<EmployeeEntity> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public List<EmployeeEntity> findByDepartment(DepartmentEntity department) {
		return employeeDao.findByDepartment_Id(department.getId());
	}

	@Override
	public void delete(EmployeeEntity employee) {
		if (projectService.isManager(employee)) {
			throw new ManagerDeletionException("Deletion of manager is forbidden.");
		}
		else {
			employeeDao.delete(employee);
		}
	}

	@Override
	public EmployeeEntity add(EmployeeEntity employee, DepartmentEntity department) {
		employee.setDepartment(department);
		return employeeDao.save(employee);
	}
	
	@Override
	public void updateDepartment(EmployeeEntity employee, DepartmentEntity department) {
		EmployeeEntity foundEmployee = employeeDao.findOne(employee.getId());
		foundEmployee.setDepartment(department);
	}

	@Override
	public void update(EmployeeEntity employee) {
		EmployeeEntity foundEmployee = employeeDao.findOne(employee.getId());
		foundEmployee.setName(employee.getName());
		foundEmployee.setSurname(employee.getSurname());
		foundEmployee.setBirthdate(employee.getBirthdate());
		foundEmployee.setPesel(employee.getPesel());
	}

	@Override
	public List<EmployeeEntity> findByNameAndSurname(String name, String surname) {
		if (name == null || name.isEmpty()) {
			return employeeDao.findBySurnameContaining(surname);
		}
		else if (surname == null || surname.isEmpty()) {
			return employeeDao.findByNameContaining(name);
		}
		return employeeDao.findByNameContainingAndSurnameContaining(name, surname);
	}
	

}
