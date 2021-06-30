package com.ams.model;

import javax.servlet.http.HttpServletRequest;

public class AssetGeneralFormSearch {
	
	private String RFID;
	private String Name;
	private String Model;
	private String Series;
	private String Department_cd;;
	private String Accountant_CD;
	private String DateStart_Start;
	private String DateStart_End;
	private String  PriceStart;
	private String status;
	private String rowpage;
	

	public String getRowPage() {
		return rowpage;
	}
	public void setRowPage(String page) {
		this.rowpage = page;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDepartment_cd() {
		return Department_cd;
	}
	public void setDepartment_cd(String department_cd) {
		Department_cd = department_cd;
	}
	public String getDateStart_Start() {
		return DateStart_Start;
	}
	public void setDateStart_Start(String dateStart_Start) {
		DateStart_Start = dateStart_Start;
	}
	public String getDateStart_End() {
		return DateStart_End;
	}
	public void setDateStart_End(String dateStart_End) {
		DateStart_End = dateStart_End;
	}
	public String getPriceStart() {
		return PriceStart;
	}
	public void setPriceStart(String priceStart) {
		PriceStart = priceStart;
	}
	public String getPriceEnd() {
		return PriceEnd;
	}
	public void setPriceEnd(String priceEnd) {
		PriceEnd = priceEnd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany_CD() {
		return Company_CD;
	}
	public void setCompany_CD(String company_CD) {
		Company_CD = company_CD;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getDepartment_name() {
		return Department_name;
	}
	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}
	public String getDateEnd_End() {
		return DateEnd_End;
	}
	public void setDateEnd_End(String dateEnd_End) {
		DateEnd_End = dateEnd_End;
	}
	public String getDateEnd_Start() {
		return DateEnd_Start;
	}
	public void setDateEnd_Start(String dateEnd_Start) {
		DateEnd_Start = dateEnd_Start;
	}

	private String PriceEnd;
	private String Note;
	private String id;
	private String Company_CD;
	private String group_id;
	private String group_name;
	private String Department_name;
	private String DateEnd_End;
	private String DateEnd_Start;
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getSeries() {
		return Series;
	}
	public void setSeries(String series) {
		Series = series;
	}
	
	public String getAccountant_CD() {
		return Accountant_CD;
	}
	public void setAccountant_CD(String accountant_CD) {
		Accountant_CD = accountant_CD;
	}
	
	
	
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	
	
	public AssetGeneralFormSearch(HttpServletRequest request)
	{
		
		
	}
	
	public AssetGeneralFormSearch()
	{
	
	}

}
