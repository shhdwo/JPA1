package com.capgemini.domain;

import javax.persistence.Embeddable;

@Embeddable
public class ContactData {
	
	private String email;
	private String phone;
	private String mobile;
	
	public ContactData() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
