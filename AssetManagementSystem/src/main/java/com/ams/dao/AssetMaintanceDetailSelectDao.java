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
import com.ams.model.AssetMaintanceModel;
import com.ams.util.Common;

public class AssetMaintanceDetailSelectDao {
	
	AssetMaintanceModel asset  = null;
	
	public AssetMaintanceDetailSelectDao(AssetMaintanceModel asset)
	{
		this.asset = asset;
	}
	
	public List<AssetMaintanceModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetMaintanceModel> lstAsset =new ArrayList<AssetMaintanceModel>();
		while (result.next()) {
			AssetMaintanceModel Asset = new AssetMaintanceModel();
			Asset.setDay(result.getString("DAY_S"));
			Asset.setMonth(result.getString("MONTH_S"));
			Asset.setYear(result.getString("YEAR_S"));
			
			lstAsset.add(Asset);
		}
		
		return lstAsset;
	}
	
	public String getIDSetup()
	{
		DateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmSS");
		Date date = new Date();
        String ID = sdf.format(date);
		return ID;
	}
	
	public String getSql()
	{
		StringBuilder sql  = new StringBuilder();
		
		sql.append(" SELECT	  DAY_S , ");
		sql.append(" MONTH_S , ");
		sql.append(" YEAR_S ");
		sql.append(" FROM MAINTAINCE_DETAIL MD ");
		sql.append(" WHERE 1=1 ");
		if(asset != null)
		{
			if(asset.getAsset().getModel()!=null && asset.getAsset().getModel().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	MD.ASSET_MODEL = " ).append("'" + asset.getAsset().getModel().trim()+"'");
			}
			if(asset.getAsset().getSeries()!=null && asset.getAsset().getSeries().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	MD.ASSET_SERIES = " ).append("'" +asset.getAsset().getSeries().trim() +"'");
			}
			if(asset.getMonth()!=null && asset.getMonth().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	MD.MONTH_S = " ).append("'" +asset.getMonth().trim() +"'");
			}
		}
		
		return sql.toString();
	}
}
