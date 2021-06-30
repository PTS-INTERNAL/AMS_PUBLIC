package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventorySession;
import com.ams.model.InventorySessionModel;
import com.ams.util.Common;

public class InventorySessionInsertDao {
	
	InventorySessionModel inven;
	
	public InventorySessionInsertDao(InventorySessionModel inven)
	{
		this.inven = inven;
	}
	
	public int excute() throws SQLException
	{
		int result =0;
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement;
		
			sqlStatement = connectString.prepareStatement(getSql());
			//System.out.println(getSql());
			sqlStatement.setString(1,inven.getInvenotrySessionCD());
			sqlStatement.setString(2,inven.getInventorySessionCompanyCD());
			sqlStatement.setString(3,inven.getInventorySessionName());
			sqlStatement.setString(4,inven.getInventorySessionShortCD());
			sqlStatement.setString(5,inven.getInventorySessionStart());
			sqlStatement.setString(6,inven.getInventorySessionEnd());
			sqlStatement.setString(7,inven.getUserInsert().getEmployee_cd());
			sqlStatement.setString(8,inven.getDate_insert());
			sqlStatement.setString(9,inven.getUserUpdate().getEmployee_cd());
			sqlStatement.setString(10,inven.getDate_update());
	
			result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" INVENTORY_SESSION");
		sql.append(" (");
		sql.append(" 	INVENTORY_SESSION_CD");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,INVENTORY_SESSION_NAME");
		sql.append(" 	,INVENTORY_SESSION_SHORT_CD");
		sql.append(" 	,INVENTORY_SESSION_START");
		sql.append(" 	,INVENTORY_SESSION_END");
		sql.append(" 	,USER_INSERT");
		sql.append(" 	,INSERT_DT");
		sql.append(" 	,USER_UPDATE");
		sql.append(" 	,UPDATE_DT");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	?");//ASSET_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//ASSET_NAME
		sql.append(" 	,?");//ASSET_MODEL
		sql.append(" 	,?");//ASSET_SERIES
		sql.append(" 	,?");//ASSET_DEPARTMENT
		sql.append(" 	,?");//ASSET_NAME
		sql.append(" 	,?");//ASSET_MODEL
		sql.append(" 	,?");//ASSET_SERIES
		sql.append(" 	,?");//ASSET_DEPARTMENT
		sql.append(" )");
		
		return sql.toString();
	}
	
	
}
