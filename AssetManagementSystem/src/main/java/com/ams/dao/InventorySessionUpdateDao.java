package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.GroupAsset;
import com.ams.model.InventorySessionModel;



public class InventorySessionUpdateDao {
	InventorySessionModel inven;
	
	public InventorySessionUpdateDao(InventorySessionModel inven)
	{
		this.inven = inven;
	}
	



	public int excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		return stmt.executeUpdate(getSql());
		
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE 	INVENTORY_SESSION	");
		sql.append("SET		");
		sql.append(" 	INVENTORY_SESSION_NAME =  ").append("N'" + inven.getInventorySessionName() +"'");
		sql.append(" 	,INVENTORY_SESSION_SHORT_CD =  ").append("N'" + inven.getInventorySessionShortCD() +"'");
		sql.append(" 	,INVENTORY_SESSION_START =  ").append("N'" + inven.getInventorySessionStart() +"'");
		sql.append(" 	,INVENTORY_SESSION_END =  ").append("N'" + inven.getInventorySessionEnd() +"'");
		sql.append(" 	,USER_UPDATE =  ").append("N'" + inven.getUserUpdate().getEmployee_cd() +"'");
		sql.append(" 	,UPDATE_DT =  ").append("N'" + inven.getDate_update() +"'");
		sql.append("WHERE 	 ");
		sql.append(" 	  INVENTORY_SESSION_CD = ").append("'" + inven.getInvenotrySessionCD() +"'");

	
		return sql.toString();
	}
}
