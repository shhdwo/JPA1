package com.capgemini.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
				+ "(SELECT ep.project "
				+ "FROM EmployeeProjectEntity ep "
				+ "WHERE "
					+ "ep.start_date < :date "
					+ "AND (ep.end_date > :date OR ep.end_date IS NULL) "
				+ "GROUP BY ep.project "
				+ "HAVING count(ep) > :n)")
	public int countProjectsWithEmployeesMoreThan(@Param("n") long n, @Param("date") Date date);

	@Query("SELECT ep.project "
			+ "FROM EmployeeProjectEntity ep "
			+ "WHERE "
				+ "ep.start_date < :date "
				+ "AND (ep.end_date > :date OR ep.end_date IS NULL) "
			+ "GROUP BY ep.project "
			+ "HAVING count(ep) > :n")
	public List<ProjectEntity> findProjectsWithEmployeesMoreThan(@Param("n") long n, @Param("date") Date date);
	
	@Query("SELECT e "
			+ "FROM EmployeeEntity e "
			+ "WHERE e IN "
				+ "(SELECT ep.employee "
				+ "FROM EmployeeProjectEntity ep "
				+ "WHERE ep.project = :project "
				+ "GROUP BY ep.employee "
				+ "HAVING SUM("
					+ "CASE "
					+ "WHEN ep.end_date is null THEN (current_timestamp - ep.start_date) "
					+ "ELSE (ep.end_date - ep.start_date) "
					+ "END)"
				+ " > :n *30D*24D*60D*60D*1000D)")
	public List<EmployeeEntity> findEmployeesWorkingInProjectMoreThan(@Param("n") double n, @Param("project") ProjectEntity project);

}
