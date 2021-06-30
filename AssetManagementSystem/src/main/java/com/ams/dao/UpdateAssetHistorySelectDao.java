package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.GroupAsset;
import com.ams.model.UpdateAssetHistoryModel;
import com.ams.util.Common;



public class UpdateAssetHistorySelectDao {
	UpdateAssetHistoryModel his;
	
	public UpdateAssetHistorySelectDao()
	{
		
	}
	

	public UpdateAssetHistorySelectDao(UpdateAssetHistoryModel his)
	{
		this.his = his;
	}

	public List<UpdateAssetHistoryModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<UpdateAssetHistoryModel> lstDept =new ArrayList<UpdateAssetHistoryModel>();
		while (result.next()) {
			UpdateAssetHistoryModel ga = new UpdateAssetHistoryModel();
			ga.setDate(result.getString("INSERT_DT"));
			ga.setReason(result.getString("REASON_UPDATE"));
			ga.getUser().setName(result.getString("USER_NAME"));
			lstDept.add(ga);
		}
		return lstDept;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	*	");
		sql.append("FROM		");
		sql.append(" 	HIS_ASSET_UPDATE  HAU");
		sql.append(" 	LEFT JOIN USER_SYSTEM US ON ");
		sql.append(" 	HAU.USER_INSERT=US.USER_EMPLOYEE_CD ");
		sql.append("WHERE 	 	1=1 ");
		if(his !=null)
		{
			if(Common.isNotCheckEmpty(his.getAsset().getId()))
			{
				sql.append(" 	AND  ASSET_CD = ").append("'" + his.getAsset().getId() +"'");
			}
			if(Common.isNotCheckEmpty(his.getAsset().getRFID()))
			{
				sql.append(" 	AND  ASSET_RFID = ").append("'" + his.getAsset().getRFID() +"'");
			}	
		}
		
		return sql.toString();
	}
}
