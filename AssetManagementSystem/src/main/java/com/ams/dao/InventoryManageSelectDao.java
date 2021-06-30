package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventorySession;



public class InventoryManageSelectDao {
	private InventorySession inventorySession =null;
	
	public InventoryManageSelectDao()
	{
		
	}
	public InventoryManageSelectDao(InventorySession inventorysession)
	{
		this.inventorySession = inventorysession;
	}
	
	public List<InventorySession> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<InventorySession> lstInventorySession =new ArrayList<InventorySession>();
		while (result.next()) {
			InventorySession invSession= new InventorySession();
			invSession.setInventory_id(result.getString("ID_SESSION"));
			invSession.setInventory_session_name(result.getString("SESSION_NAME"));
			invSession.setInventory_start_date(result.getString("START_DT"));
			invSession.setInventory_end_date(result.getString("END_DT"));
			invSession.setInventory_session_id(result.getString("ID_INVENTORY"));
			
			lstInventorySession.add(invSession);
		}
		
		return lstInventorySession;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT *");
		sql.append(" FROM");
		sql.append(" 	INVENTORY_SESSION");
		sql.append(" WHERE");
		sql.append(" 	1=1");
		
		
		return sql.toString();
	}
}
