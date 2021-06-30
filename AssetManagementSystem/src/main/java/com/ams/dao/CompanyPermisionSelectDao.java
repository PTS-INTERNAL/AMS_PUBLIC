package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.CompanyModel;
import com.ams.model.CompanyPermision;

public class CompanyPermisionSelectDao {
	CompanyModel company;
	
	public CompanyPermisionSelectDao(CompanyModel company) {
		this.company = company;
		
	}
	public List<CompanyModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<CompanyModel> lstCompany =new ArrayList<CompanyModel>();
		while (result.next()) {
			CompanyModel company = new CompanyModel();
			company.setCompany_cd(result.getString("CMPN_CD"));
			lstCompany.add(company);
		}
		return lstCompany;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	COMPANY_PERMISSION CPM, COMPANY CMPN ");
		sql.append("WHERE  CMPN.DELETE_FG = '0' AND CPM.CMPN_CD = CMPN.CMPN_CD ");
		if(company.getCompany_cd() != null && company.getCompany_cd().trim().length()>0)
		{
			sql.append(" AND CPM.CMPN_CD = ").append("'" + company.getCompany_cd() + "'");
		}
		if(company.getCompany_delete() != null && company.getCompany_delete().trim().length()>0)
		{
			sql.append(" AND CPM.DELETE_FG = ").append("'" + company.getCompany_delete() + "'");
		}
		if(company.getCompany_user() != null && company.getCompany_user().trim().length()>0)
		{
			sql.append(" AND CPM.EMPLOYEE_CD = ").append("'" + company.getCompany_user() + "'");
		}
		sql.append(" ORDER BY CPM.CMPN_CD ");
		
		
		return sql.toString();
	}
	
	public List<CompanyPermision> excuteCompanyPermission() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSqlCompanyPermision());
		result = stmt.executeQuery(getSqlCompanyPermision());
		List<CompanyPermision> lstCompany =new ArrayList<CompanyPermision>();
		while (result.next()) {
			CompanyPermision company = new CompanyPermision();
			company.getCompany().setCompany_cd(result.getString("CMPN_CD"));
			company.getUser().setEmployee_cd(result.getString("USER_EMPLOYEE_CD"));
			company.getUser().setName(result.getString("USER_NAME"));
			company.setId(result.getString("ROLE").trim());
			company.setValueCheck(result.getString("DELETE_FG").trim());
			lstCompany.add(company);
		}
		return lstCompany;
	}
	public String getSqlCompanyPermision()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 		");
		sql.append("	CP.CMPN_CD	");
		sql.append("	,US.USER_EMPLOYEE_CD	");
		sql.append("	,US.USER_NAME	");
		sql.append("	,CP.CMPN_PER_CD	");
		sql.append("	,ISNULL(CP.DELETE_FG,1) AS	DELETE_FG");
		sql.append("	,ISNULL(CMPN_PER_CD,0) AS ROLE	");
		sql.append(" 	FROM USER_SYSTEM US  LEFT JOIN  COMPANY_PERMISSION  CP ");
		sql.append(" 	ON US.USER_EMPLOYEE_CD = CP.EMPLOYEE_CD ");
		sql.append("	AND CP.CMPN_CD = ").append("'" + company.getCompany_cd() + "'");
		sql.append("WHERE 	 	1=1 ");
		if(company.getCompany_delete() != null && company.getCompany_delete().trim().length()>0)
		{
			sql.append(" AND DELETE_FG = ").append("'" + company.getCompany_delete() + "'");
		}
		if(company.getCompany_user() != null && company.getCompany_user().trim().length()>0)
		{
			sql.append(" AND EMPLOYEE_CD = ").append("'" + company.getCompany_user() + "'");
		}
		
		
		return sql.toString();
	}
}
