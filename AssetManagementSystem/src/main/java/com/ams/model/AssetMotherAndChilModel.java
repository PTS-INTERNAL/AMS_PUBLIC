package com.ams.model;

import java.util.ArrayList;
import java.util.List;

public class AssetMotherAndChilModel {
	private AssetGeneralModel asset;
	private CompanyModel company;
	private String count;
	private int column;
	private String keys;
	private List<String> lstColumn;
	private int sum;
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public List<String> getLstColumn() {
		return lstColumn;
	}
	public void setLstColumn(List<String> lstColumn) {
		this.lstColumn = lstColumn;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public AssetMotherAndChilModel()
	{
		this.asset = new AssetGeneralModel();
		this.company = new CompanyModel();
		this.lstColumn = new ArrayList<String>();
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public AssetGeneralModel getAsset() {
		return asset;
	}
	public void setAsset(AssetGeneralModel asset) {
		this.asset = asset;
	}
	public CompanyModel getCompany() {
		return company;
	}
	public void setCompany(CompanyModel company) {
		this.company = company;
	}

}
