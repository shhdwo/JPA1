package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService service;
	
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
		DepartmentEntity department = service.findById(1L);
		List<EmployeeEntity> employeesInDepartment = EmployeeService.findByDepartment(department);
		int sizeBefore = service.findAll().size();
		int employeeSizeBefore = EmployeeService.findAll().size();
		// when
		service.delete(department);
		int sizeAfter = service.findAll().size();
		int employeeSizeAfter = EmployeeService.findAll().size();
		// then
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
		Assert.assertEquals(employeeSizeBefore, employeeSizeAfter);
		Assert.assertNull(employeesInDepartment.get(0).getDepartment());
	}
	
	@Test
	public void shouldSaveDepartment() {
		// given
		DepartmentEntity department = new DepartmentEntity();
		department.setName("Entertainment");
		// when
		service.add(department);
		DepartmentEntity foundDepartmentById = service.findById(3L);
		// then
		Assert.assertEquals(department.getName(), foundDepartmentById.getName());
		Assert.assertEquals(new Long(3), foundDepartmentById.getId());
		Assert.assertEquals(1, foundDepartmentById.getVersion());
	}
	
	@Test
	public void shouldUpdateDepartmentName() {
		// given
		final long departmentId = 1L;
		DepartmentEntity department = service.findById(departmentId);
		department.setName("Health");
		// when
		service.updateName(department);
		DepartmentEntity updatedDepartment = service.findById(departmentId);
		// then
		Assert.assertEquals(department.getName(), updatedDepartment.getName());
		Assert.assertEquals(2, updatedDepartment.getVersion());
	}

}
