package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserModel;
import com.ams.util.Common;



public class UserInsertDao {
	UserModel user = null;
	
	public UserInsertDao()
	{
		
	}
	
	public UserInsertDao(UserModel us)
	{
		this.user = us;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
			sqlStatement.setString(2,user.getName());
			sqlStatement.setString(3,user.getEmployee_cd());
			sqlStatement.setString(4,user.getPasword());
			sqlStatement.setString(5,user.getCompany_cd());
			sqlStatement.setString(6,user.getPassword_Default());
			sqlStatement.setString(7,user.getRole());
			sqlStatement.setString(8,user.getUser_Insert());
			sqlStatement.setString(9,user.getInsert_Dt());
			sqlStatement.setString(10,user.getUser_Update());
			sqlStatement.setString(11,user.getUpdate_Dt());
			sqlStatement.setString(12,user.getDate_expire());
			sqlStatement.setString(13,user.getTime_expire());

			 result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" USER_SYSTEM");
		sql.append(" (");
		sql.append(" 	USER_ID");
		sql.append(" 	,USER_NAME");
		sql.append(" 	,USER_EMPLOYEE_CD");
		sql.append(" 	,USER_PASSWORD");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,PASSWORD_DEFAULT");
		sql.append(" 	,ROLE");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" 	,DATE_EXPIRE");
		sql.append(" 	,TIME_EXPIRE");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" )");
		
		return sql.toString();
	}

}
