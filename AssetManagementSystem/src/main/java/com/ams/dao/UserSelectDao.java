package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserModel;



public class UserSelectDao {

	UserModel user;
	
	public UserSelectDao()
	{
		
	}
	
	public UserSelectDao(UserModel user)
	{
		this.user = user;
	}
	
	public List<UserModel> excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//---
		//System.out.println(getSql());
		//---
		result = stmt.executeQuery(getSql());
		List<UserModel> lstAsset =new ArrayList<UserModel>();
		while (result.next()) {
			UserModel user = new UserModel();
			user.setName(result.getString("USER_NAME"));
			//user.setDepartment(result.getString("DEPARTMENT"));
			user.setEmployment_CD(result.getString("USER_EMPLOYEE_CD"));
			//user.setPosition(result.getString("POSITION"));
			user.setCompany_cd(result.getString("CMPN_CD"));
			user.setRole(result.getString("ROLE"));
			user.setUser_Insert(result.getString("USER_INSERT"));
			user.setInsert_Dt(result.getString("INSERT_DT"));
			user.setUser_Update(result.getString("USER_UPDATE"));
			user.setUpdate_Dt(result.getString("UPDATE_DT"));
			user.setPassword_Default(result.getString("PASSWORD_DEFAULT"));
			user.setDate_expire(result.getString("DATE_EXPIRE"));
			user.setTime_expire(result.getString("TIME_EXPIRE"));
			lstAsset.add(user);
		}
		
		return lstAsset;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT *");
		sql.append(" FROM");
		sql.append(" USER_SYSTEM");
		sql.append(" WHERE");
		sql.append(" 1=1 ");
		if(user != null)
		{
			if(user.getEmployment_CD()!=null && user.getEmployment_CD().trim().length() >0)
			{
				sql.append(" AND USER_EMPLOYEE_CD = ").append("'" + user.getEmployment_CD() + "'");
			}
			if(user.getName()!=null && user.getName().trim().length() >0)
			{
				sql.append(" AND USER_NAME = ").append("'" + user.getName() + "'");
			}
			if(user.getPasword()!=null && user.getPasword().trim().length() >0)
			{
				sql.append(" AND USER_PASSWORD = ").append("'" + user.getPasword() + "'");
			}
			if(user.getCompany_cd()!=null && user.getCompany_cd().trim().length() >0)
			{
				sql.append(" AND CMPN_CD = ").append("'" + user.getCompany_cd() + "'");
			}
			if(user.getPassword_Default()!=null && user.getPassword_Default().trim().length() >0)
			{
				sql.append(" AND PASSWORD_DEFAULT = ").append("'" + user.getPassword_Default() + "'");
			}
		}
		
		return sql.toString();
	}
}
