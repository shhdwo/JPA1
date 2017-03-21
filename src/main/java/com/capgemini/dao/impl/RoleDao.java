package com.capgemini.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.RoleEntity;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long> {

}
