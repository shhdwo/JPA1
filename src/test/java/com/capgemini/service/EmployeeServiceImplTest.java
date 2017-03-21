package com.capgemini.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.capgemini.dao.impl.EmployeeDao;
import com.capgemini.dao.impl.EmployeeProjectDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exception.ManagerDeletionException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeProjectDao employeeProjectDao;
	
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
	public void shouldDeleteEmployeeAndHisProjectAssignments() {
		// given	
		EmployeeEntity employee = service.findById(1L);
		int sizeBefore = service.findAll().size();
		
		// when	
		service.delete(employee);
		int sizeAfter = service.findAll().size();
		
		// then	
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
		Assert.assertEquals(0, employeeProjectDao.findByEmployee_Id(1L).size());
	}
	
	@Test(expected = ManagerDeletionException.class)
	public void shouldThrowExceptionWhenDeleteEmployeeThatIsManager() {
		// given	
		EmployeeEntity employee = service.findById(2L);
		
		// when	
		service.delete(employee);
		
		// then
	}
	
	@Test
	public void shouldSaveEmployee() {
		// given	
		EmployeeEntity employee = giveEmployee();
		
		// when	
		service.add(employee, departmentService.findById(1L));
		EmployeeEntity foundEmployeeById = service.findById(new Long(service.findAll().size()));
		
		// then	
		Assert.assertEquals(employee.getName(), foundEmployeeById.getName());
		Assert.assertEquals(employee.getSurname(), foundEmployeeById.getSurname());
		Assert.assertEquals(new Long(service.findAll().size()), foundEmployeeById.getId());
		Assert.assertEquals(1, foundEmployeeById.getVersion());
		Assert.assertEquals("Finance", foundEmployeeById.getDepartment().getName());
	}
	
	@Test
	public void shouldUpdateDepartment() {
		// given	
		final long employeeId = 1L;
		EmployeeEntity employee = service.findById(employeeId);
		int version = employee.getVersion();
		String departmentNameBefore = employee.getDepartment().getName();
		DepartmentEntity department = departmentService.findById(1L);
		
		// when	
		service.updateDepartment(employee, department);
		dao.flush();
		String departmentNameAfter = service.findById(employeeId).getDepartment().getName();
		
		// then		
		Assert.assertNotEquals(departmentNameBefore, departmentNameAfter);
		Assert.assertEquals(version + 1, service.findById(employeeId).getVersion());
	}
	
	@Test
	public void shouldUpdateEmployee() {
		// given		
		EmployeeEntity employee = giveEmployee();
		final long employeeId = 1L;
		employee.setId(employeeId);
		int version = service.findById(employeeId).getVersion();
		
		// when	
		service.update(employee);
		dao.flush();
		EmployeeEntity updatedEmployee = service.findById(1L);
		
		// then	
		Assert.assertEquals(employee.getName(), updatedEmployee.getName());
		Assert.assertEquals(employee.getSurname(), updatedEmployee.getSurname());
		Assert.assertEquals(employee.getPesel(), updatedEmployee.getPesel());
		Assert.assertEquals(employee.getBirthdate(), updatedEmployee.getBirthdate());
		Assert.assertEquals(version + 1, updatedEmployee.getVersion());
	}
	
	@Test
	public void shouldFindEmployeeByExistingNameAndSurname() {
		// given	
		String name = "Ryszard";
		String surname = "Riedel";
		
		// when	
		List<EmployeeEntity> foundEmployees = service.findByNameAndSurname(name, surname);
		
		// then	
		Assert.assertEquals(1, foundEmployees.size());
	}
	
	@Test
	public void shouldFindEmployeeByContainedSubstring() {
		// given		
		String name = "sza";
		String surname = "ede";
		
		// when		
		List<EmployeeEntity> foundEmployees = service.findByNameAndSurname(name, surname);
		
		// then
		
		Assert.assertEquals(1, foundEmployees.size());
	}
	
	@Test
	public void shouldNotFindEmployeeByNotExistingNameAndSurname() {
		// given	
		String name = "Stan";
		String surname = "Borys";
		
		// when	
		List<EmployeeEntity> foundEmployees = service.findByNameAndSurname(name, surname);
		
		// then	
		Assert.assertEquals(0, foundEmployees.size());
	}
	
	
	@Test
	public void shouldFindEmployeeByExistingSurname() {
		// given		
		String name = "";
		String surname = "Riedel";
		
		// when	
		List<EmployeeEntity> foundEmployees = service.findByNameAndSurname(name, surname);
		
		// then	
		Assert.assertEquals(1, foundEmployees.size());
	}
	
	
	@Test
	public void shouldNotFindEmployeeByNotExistingSurname() {
		// given	
		String name = "";
		String surname = "Borys";
		
		// when	
		List<EmployeeEntity> foundEmployees = service.findByNameAndSurname(name, surname);
		
		// then	
		Assert.assertEquals(0, foundEmployees.size());
	}
	
	@Test
	public void shouldFindByDepartment() {
		// given
		DepartmentEntity department = departmentService.findById(2L);
		
		// when	
		List<EmployeeEntity> foundByDepartment = service.findByDepartment(department);
		
		// them	
		Assert.assertEquals(2, foundByDepartment.size());
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
