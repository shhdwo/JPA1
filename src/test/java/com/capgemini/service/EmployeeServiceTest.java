package com.capgemini.service;

import java.util.Date;

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
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void shouldFindEmployeeById() {
		// given
		final long employeeId = 1L;
		// when
		EmployeeEntity employee = service.findById(employeeId);
		// then
		Assert.assertEquals("Czeslaw", employee.getName());
		Assert.assertEquals("Automotive", employee.getDepartment().getName());
	}
	
	@Test
	public void shouldDeleteEmployee() {
		// given
		EmployeeEntity employee = service.findById(1L);
		int sizeBefore = service.findAll().size();
		// when
		service.delete(employee);
		int sizeAfter = service.findAll().size();
		// then
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}
	
	@Test
	public void shouldSaveEmployee() {
		// given
		EmployeeEntity employee = giveEmployee();
		// when
		service.add(employee, departmentService.findById(1L));
		EmployeeEntity foundEmployeeById = service.findById(3L);
		// then
		Assert.assertEquals(employee.getName(), foundEmployeeById.getName());
		Assert.assertEquals(employee.getSurname(), foundEmployeeById.getSurname());
		Assert.assertEquals(new Long(service.findAll().size()), foundEmployeeById.getId());
		Assert.assertEquals(1, foundEmployeeById.getVersion());
		Assert.assertEquals("Finance", foundEmployeeById.getDepartment().getName());
	}
	
	@Test
	public void shouldUpdateEmployeeName() {
		// given
		final long employeeId = 1L;
		EmployeeEntity employee = service.findById(employeeId);
		employee.setName("Andrzej");
		// when
		service.update(employee);
		EmployeeEntity savedEmployee = service.findById(employeeId);
		// then
		Assert.assertEquals(employee.getName(), savedEmployee.getName());
	}
	
	@Test
	public void shouldUpdateDepartment() {
		// given
		final long employeeId = 1L;
		EmployeeEntity employee = service.findById(employeeId);
		String departmentNameBefore = employee.getDepartment().getName();
		DepartmentEntity department = departmentService.findById(1L);
		// when
		service.updateDepartment(employee, department);
		String departmentNameAfter = service.findById(employeeId).getDepartment().getName();
		// then
		Assert.assertNotEquals(departmentNameBefore, departmentNameAfter);
	}
	
	@Test
	public void shouldUpdateEmployee() {
		// given
		EmployeeEntity employee = giveEmployee();
		employee.setId(1L);
		// when
		service.update(employee);
		EmployeeEntity updatedEmployee = service.findById(1L);
		// then
		Assert.assertEquals(employee.getName(), updatedEmployee.getName());
		Assert.assertEquals(employee.getSurname(), updatedEmployee.getSurname());
		Assert.assertEquals(employee.getPesel(), updatedEmployee.getPesel());
		Assert.assertEquals(employee.getBirthdate(), updatedEmployee.getBirthdate());
	}
	
	
	private EmployeeEntity giveEmployee() {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Jan");
		employee.setSurname("Borysewicz");
		employee.setPesel(22211133344L);
		employee.setBirthdate(new Date());
		return employee;
	}

}
