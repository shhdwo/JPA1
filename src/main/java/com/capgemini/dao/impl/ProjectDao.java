package com.capgemini.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.ProjectEntity;

@Repository
public interface ProjectDao extends JpaRepository<ProjectEntity, Long> {
	
	public List<ProjectEntity> findByManager_Id(Long id);
	
}
