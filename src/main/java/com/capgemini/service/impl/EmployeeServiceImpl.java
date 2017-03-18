package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.impl.EmployeeDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.service.EmployeeService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

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
		employeeDao.delete(employee);
	}

	@Override //TODO osobny serwis na podlaczanie department?
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
		employeeDao.update(employee.getId(), employee.getName(), employee.getSurname(), employee.getBirthdate(), employee.getPesel());
	}
	

}
