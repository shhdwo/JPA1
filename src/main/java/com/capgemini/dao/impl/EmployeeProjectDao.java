package com.capgemini.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.domain.ProjectEntity;

@Repository
public interface EmployeeProjectDao extends JpaRepository<EmployeeProjectEntity, Long> {

	public List<EmployeeProjectEntity> findByEmployee_Id(Long id);
	
	public List<EmployeeProjectEntity> findByProject_Id(Long id);
	
	@Query("select ep.employee from EmployeeProjectEntity ep where ep.project = ?1 and ep.end_date is null")
	public List<EmployeeEntity> findActualEmployees(ProjectEntity project);
	
	@Query("SELECT count(p) "
			+ "FROM ProjectEntity p "
			+ "WHERE p IN "
			+ "(SELECT ep.project FROM EmployeeProjectEntity ep GROUP BY ep.project HAVING count(ep) > ?1)")
	public int countProjectsWithEmployeesMoreThan(long n);
	
	@Query("SELECT ep.project FROM EmployeeProjectEntity ep GROUP BY ep.project HAVING count(ep) > ?1")
	public List<ProjectEntity> findProjectsWithEmployeesMoreThan(long n);

}
