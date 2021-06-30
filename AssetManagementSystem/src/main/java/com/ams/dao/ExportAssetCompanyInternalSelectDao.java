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

public class ExportAssetCompanyInternalSelectDao {
	String cmpnCd;
	public ExportAssetCompanyInternalSelectDao(String cmpnCd)
	{
		this.cmpnCd = cmpnCd;
	}
	
	public List<AssetMotherAndChilModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetMotherAndChilModel> lstAsset =new ArrayList<AssetMotherAndChilModel>();
		while (result.next()) {
			AssetMotherAndChilModel Asset = new AssetMotherAndChilModel();
			Asset.getAsset().setName(result.getString("ASSET_NAME").trim());
			Asset.getAsset().setModel(result.getString("ASSET_MODEL").trim());
			Asset.getAsset().setDepartment_cd(result.getString("DEPT_CD").trim());
			Asset.getAsset().setDepartment_name(result.getString("DEPARTMENT_NAME").trim());
			Asset.setCount(result.getString("SL").trim());
			Asset.setKeys(result.getString("KEYS").trim());
			Asset.getAsset().setGroup_id(result.getString("GROUP_CD").trim());
			Asset.getAsset().setGroup_name(result.getString("GROUP_NAME").trim());
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
		
//		sql.append(" SELECT TBL.*  ");
//		sql.append("  ,CONCAT(TBL.ASSET_NAME,TBL.ASSET_MODEL) AS KEYS  ");
//		sql.append("  FROM (SELECT   ");
//		sql.append("  	  AG.ASSET_NAME  ");
//		sql.append("  	  ,AG.ASSET_MODEL  ");
//		sql.append("  	  ,DPT.DEPT_CD  ");
//		sql.append("  	  ,DPT.DEPARTMENT_NAME ");
//		sql.append("  	  ,COUNT(AG.ASSET_NAME) AS SL  ");
//		sql.append("  FROM ASSETS_GENERAL AG  ");
//		sql.append("  INNER JOIN DEPRATMENT DPT   ");
//		sql.append("  ON  AG.CMPN_CD = DPT.CMPN_CD  ");
//		sql.append(" AND AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
//		sql.append("  WHERE AG.CMPN_CD  = '"+this.cmpnCd+"' ");
//		sql.append("  AND  AG.DELETE_FG  = '0' ");
//		sql.append("  GROUP BY  ");
//		sql.append("  	AG.CMPN_CD  ");
//		sql.append("  	,AG.ASSET_MODEL  ");
//		sql.append("  	,AG.ASSET_NAME  ");
//		sql.append("  	 ,DPT.DEPT_CD  ");
//		sql.append(" 	 ,DPT.DEPARTMENT_NAME) TBL  ");
//		sql.append("  ORDER BY   ");
//		sql.append(" 	TBL.ASSET_NAME  ");
//		sql.append(" 	,TBL.ASSET_MODEL  ");
//		
		
		
		sql.append(" 	SELECT TBL.*   ");
		sql.append(" 	 ,CONCAT('',LTRIM(RTRIM(TBL.ASSET_MODEL))) AS KEYS    ");
		sql.append(" 	 FROM (SELECT    ");
		sql.append(" 			AG.GROUP_CD  ");
		sql.append(" 		  ,GA.GROUP_NAME  ");
		sql.append(" 	 	  ,AG.ASSET_NAME   ");
		sql.append(" 	 	  ,TRIM(AG.ASSET_MODEL)   ");
		sql.append(" 	 	  ,DPT.DEPT_CD   ");
		sql.append(" 	 	  ,DPT.DEPARTMENT_NAME  ");
		sql.append(" 	 	  ,COUNT(AG.ASSET_NAME) AS SL   ");
		sql.append(" 	 FROM ASSETS_GENERAL AG   ");
		sql.append(" 	 INNER JOIN DEPRATMENT DPT    ");
		sql.append(" 	 ON  AG.CMPN_CD = DPT.CMPN_CD   ");
		sql.append(" 	AND AG.ASSET_DEPARTMENT = DPT.DEPT_CD  ");
		sql.append(" 	INNER JOIN GROUP_ASSET GA ON   ");
		sql.append(" 	GA.CMPN_CD = AG.CMPN_CD  ");
		sql.append(" 	AND GA.GROUP_ID = AG.GROUP_CD  ");
		sql.append(" 	 WHERE AG.CMPN_CD  = '"+ this.cmpnCd +"' ");
		sql.append(" 	 GROUP BY   ");
		sql.append(" 		AG.GROUP_CD  ");
		sql.append(" 		,GA.GROUP_NAME   ");
		sql.append(" 	 	,LTRIM(AG.ASSET_MODEL)   ");
		sql.append(" 	 	,AG.ASSET_NAME   ");
		sql.append(" 	 	 ,DPT.DEPT_CD   ");
	 	sql.append(" 		 ,DPT.DEPARTMENT_NAME) TBL   ");
	 	sql.append(" 	 ORDER BY    ");
	 	sql.append(" 		TBL.GROUP_NAME  ");
	 	sql.append(" 	 	,TBL.ASSET_NAME   ");
	 	sql.append(" 	 	,TRIM(TBL.ASSET_MODEL)   ");
	 	
		return sql.toString();
	}
}
