package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventoryCheckingRealtimeModel;

public class InventoryCheckingResultUpdateExpainDao {
	
	public InventoryCheckingRealtimeModel asset;
	
	public InventoryCheckingResultUpdateExpainDao(InventoryCheckingRealtimeModel asset)
	{
		this.asset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" INVENTORY_CHECKING");
		sql.append(" SET");
		sql.append(" 	STATUS_EXPLAIN =").append("'"+asset.getStatus()+"'");
		sql.append(" 	,EXPLAIN =").append("'"+asset.getExplain()+"'");
		sql.append(" 	,EXPLAIN_DT =").append("'"+asset.getExpain_dt()+"'");
		sql.append(" 	,USER_EXPLAIN =").append("'"+asset.getUser_explain()+"'");
		sql.append(" WHERE ");
		sql.append(" 	INV_CHECKING_CD =").append("'"+asset.getInventory_Checking_Cd()+"'");
		
		
		return sql.toString();
	}
}
