package com.capgemini.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.impl.EmployeeDao;
import com.capgemini.dao.impl.EmployeeProjectDao;
import com.capgemini.dao.impl.ProjectDao;
import com.capgemini.dao.impl.ProjectTypeDao;
import com.capgemini.dao.impl.RoleDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.domain.ProjectTypeEntity;
import com.capgemini.domain.RoleEntity;
import com.capgemini.exception.DoubleActualProjectAssignmentException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {
	
	@Autowired
	private ProjectService service;
	
	@Autowired
	private ProjectDao dao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ProjectTypeDao projectTypeDao;
	
	@Autowired
	private EmployeeProjectDao employeeProjectDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void shouldSaveNewProject() {
		// given
		ProjectEntity project = giveProject();
		ProjectTypeEntity type = projectTypeDao.findOne(1L);
		EmployeeEntity manager = employeeDao.findOne(2L);
		
		// when
		ProjectEntity newProject = service.add(project, type, manager);
		
		// then
		Assert.assertEquals(type.getName(), newProject.getType().getName());
		Assert.assertEquals(manager.getId(), newProject.getManager().getId());
		Assert.assertEquals(project.getName(), newProject.getName());
		
	}
	
	@Test
	public void shouldDeleteProjectAndHisEmployeeAssignments() {
		// given
		ProjectEntity project = dao.findOne(1L);
		int sizeBefore = dao.findAll().size();
		
		// when
		service.delete(project);
		int sizeAfter = dao.findAll().size();
		
		// then
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
		Assert.assertEquals(0, employeeProjectDao.findByProject_Id(1L).size());
	}
	
	@Test
	public void shouldReturnTrueWhenManager() {
		// given
		EmployeeEntity manager = employeeDao.findOne(2L);
		
		// when		
		boolean result = service.isManager(manager);
		
		// then
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldReturnFalseWhenNotManager() {
		// given		
		EmployeeEntity notManager = employeeDao.findOne(1L);
		
		// when	
		boolean result = service.isManager(notManager);
		
		// then	
		Assert.assertFalse(result);
	}
	
	@Test
	public void shouldUpdateName() {
		// given
		ProjectEntity project = giveProject();
		project.setId(1L);
		int version = dao.findOne(1L).getVersion();
		
		// when
		service.updateName(project);
		dao.flush();
		ProjectEntity updatedProject = dao.findOne(1L);
		
		// then
		Assert.assertEquals(project.getName(), updatedProject.getName());
		Assert.assertEquals(version + 1, updatedProject.getVersion());
	}
	
	@Test(expected = OptimisticLockException.class) 
	public void shouldThrowOptimisticLockExceptionWhenUpdateNameWithWrongVersion() {
		// given
		ProjectEntity first = dao.findOne(1L);
		em.detach(first);
		ProjectEntity second = dao.findOne(1L);

		// when
		second.setName("Second but first to edit");
		em.flush();
		first.setName("First but to late to edit");
		em.merge(first);
		
	}
	
	@Test
	public void shouldSelectActualEmployeesOfProject() {
		// given
		ProjectEntity project = dao.findOne(3L);
		
		// when
		List<EmployeeEntity> actualEmployeesFound = employeeProjectDao.findActualEmployees(project);
		
		// then
		Assert.assertEquals(2, actualEmployeesFound.size());
	}
	
	@Test
	public void shouldAssignEmployeeToProject() {
		// given
		ProjectEntity project = dao.findOne(1L);
		EmployeeEntity employee = employeeDao.findOne(1L);
		RoleEntity role = roleDao.findOne(4L);
		BigDecimal salary = BigDecimal.valueOf(120.75F);
		
		// when
		EmployeeProjectEntity assignment = service.assignEmployee(employee, project, role, salary);
		
		// then
		Assert.assertNull(assignment.getEndDate());
		Assert.assertNotNull(assignment.getStartDate());
		Assert.assertEquals(salary, assignment.getSalary());
		Assert.assertEquals(project.getId(), assignment.getProject().getId());
		Assert.assertEquals(employee.getId(), assignment.getEmployee().getId());
	}
	
	@Test(expected = DoubleActualProjectAssignmentException.class)
	public void shouldThrowExceptionWhenAssignEmployeeToProjectThatHeAlreadyWorksInActually() {
		// given
		ProjectEntity project = dao.findOne(3L);
		EmployeeEntity employee = employeeDao.findOne(2L);
		RoleEntity role = roleDao.findOne(4L);
		BigDecimal salary = BigDecimal.valueOf(120.75F);
		
		// when
		service.assignEmployee(employee, project, role, salary);
	}
	
	
	@Test
	public void shouldWithdrawEmployeeFromProject() {
		// given
		EmployeeProjectEntity projectAssignment = employeeProjectDao.findOne(5L);
		Date endDateBefore = projectAssignment.getEndDate();
		
		// when
		service.withdrawEmployee(projectAssignment);
		Date endDateAfter = projectAssignment.getEndDate();
		
		// then
		Assert.assertNull(endDateBefore);
		Assert.assertNotNull(endDateAfter);
	}
	
	@Test
	public void shouldCountNumberOfProjectsWithMoreThan10Employees() {
		// given when
		int counter = employeeProjectDao.countProjectsWithEmployeesMoreThan(10L);
		
		// then
		Assert.assertEquals(1, counter);
	}
	
	@Test
	public void shouldCountNumberOfProjectsWithMoreThan0Employees() {
		// given when
		int counter = employeeProjectDao.countProjectsWithEmployeesMoreThan(0L);
		
		// then
		Assert.assertEquals(4, counter);
	}
	
	@Test
	public void shouldFindProjectsWithMoreThan10Employees() {
		// given when
		List<ProjectEntity> projectsFound = employeeProjectDao.findProjectsWithEmployeesMoreThan(10L);
				
		// then
		Assert.assertEquals(1, projectsFound.size());
	}
	
	@Test
	public void shouldFindProjectsWithMoreThan0Employees() {
		// given when
		List<ProjectEntity> projectsFound = employeeProjectDao.findProjectsWithEmployeesMoreThan(0L);
				
		// then
		Assert.assertEquals(4, projectsFound.size());
	}
	
	private ProjectEntity giveProject() {
		ProjectEntity project = new ProjectEntity();
		project.setName("ProjectX");
		return project;
	}

}
