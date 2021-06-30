package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.PermissionInventorySession;
import com.ams.util.Common;

public class PermissionInventorySessionInsertDao {
	
	PermissionInventorySession permission;
	
	public PermissionInventorySessionInsertDao(PermissionInventorySession permission)
	{
		this.permission = permission;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
		sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
		sqlStatement.setString(2,permission.getInventorySession().getInventory_id());
		sqlStatement.setString(3,permission.getUser().getEmployee_cd());
		sqlStatement.setString(4,permission.getUserInsert());
		sqlStatement.setString(5,permission.getInsert_dt());
		sqlStatement.setString(6,permission.getUserUpdate());
		sqlStatement.setString(7,permission.getUpdate_dt());
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
				
		sql.append(" INSERT INTO");
		sql.append(" PERMISSION_INVENTORY_SESSION");
		sql.append(" (");
		sql.append(" 	PERMIS_CD ");
		sql.append(" 	,SESSION_CD");
		sql.append(" 	,USER_EMPLOYEE_CD");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//BORROW_CD
		sql.append(" 	,?");//LOAN_CMPN_CD
		sql.append(" 	,?");//LOAN_DEPT
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//BORROW_CMPN_CD
		sql.append(" 	,?");//BORROW_DEPT
		sql.append(" 	,?");//DATE_START
		sql.append(" )");
		
		return sql.toString();
	}

	

}
