package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventoryChecking;
import com.ams.util.Common;



public class CheckingInventoryInsertDao {
	
	InventoryChecking inventoryChecking = null;
	
	public CheckingInventoryInsertDao()
	{
		
	}
	
	public CheckingInventoryInsertDao(InventoryChecking inventoryChecking) {
		this.inventoryChecking = inventoryChecking;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));//INVENTORY_SESSION_CD
			sqlStatement.setString(2,inventoryChecking.getAsset_Rfid());//ASSET_RFID
			sqlStatement.setString(3,inventoryChecking.getInventorySessionCD());//INVENTORY_CHECK
			sqlStatement.setString(4,inventoryChecking.getInventory_Date());//INVENTORY_CHECK_DATE
			sqlStatement.setString(5,inventoryChecking.getUserChecking());//USER_CHECK
			sqlStatement.setString(6,"1");//USER_CHECK
			result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" INVENTORY ");
		sql.append(" (");
		sql.append(" 	INVENTORY_SESSION_CD");
		sql.append(" 	,ASSET_RFID");
		sql.append(" 	,INVENTORY_CHECK");
		sql.append(" 	,INVENTORY_DATE");
		sql.append(" 	,USER_CHECK");
		sql.append(" 	,STATUS");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	 ?");//INVENTORY_SESSION_CD
		sql.append(" 	,?");//ASSET_RFID
		sql.append(" 	,?");//INVENTORY_CHECK
		sql.append(" 	,?");//INVENTORY_DATE
		sql.append(" 	,?");//USER_CHECK
		sql.append(" 	,?");//USER_CHECK
		sql.append(" )");
		
		return sql.toString();
	}

}
