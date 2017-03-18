package com.capgemini.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {
	
	public List<EmployeeEntity> findByDepartment_Id(Long id);
	
	@Modifying
	@Query("update EmployeeEntity e set e.name = :name, e.surname = :surname, e.birthdate = :birthdate, e.pesel = :pesel"
			+ " where e.id = :id")
	public void update(@Param("id") Long id,
					   @Param("name") String name,
					   @Param("surname") String surname,
					   @Param("birthdate") Date birthdate,
					   @Param("pesel") Long pesel);
	
	@Modifying //TODO nie dziala
	@Query("update EmployeeEntity e set e.department = ?1 where e.id = ?2")
	public void updateDepartment(DepartmentEntity department, Long id);

}
