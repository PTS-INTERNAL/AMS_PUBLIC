package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.util.Common;

public class InventoryCheckingSelectRealtimeDao {
	
	InventoryCheckingRealtimeModel asset;
	
	public InventoryCheckingSelectRealtimeDao(InventoryCheckingRealtimeModel asset)
	{
		this.asset = asset;
	}
	
	public List<InventoryCheckingRealtimeModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<InventoryCheckingRealtimeModel> lstAsset =new ArrayList<InventoryCheckingRealtimeModel>();
		while (result.next()) {
			InventoryCheckingRealtimeModel Asset = new InventoryCheckingRealtimeModel();
			Asset.getAsset().setRFID(result.getString("ASSET_RFID"));
			Asset.getAsset().setName(result.getString("ASSET_NAME"));
			Asset.getAsset().setModel(result.getString("ASSET_MODEL"));
			Asset.getAsset().setSeries(result.getString("ASSET_SERIES"));
			Asset.setStatus(result.getString("STATUS").trim());
			Asset.getAsset().setId(result.getString("ASSET_CD"));

			lstAsset.add(Asset);
		}
		
		return lstAsset;
	}
	
	public String getSql()
	{
		StringBuilder sql  = new StringBuilder();
		
		sql.append(" SELECT AG.ASSET_RFID ,");
		sql.append("    AG.ASSET_CD ,");
		sql.append("    AG.ASSET_NAME ,");
		sql.append("    AG.ASSET_MODEL ,");
		sql.append("    GROUP_CD ,");
		sql.append("    AG.ASSET_SERIES ,");
		sql.append("    ASSET_DEPARTMENT,");
		sql.append("    ISNULL(IC.ASSET_RFID,0) AS STATUS");
		sql.append(" FROM ASSETS_GENERAL AG");
		sql.append(" LEFT JOIN INVENTORY_CHECKING IC");
		sql.append(" ON AG.ASSET_RFID = IC.ASSET_RFID");
		sql.append(" AND AG.ASSET_SERIES = IC.ASSET_SERIES");
		sql.append(" AND AG.ASSET_MODEL = IC.ASSET_MODEL ");
		sql.append(" AND IC.IVN_SESS_CD = ").append("'" + asset.getInventorySession().getInvenotrySessionCD()+"'");
		sql.append(" WHERE");
		sql.append(" 	AG.DELETE_FG = '0'  ");
		
		if(asset != null)
		{
			if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
			{
				sql.append(" 	AND AG.CMPN_CD = ").append("'" + asset.getAsset().getCompany_CD()+"'");
			}
			if(Common.isNotEmpty(asset.getAsset().getDepartment_cd()))
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DEPARTMENT = " ).append("'" +asset.getAsset().getDepartment_cd() +"'");
			}
			
		}
		
		
		return sql.toString();
	}

}
