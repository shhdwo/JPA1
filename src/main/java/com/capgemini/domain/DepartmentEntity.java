package com.capgemini.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class DepartmentEntity extends AbstractEntity {

	@Column(nullable = false)
	private String name;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "email", column = @Column(name = "email")),
		@AttributeOverride(name = "phone", column = @Column(name = "phone")),
		@AttributeOverride(name = "mobile", column = @Column(name = "mobile"))
	})
	private ContactData contactData; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
