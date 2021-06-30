package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventorySession;
import com.ams.model.InventorySessionModel;
import com.ams.util.Common;



public class InventorySessionSelectDao {
	private InventorySessionModel inven =null;
	
	public InventorySessionSelectDao()
	{
		
	}
	public InventorySessionSelectDao(InventorySessionModel inven)
	{
		this.inven = inven;
	}
	
	public List<InventorySessionModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<InventorySessionModel> lstInventorySession =new ArrayList<InventorySessionModel>();
		while (result.next()) {
			InventorySessionModel invSession= new InventorySessionModel();
			invSession.setInvenotrySessionCD(result.getString("INVENTORY_SESSION_CD"));
			invSession.setInventorySessionName(result.getString("INVENTORY_SESSION_NAME"));
			invSession.setInventorySessionShortCD(result.getString("INVENTORY_SESSION_SHORT_CD"));
			invSession.setInventorySessionStart(result.getString("INVENTORY_SESSION_START"));
			invSession.setInventorySessionEnd(result.getString("INVENTORY_SESSION_END"));
			
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
		if(inven !=null)
		{
			if(Common.isNotEmpty(inven.getInvenotrySessionCD()))
			{
				sql.append(" 	AND INVENTORY_SESSION_CD = ").append("'"+inven.getInvenotrySessionCD()+"'");
			}
			if(Common.isNotEmpty(inven.getInventorySessionCompanyCD()))
			{
				sql.append(" 	AND CMPN_CD = ").append("'"+inven.getInventorySessionCompanyCD()+"'");
			}
			
			if(Common.isNotEmpty(inven.getInventorySessionName()))
			{
				sql.append(" 	AND INVENTORY_SESSION_NAME = ").append("'"+inven.getInventorySessionName()+"'");
			}
			if(Common.isNotEmpty(inven.getInventorySessionEnd()))
			{
				sql.append(" 	AND INVENTORY_SESSION_END <= ").append("'"+inven.getInventorySessionEnd()+"'");
			}
			if(Common.isNotEmpty(inven.getInventorySessionShortCD()))
			{
				sql.append(" 	AND INVENTORY_SESSION_SHORT_CD = ").append("'"+inven.getInventorySessionShortCD()+"'");
			}
			if(Common.isNotEmpty(inven.getInventorySessionStart()))
			{
				sql.append(" 	AND INVENTORY_SESSION_START >= ").append("'"+inven.getInventorySessionStart()+"'");
			}			
		}
		return sql.toString();
	}
}
