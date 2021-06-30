package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.InventorySessionPermission;
import com.ams.model.PermissionInventorySession;


public class InventorySessionPermissionSelectDao {
	
	PermissionInventorySession inventorySessionPermission =null;
	
	public InventorySessionPermissionSelectDao()
	{
		
	}
	public InventorySessionPermissionSelectDao( PermissionInventorySession inventorySessionPermission)
	{
		this.inventorySessionPermission = inventorySessionPermission;
		
	}
	
	public List<PermissionInventorySession> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<PermissionInventorySession> lstInventorySessionPermission =new ArrayList<PermissionInventorySession>();
		while (result.next()) {
			PermissionInventorySession invSessionPermission= new PermissionInventorySession();
			invSessionPermission.getUser().setEmployee_cd(result.getString("USER_EMPLOYEE_CD"));
			invSessionPermission.getInventorySession().setInventory_session_id(result.getString("SESSION_CD"));
			
			lstInventorySessionPermission.add(invSessionPermission);
		}
		
		return lstInventorySessionPermission;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT *");
		sql.append(" FROM");
		sql.append(" 	PERMISSION_INVENTORY_SESSION");
		sql.append(" WHERE");
		sql.append(" 	1=1");
		if(inventorySessionPermission != null)
		{
			if(inventorySessionPermission.getUser().getEmployee_cd() != null)
			{
				sql.append(" AND USER_EMPLOYEE_CD=").append("'" + inventorySessionPermission.getUser().getEmployee_cd()+"'");
			}
			if(inventorySessionPermission.getInventorySession().getInventory_id() != null)
			{
				sql.append(" AND SESSION_CD=").append("'" + inventorySessionPermission.getInventorySession().getInventory_id()+"'");
			}
		}
			
		return sql.toString();
	}

}
