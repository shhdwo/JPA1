package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.impl.DepartmentDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.exception.DepartmentContainEmployeesException;
import com.capgemini.exception.DepartmentIdAlreadyExistsException;
import com.capgemini.service.DepartmentService;
import com.capgemini.service.EmployeeService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public DepartmentEntity findById(Long id) {
		return departmentDao.findOne(id);
	}

	@Override
	public void delete(DepartmentEntity department) {
		if (employeeService.findByDepartment(department).size() > 0) {
			throw new DepartmentContainEmployeesException("Department with employees cannot be deleted.");
		}
		else {
			departmentDao.delete(department);
		}
	}

	@Override
	public DepartmentEntity add(DepartmentEntity department) {
		Long departmentID = department.getId();
		if (departmentID == null) {
			return departmentDao.save(department);
		}
		else {
			if (departmentDao.findOne(departmentID) != null) {
				throw new DepartmentIdAlreadyExistsException("Department with this ID already exists.");
			}
			else {
				return departmentDao.save(department);
			}
		}
	}

	@Override
	public List<DepartmentEntity> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public void updateName(DepartmentEntity department) {
		DepartmentEntity foundDepartment = departmentDao.findOne(department.getId());
		foundDepartment.setName(department.getName());
	}

}
