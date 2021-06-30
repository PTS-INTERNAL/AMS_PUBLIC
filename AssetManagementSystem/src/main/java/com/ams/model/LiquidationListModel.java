package com.ams.model;

public class LiquidationListModel {
	
	private String List_id;
	private String ListName;
	private String DateCreate;
	private String UserCreate;
	private String Compary_cd;
	
	public String getCompary_cd(){
		return Compary_cd;
	}

	public void setCompary_cd(String cp) {
		this.Compary_cd = cp;
	}
	
	public String getList_id() {
		return List_id;
	}

	public void setList_id(String List) {
		this.List_id = List;
	}

	
	public String getListName() {
		return ListName;
	}

	public void setListName(String listname) {
		this.ListName = listname;
	}

	public String getDateCreate() {
		return DateCreate;
	}
	public void setDateCreate(String date) {
		this.DateCreate = date;
	}

	
	public void setUserCreate(String User) {
		this.UserCreate = User;
	}

	public String getUserCreate() {
		return UserCreate;
	}
	public LiquidationListModel()
	{
		
	}

}
