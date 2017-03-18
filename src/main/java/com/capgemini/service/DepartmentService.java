package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.DepartmentEntity;

public interface DepartmentService {
	
	public DepartmentEntity findById(Long id);
	
	public void delete(DepartmentEntity department);
	
	public DepartmentEntity add(DepartmentEntity department);
	
	public void updateName(DepartmentEntity department);
	
	public List<DepartmentEntity> findAll();

}
