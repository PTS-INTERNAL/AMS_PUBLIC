package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserDeptPermissionModel;
import com.ams.model.UserModel;
import com.ams.util.Common;



public class UserDeptPermissionInsertDao {
	UserDeptPermissionModel user = null;
	
	public UserDeptPermissionInsertDao()
	{
		
	}
	
	public UserDeptPermissionInsertDao(UserDeptPermissionModel user)
	{
		this.user = user;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		System.out.println(getSQL());
		
			sqlStatement.setString(1,user.getPerCd());
			sqlStatement.setString(2,user.getCmpn_cd());
			sqlStatement.setString(3,user.getUser_cd());
			sqlStatement.setString(4,user.getDept_cd());
			sqlStatement.setString(5,user.getValue());
			
			 result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" USER_DEPT_PERMISSION");
		sql.append(" (");
		sql.append(" 	PER_CD");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,USER_CD");
		sql.append(" 	,DEPT_CD");
		sql.append(" 	,VALUE");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" )");
		
		return sql.toString();
	}

}
