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

public class AssetMaintanceSelectDao {
	
	AssetMaintanceModel asset  = null;
	
	public AssetMaintanceSelectDao(AssetMaintanceModel asset)
	{
		this.asset = asset;
	}
	
	public List<AssetMaintanceModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetMaintanceModel> lstAsset =new ArrayList<AssetMaintanceModel>();
		while (result.next()) {
			AssetMaintanceModel Asset = new AssetMaintanceModel();
			Asset.SetSTT(result.getString("STT"));
			Asset.setDay(result.getString("LAST_MAINTAINCE"));
			Asset.getAsset().setRFID(result.getString("ASSET_RFID"));
			Asset.getAsset().setName(result.getString("ASSET_NAME"));
			Asset.getAsset().setModel(result.getString("ASSET_MODEL"));
			Asset.getAsset().setSeries(result.getString("ASSET_SERIES"));
			Asset.getAsset().setDepartment_cd(result.getString("ASSET_DEPARTMENT"));
			Asset.getAsset().setDepartment_name(result.getString("DEPARTMENT_NAME"));
			Asset.getAsset().setGroup_name(result.getString("GROUP_NAME"));
			Asset.getAsset().setGroup_id(result.getString("GROUP_CD"));
			Asset.getAsset().setId(result.getString("ASSET_CD"));
			Asset.getAsset().setCompany_CD(result.getString("CMPN_CD"));
			Asset.getAsset().setMaintaince(result.getString("ASSET_MAINTAINCE"));
			Asset.setMonthMaintaince(result.getString("LAST_MAINTAINCE"));
			Asset.getAsset().setId(result.getString("ASSET_CD"));
			Asset.setUserInsert(result.getString("USER_INSERT"));
			Asset.SetContent(result.getString("MAINTAIN_CONTENT"));
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
		
		sql.append(" SELECT	 ROW_NUMBER() OVER(ORDER BY AG.ASSET_RFID) as STT, AG.ASSET_RFID , ");
		sql.append(" AG.ASSET_CD , ");
		sql.append(" ASSET_NAME , ");
		sql.append("   AG.ASSET_MODEL , ");
		sql.append(" GROUP_CD , ");
		sql.append(" AG.ASSET_SERIES , ");
		sql.append(" ASSET_DEPARTMENT , ");
		sql.append(" ASSET_ACCOUNTANT , ");
		sql.append(" ASSET_DATE_INVEST , ");
		sql.append(" ASSET_PRICE, ");
		sql.append(" ASSET_NOTE, ");
		sql.append(" ASSET_DATE_END , ");
		sql.append(" GROUP_NAME , ");
		sql.append("  DEPARTMENT_NAME , ");
		sql.append(" AG.CMPN_CD AS CMPN_CD, ");
		sql.append("   ASSET_MAINTAINCE,TBL1.MAINTAIN_CONTENT,TBL1.USER_INSERT, ");
		sql.append("   MAINTAINCE_DT AS LAST_MAINTAINCE ");
		sql.append(" FROM ASSETS_GENERAL AG ");
		sql.append(" INNER JOIN DEPRATMENT DPT ON AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
		sql.append(" LEFT JOIN GROUP_ASSET GA ON AG.GROUP_CD = GA.GROUP_ID ");
		
		
		sql.append(" LEFT JOIN ( ");
		sql.append(" SELECT   MAINTAIN_CONTENT ,USER_INSERT,");
		sql.append(" ASSET_CD, ");
		sql.append(" ASSET_RFID, ");
		sql.append(" ASSET_MODEL, ");
		sql.append(" 	ASSET_SERIES, ");
		sql.append(" MAX(MAINTAINCE_DT) AS MAINTAINCE_DT ");
		sql.append(" FROM MAINTAINCE ");
		sql.append(" GROUP BY  MAINTAIN_CONTENT, USER_INSERT, ");
		sql.append(" ASSET_CD, ");
		sql.append(" ASSET_RFID, ");
		sql.append(" ASSET_MODEL, ");
		sql.append(" ASSET_SERIES ");
		sql.append(" ) AS TBL1  ");
		sql.append(" ON  TBL1.ASSET_CD = AG.ASSET_CD  ");
		sql.append(" AND TBL1.ASSET_MODEL = AG.ASSET_MODEL  ");
		sql.append(" AND TBL1.ASSET_SERIES = AG.ASSET_SERIES ");
		sql.append(" WHERE 1=1 ");
		if(asset != null)
		{
			if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
			{
				sql.append(" 	AND AG.CMPN_CD = ").append("'" + asset.getAsset().getCompany_CD()+"'");
			}
			if(asset.getAsset().getRFID()!=null && asset.getAsset().getRFID().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_RFID = " ).append("'" + asset.getAsset().getRFID().trim()+"'");
			}
			if(asset.getAsset().getName()!=null && asset.getAsset().getName().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_NAME = " ).append("N'" +asset.getAsset().getName().trim() +"'");
			}
			if(asset.getAsset().getModel()!=null && asset.getAsset().getModel().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_MODEL = " ).append("'" + asset.getAsset().getModel().trim()+"'");
			}
			if(asset.getAsset().getSeries()!=null && asset.getAsset().getSeries().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_SERIES = " ).append("'" +asset.getAsset().getSeries().trim() +"'");
			}
			if(asset.getAsset().getDepartment_cd()!=null && asset.getAsset().getDepartment_cd().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DEPARTMENT = " ).append("'" +asset.getAsset().getDepartment_cd().trim() +"'");
			}
			
			if(asset.getAsset().getGroup_id()!=null && asset.getAsset().getGroup_id().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.GROUP_CD = " ).append("'" + asset.getAsset().getGroup_id()+"'");
			}
		}
		if(asset != null)
		{
			if(asset.getRowPage()!=null && asset.getRowPage().trim().length()>0)
			{
				sql.insert(0, " select * from("  );
				sql.append("  ) as BYPAGE WHERE STT >= ");
				sql.append(Integer.parseInt(asset.getRowPage())*13-12);
				sql.append("  AND STT <= ");
				sql.append(Integer.parseInt(asset.getRowPage())*13);
			}
		}
		return sql.toString();
	}
}
