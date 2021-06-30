package com.ams.model;

public class RentAsset {
	private String rent_cd;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRent_cd() {
		return rent_cd;
	}

	public void setRent_cd(String rent_cd) {
		this.rent_cd = rent_cd;
	}

	private CompanyModel company_master;
	public CompanyModel getCompany_master() {
		return company_master;
	}

	public void setCompany_master(CompanyModel company_master) {
		this.company_master = company_master;
	}

	public CompanyModel getComany_client() {
		return comany_client;
	}

	public void setComany_client(CompanyModel comany_client) {
		this.comany_client = comany_client;
	}

	public Department getDept_master() {
		return dept_master;
	}

	public void setDept_master(Department dept_master) {
		this.dept_master = dept_master;
	}

	public AssetGeneralModel getAsset() {
		return asset;
	}

	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}

	public String getDate_end_schedual() {
		return date_end_schedual;
	}

	public void setDate_end_schedual(String date_end_schedual) {
		this.date_end_schedual = date_end_schedual;
	}

	public String getDate_end_real() {
		return date_end_real;
	}

	public void setDate_end_real(String date_end_real) {
		this.date_end_real = date_end_real;
	}

	private CompanyModel comany_client;
	private Department dept_master;
	private AssetGeneralModel asset;
	private String date_start;
	private String date_start_end;
	private String date_end_schedual;
	private String date_end_schedual_end;
	private String date_end_real;
	private String date_end_real_end;
	
	public String getDate_start_end() {
		return date_start_end;
	}

	public void setDate_start_end(String date_start_end) {
		this.date_start_end = date_start_end;
	}

	public String getDate_end_schedual_end() {
		return date_end_schedual_end;
	}

	public void setDate_end_schedual_end(String date_end_schedual_end) {
		this.date_end_schedual_end = date_end_schedual_end;
	}

	public String getDate_end_real_end() {
		return date_end_real_end;
	}

	public void setDate_end_real_end(String date_end_real_end) {
		this.date_end_real_end = date_end_real_end;
	}

	public RentAsset()
	{
		comany_client = new CompanyModel();
		company_master = new CompanyModel();
		dept_master = new Department();
		asset = new AssetGeneralModel();
	}
	

}
