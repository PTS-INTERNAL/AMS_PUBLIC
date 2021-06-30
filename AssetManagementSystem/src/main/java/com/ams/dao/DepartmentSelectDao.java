package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.Department;



public class DepartmentSelectDao {
	Department department;
	
	public DepartmentSelectDao()
	{
		
	}
	

	public DepartmentSelectDao(Department department)
	{
		this.department = department;
	}

	public List<Department> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<Department> lstDept =new ArrayList<Department>();
		while (result.next()) {
			Department dept = new Department();
			dept.setCompany_cd(result.getString("CMPN_CD"));
			dept.setDept_cd(result.getString("DEPT_CD"));
			dept.setDept_name(result.getString("DEPARTMENT_NAME"));
			dept.setDept_desciption(result.getString("DESCRIPTION"));
			
			lstDept.add(dept);
		}
		return lstDept;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	DEPRATMENT ");
		sql.append("WHERE 	 	1=1 ");
		if(department !=null)
		{
			if(department.getCompany_cd()!=null && department.getCompany_cd().trim().length()>0)
			{
				sql.append(" 	AND  CMPN_CD = ").append("'" + department.getCompany_cd() +"'");
			}
			if(department.getDept_cd() !=null && department.getDept_cd().trim().length()>0)
			{
				sql.append(" 	AND  DEPT_CD = ").append("'" + department.getDept_cd() +"'");
			}
			if(department.getDept_name() !=null && department.getDept_name().trim().length()>0)
			{
				sql.append(" 	AND  DEPARTMENT_NAME = ").append("'" + department.getDept_name() +"'");
			}
		}
		
		return sql.toString();
	}
}
