package com.capgemini.dao.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.domain.EmployeeEntity;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {
	
	public List<EmployeeEntity> findByDepartment_Id(Long id);
	
	public List<EmployeeEntity> findByNameContainingAndSurnameContaining(String name, String surname);
	
	public List<EmployeeEntity> findByNameContaining(String name);
	
	public List<EmployeeEntity> findBySurnameContaining(String surname);

}
