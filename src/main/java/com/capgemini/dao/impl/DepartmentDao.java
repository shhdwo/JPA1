package com.capgemini.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.DepartmentEntity;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity, Long> {
	
	@Modifying
	@Query("update DepartmentEntity d set d.name = ?1 where d.id = ?2")
	public void updateName(String name, Long id);
	
}
