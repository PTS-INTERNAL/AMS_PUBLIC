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



public class UserUpdatePassworDefaultDao {

	UserModel user;
	
	public UserUpdatePassworDefaultDao()
	{
		
	}
	
	public UserUpdatePassworDefaultDao(UserModel user)
	{
		this.user = user;
	}
	
	public int excute() throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		int result = 0;
		//---
		//System.out.println(getSql());
		//---
		result = sqlStatement.executeUpdate();
		
		return result;
		
		
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE USER_SYSTEM ");
		sql.append(" SET ");
		sql.append("  PASSWORD_DEFAULT = ''");
		sql.append("  ,USER_PASSWORD =  ").append("'"+user.getPasword()+"'");
		sql.append(" WHERE ");
		sql.append(" USER_EMPLOYEE_CD =  ").append("'"+user.getEmployment_CD()+"'");;
		
		return sql.toString();
	}
}
