package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.GroupAsset;



public class GroupAssetSelectDao {
	GroupAsset groupAsset;
	
	public GroupAssetSelectDao()
	{
		
	}
	

	public GroupAssetSelectDao(GroupAsset groupAsset)
	{
		this.groupAsset = groupAsset;
	}

	public List<GroupAsset> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<GroupAsset> lstDept =new ArrayList<GroupAsset>();
		while (result.next()) {
			GroupAsset ga = new GroupAsset();
			ga.setCompany_cd(result.getString("CMPN_CD"));
			ga.setGroup_desciption(result.getString("GROUP_DESCRIPTION"));
			ga.setGroup_id(result.getString("GROUP_ID"));
			ga.setGroup_name(result.getString("GROUP_NAME"));
			
			lstDept.add(ga);
		}
		return lstDept;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	GROUP_ASSET ");
		sql.append("WHERE 	 	1=1 ");
		if(groupAsset !=null)
		{
			if(groupAsset.getCompany_cd()!=null && groupAsset.getCompany_cd().trim().length()>0)
			{
				sql.append(" 	AND  CMPN_CD = ").append("'" + groupAsset.getCompany_cd() +"'");
			}
			if(groupAsset.getGroup_id() !=null && groupAsset.getGroup_id().trim().length()>0)
			{
				sql.append(" 	AND  GROUP_ID = ").append("'" + groupAsset.getGroup_id() +"'");
			}
			if(groupAsset.getGroup_name() !=null && groupAsset.getGroup_name().trim().length()>0)
			{
				sql.append(" 	AND  GROUP_NAME = ").append("'" + groupAsset.getGroup_name() +"'");
			}
		}
		sql.append("ORDER BY  GROUP_NAME");
		return sql.toString();
	}
}
