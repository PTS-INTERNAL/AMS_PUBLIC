package com.ams.model;

public class CompanyPermision {
	
	private CompanyModel company;
	private String valueCheck;
	private String id;
	private UserModel user;
	
	public CompanyPermision()
	{
		this.company = new CompanyModel();
		this.user = new UserModel();
	}
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public CompanyModel getCompany() {
		return company;
	}

	public void setCompany(CompanyModel company) {
		this.company = company;
	}

	public String getValueCheck() {
		return valueCheck;
	}

	public void setValueCheck(String valueCheck) {
		this.valueCheck = valueCheck;
	}

}
