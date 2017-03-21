package com.capgemini.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.ProjectTypeEntity;

@Repository
public interface ProjectTypeDao extends JpaRepository<ProjectTypeEntity, Long>{

}
