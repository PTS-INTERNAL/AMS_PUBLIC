package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.GroupAsset;



public class GroupAssetUpdateDao {
	GroupAsset groupAsset;
	
	public GroupAssetUpdateDao()
	{
		
	}
	

	public GroupAssetUpdateDao(GroupAsset groupAsset)
	{
		this.groupAsset = groupAsset;
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
		
		sql.append("UPDATE 	GROUP_ASSET	");
		sql.append("SET		");
		sql.append(" 	GROUP_NAME =  ").append("N'" + groupAsset.getGroup_name() +"'");
		sql.append(" 	,GROUP_DESCRIPTION =  ").append("N'" + groupAsset.getGroup_desciption() +"'");
		sql.append("WHERE 	 ");
		sql.append(" 	  GROUP_ID = ").append("'" + groupAsset.getGroup_id() +"'");

	
		return sql.toString();
	}
}
