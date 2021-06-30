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
import com.ams.model.AssetMaintanceModel;
import com.ams.model.AssetMotherAndChilModel;
import com.ams.util.Common;

public class CompanyAssetInternal {
	AssetMotherAndChilModel asset  = null;
	
	public CompanyAssetInternal()
	{
		
	}
	
	public List<AssetMotherAndChilModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetMotherAndChilModel> lstAsset =new ArrayList<AssetMotherAndChilModel>();
		while (result.next()) {
			AssetMotherAndChilModel Asset = new AssetMotherAndChilModel();
			Asset.getAsset().setName(result.getString("ASSET_NAME"));
			Asset.getAsset().setModel(result.getString("ASSET_MODEL"));
			Asset.getAsset().setCompany_CD(result.getString("CMPN_CD"));
			Asset.getCompany().setCompany_cd(result.getString("CMPN_CD"));
			Asset.getCompany().setCompany_shortname(result.getString("CMPN_SHORT_NAME"));
			Asset.setCount(result.getString("SL"));
			Asset.setKeys(result.getString("KEYS"));
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
		
		sql.append(" SELECT TBL.* ");
		sql.append(" ,CONCAT(TBL.ASSET_NAME,TBL.ASSET_MODEL) AS KEYS ");
		sql.append(" FROM (SELECT  ");
		sql.append(" 	  AG.ASSET_NAME ");
		sql.append(" 	  ,AG.ASSET_MODEL ");
		sql.append(" 	  ,AG.CMPN_CD ");
		sql.append(" 	  ,CMP.CMPN_SHORT_NAME ");
		sql.append(" 	  ,COUNT(AG.ASSET_NAME) AS SL ");
		sql.append(" FROM ASSETS_GENERAL AG ");
		sql.append(" INNER JOIN COMPANY CMP  ");
		sql.append(" ON  AG.CMPN_CD = CMP.CMPN_CD ");
		sql.append(" GROUP BY ");
		sql.append(" 	AG.CMPN_CD ");
		sql.append(" 	,AG.ASSET_MODEL ");
		sql.append(" 	,AG.ASSET_NAME ");
		sql.append(" 	,CMP.CMPN_SHORT_NAME) TBL ");
		sql.append(" ORDER BY  ");
		sql.append(" 	TBL.ASSET_NAME ");
		sql.append(" 	,TBL.ASSET_MODEL ");
	
		
		return sql.toString();
	}
}
