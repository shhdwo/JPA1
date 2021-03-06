package com.capgemini.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.DepartmentEntity;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity, Long> {
	
}
