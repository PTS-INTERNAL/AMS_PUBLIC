package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyModel;
import com.ams.util.Common;



public class CompanySelectDao {
	
	CompanyModel company;
	
	public CompanySelectDao()
	{
		
	}
	

	public CompanySelectDao(CompanyModel company)
	{
		this.company = company;
	}
	public CompanyModel excuteById(String id) throws SQLException
	{
		CompanyModel company = new CompanyModel();
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		
		//System.out.println(getSqlById(id));
		
		result = stmt.executeQuery(getSqlById(id));
		while (result.next()) {
			company.setCompany_cd(result.getString("CMPN_CD"));
			company.setCompany_name(result.getString("CMPN_NAME"));
			company.setCompany_logo(result.getString("CMPN_LOGO"));
			company.setCompany_address(result.getString("CMPN_ADDRESS"));
			company.setCompany_tax_number(result.getString("CMPN_TAX"));
			company.setCompany_phone(result.getString("CMPN_PHONE"));
			company.setCompany_email(result.getString("CMPN_EMAIL"));
			company.setCompany_website(result.getString("CMPN_WEBSITE"));
			company.setCompany_description(result.getString("CMPN_DESCRIPTION"));
			company.setCompany_shortname(result.getString("CMPN_SHORT_NAME"));
		}
		return company;
	}
	public List<CompanyModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<CompanyModel> lstCompany =new ArrayList<CompanyModel>();
		while (result.next()) {
			CompanyModel company = new CompanyModel();
			company.setCompany_cd(result.getString("CMPN_CD"));
			company.setCompany_name(result.getString("CMPN_NAME"));
			company.setCompany_logo(result.getString("CMPN_LOGO"));
			company.setCompany_address(result.getString("CMPN_ADDRESS"));
			company.setCompany_tax_number(result.getString("CMPN_TAX"));
			company.setCompany_phone(result.getString("CMPN_PHONE"));
			company.setCompany_email(result.getString("CMPN_EMAIL"));
			company.setCompany_website(result.getString("CMPN_WEBSITE"));
			company.setCompany_description(result.getString("CMPN_DESCRIPTION"));
			company.setCompany_shortname(result.getString("CMPN_SHORT_NAME"));
			
			lstCompany.add(company);
		}
		return lstCompany;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	COMPANY ");
		sql.append("WHERE 	 	DELETE_FG=0 ");
		if(company !=null)
		{
			if(company.getCompany_cd() != null && company.getCompany_cd().trim().length()>0)
			{
				sql.append("AND CMPN_CD = ").append("'" + company.getCompany_cd() + "'");
			}
			if(company.getCompany_delete() != null && company.getCompany_delete().trim().length()>0)
			{
				sql.append("AND DELETE_FG = ").append("'" + company.getCompany_delete() + "'");
			}
			if(Common.isNotCheckEmpty(company.getCompany_shortname()))
			{
				sql.append("AND CMPN_SHORT_NAME = ").append("'" + company.getCompany_shortname() + "'");
			}
			if(Common.isNotCheckEmpty(company.getCompany_name()))
			{
				sql.append("AND CMPN_NAME = ").append("'" + company.getCompany_name() + "'");
			}
		}
		sql.append(" ORDER BY 	 	CMPN_CD ");
		
		
		return sql.toString();
	}
	
	public String getSqlById(String id)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	COMPANY ");
		sql.append("WHERE 	 	");
		sql.append(" CMPN_CD = ").append("'" + id + "'");
		
		return sql.toString();
	}
	
	

}
