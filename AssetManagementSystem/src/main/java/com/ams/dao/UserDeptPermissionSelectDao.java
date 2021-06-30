package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserDeptPermissionModel;
import com.ams.model.UserModel;
import com.ams.util.Common;

public class UserDeptPermissionSelectDao {
	
	UserDeptPermissionModel userDept;
	
	public UserDeptPermissionSelectDao()
	{
		
	}
	
	public UserDeptPermissionSelectDao(UserDeptPermissionModel userDept)
	{
		this.userDept = userDept;
	}
	
	public List<UserDeptPermissionModel> excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//---
		System.out.println(getSql());
		//---
		result = stmt.executeQuery(getSql());
		List<UserDeptPermissionModel> lstUser =new ArrayList<UserDeptPermissionModel>();
		while (result.next()) {
			UserDeptPermissionModel user = new UserDeptPermissionModel();
			user.setPerCd(result.getString("PER_CD"));
			user.setCmpn_cd(result.getString("CMPN_CD"));
			user.setDept_cd(result.getString("DEPT_CD"));
			user.setUser_cd(result.getString("USER_EMPLOYEE_CD"));
			user.setValue(result.getString("VALUE").trim());
			user.setDept_name(result.getString("DEPARTMENT_NAME"));
			lstUser.add(user);
		}
		
		return lstUser;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT  ");
		sql.append("UDP.PER_CD, ");
		sql.append("UDP.CMPN_CD, ");
		sql.append("US.USER_EMPLOYEE_CD, ");
		sql.append("DPT.DEPT_CD,  ");
		sql.append("DPT.DEPARTMENT_NAME, ");
		sql.append("ISNULL(UDP.VALUE,'0')  AS VALUE ");
		sql.append("FROM  ");
		sql.append("DEPRATMENT DPT ");
		sql.append("LEFT JOIN  USER_DEPT_PERMISSION UDP  ");
		sql.append("ON UDP.DEPT_CD = DPT.DEPT_CD  ");
		sql.append("AND UDP.USER_CD =").append("'"+userDept.getUser_cd()+"'");
		sql.append("AND UDP.CMPN_CD=").append("'"+userDept.getCmpn_cd()+"'");
		sql.append("LEFT JOIN  USER_SYSTEM US  ");
		sql.append("ON UDP.USER_CD = US.USER_EMPLOYEE_CD  ");
		sql.append("WHERE 1=1  "); 
		if(Common.isNotEmpty(userDept.getPerCd()))
		{
			sql.append("AND PER_CD = ").append("'"+userDept.getPerCd()+"'");
		}
		
		if(Common.isNotEmpty(userDept.getCmpn_cd()))
		{
			sql.append("AND DPT.CMPN_CD = ").append("'"+userDept.getCmpn_cd()+"'");
		}
		
		return sql.toString();
	}

}
