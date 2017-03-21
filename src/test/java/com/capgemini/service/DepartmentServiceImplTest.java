package com.capgemini.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.DepartmentDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.exception.DepartmentContainEmployeesException;
import com.capgemini.exception.DepartmentIdAlreadyExistsException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceImplTest {
	
	@Autowired
	private DepartmentService service;
	
	@Autowired
	private DepartmentDao dao;
	
	@Autowired
	private EmployeeService EmployeeService;
	
	@Test
	public void shouldFindDepartmentById() {
		// given		
		final long departmentId = 1L;
		
		// when		
		DepartmentEntity department = service.findById(departmentId);
		
		// then	
		Assert.assertEquals("Finance", department.getName());
	}
	
	@Test
	public void shouldDeleteDepartment() {
		// given	
		DepartmentEntity department = service.findById(3L);
		int sizeBefore = service.findAll().size();
		int employeeSizeBefore = EmployeeService.findAll().size();
		
		// when		
		service.delete(department);
		int sizeAfter = service.findAll().size();
		int employeeSizeAfter = EmployeeService.findAll().size();
		
		// then		
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
		Assert.assertEquals(employeeSizeBefore, employeeSizeAfter);
	}
	
	@Test(expected = DepartmentContainEmployeesException.class)
	public void shouldThrowExceptionWhenDeleteDepartmentWithEmployees() {
		// given	
		DepartmentEntity department = service.findById(1L);
		
		// when	
		service.delete(department);
	}
	
	@Test
	public void shouldSaveDepartment() {
		// given		
		int departmentsActualSize = service.findAll().size();
		DepartmentEntity department = new DepartmentEntity();
		department.setName("Entertainment");
		
		// when	
		service.add(department);
		DepartmentEntity foundDepartmentById = service.findById(departmentsActualSize + 1L);
		
		// then	
		Assert.assertEquals(department.getName(), foundDepartmentById.getName());
		Assert.assertEquals(new Long(departmentsActualSize + 1L), foundDepartmentById.getId());
		Assert.assertEquals(1, foundDepartmentById.getVersion());
	}
	
	@Test(expected = DepartmentIdAlreadyExistsException.class)
	public void shouldThrowExceptionWhenSaveDepartmentWithExistingId() {
		// given	
		DepartmentEntity department = new DepartmentEntity();
		department.setName("Entertainment");
		department.setId(1L);
		
		// when	
		service.add(department);
	}
	
	@Test
	public void shouldUpdateDepartmentName() {
		// given	
		final long departmentId = 1L;
		DepartmentEntity department = service.findById(departmentId);
		int version = department.getVersion();
		department.setName("Health");
		
		// when	
		service.updateName(department);
		dao.flush();
		DepartmentEntity updatedDepartment = service.findById(departmentId);
		
		// then	
		Assert.assertEquals(department.getName(), updatedDepartment.getName());
		Assert.assertEquals(version + 1, updatedDepartment.getVersion());
	}

}
