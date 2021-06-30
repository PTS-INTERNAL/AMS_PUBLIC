package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralFormSearch;
import com.ams.model.AssetGeneralModel;
import com.ams.util.Common;
import com.ams.util.SystemControl;


public class AssetGeneralSelectDao {
	AssetGeneralFormSearch form = null;
	AssetGeneralModel assetModel = null;
	public AssetGeneralSelectDao(AssetGeneralFormSearch form)
	{
		this.form  = form;
	}
	
	public AssetGeneralSelectDao(AssetGeneralModel assetModel)
	{
		this.assetModel  = assetModel;
	}
	public AssetGeneralSelectDao()
	{
		
	}
	
	public List<AssetGeneralModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetGeneralModel> lstAsset =new ArrayList<AssetGeneralModel>();
		while (result.next()) {
			AssetGeneralModel Asset = new AssetGeneralModel();
			Asset.setRFID(result.getString("ASSET_RFID"));
			Asset.setName(result.getString("ASSET_NAME"));
			Asset.setModel(result.getString("ASSET_MODEL"));
			Asset.setSeries(result.getString("ASSET_SERIES"));
			Asset.setDepartment_cd(result.getString("ASSET_DEPARTMENT"));
			Asset.setAccountant_CD(result.getString("ASSET_ACCOUNTANT"));
			Asset.setDateStart(result.getString("ASSET_DATE_INVEST"));
			Asset.setDateEnd(result.getString("ASSET_DATE_END"));
			Asset.setPrice(result.getString("ASSET_PRICE"));
			Asset.setNote(result.getString("ASSET_NOTE"));
			Asset.setDepartment_name(result.getString("DEPARTMENT_NAME"));
			Asset.setGroup_name(result.getString("GROUP_NAME"));
			Asset.setGroup_id(result.getString("GROUP_CD"));
			Asset.setId(result.getString("ASSET_CD"));
			Asset.setCompany_CD(result.getString("CMPN_CD"));
			Asset.setOriginal(result.getString("ASSET_ORIGINAL"));
			Asset.setMaintaince(result.getString("ASSET_MAINTAINCE"));
			Asset.setStatus(result.getString("ASSET_STATUS"));
			Asset.setInline(result.getString("INLINE"));
			Asset.setCustomer(result.getString("CUSTOMER"));
			Asset.setContract(result.getString("CONTRACT"));
			lstAsset.add(Asset);
		}
		
		return lstAsset;
	}
	
	public List<AssetGeneralModel> FindAssetFromGroupAndDepartment(String GroupID,String DeptID,String model) throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetGeneralModel> lstAsset =new ArrayList<AssetGeneralModel>();
		while (result.next()) {
			if(result.getString("GROUP_NAME").trim().equals(GroupID.trim()) && result.getString("DEPARTMENT_NAME").trim().equals(DeptID.trim()))
			{
				AssetGeneralModel Asset = new AssetGeneralModel();
				Asset.setRFID(result.getString("ASSET_RFID"));
				Asset.setName(result.getString("ASSET_NAME"));
				Asset.setModel(result.getString("ASSET_MODEL"));
				Asset.setSeries(result.getString("ASSET_SERIES"));
				Asset.setDepartment_cd(result.getString("ASSET_DEPARTMENT"));
				Asset.setAccountant_CD(result.getString("ASSET_ACCOUNTANT"));
				Asset.setDateStart(result.getString("ASSET_DATE_INVEST"));
				Asset.setDateEnd(result.getString("ASSET_DATE_END"));
				Asset.setPrice(result.getString("ASSET_PRICE"));
				Asset.setNote(result.getString("ASSET_NOTE"));
				Asset.setDepartment_name(result.getString("DEPARTMENT_NAME"));
				Asset.setGroup_name(result.getString("GROUP_NAME"));
				Asset.setGroup_id(result.getString("GROUP_CD"));
				Asset.setId(result.getString("ASSET_CD"));
				Asset.setCompany_CD(result.getString("CMPN_CD"));
				Asset.setOriginal(result.getString("ASSET_ORIGINAL"));
				Asset.setMaintaince(result.getString("ASSET_MAINTAINCE"));
				Asset.setStatus(result.getString("ASSET_STATUS"));
				lstAsset.add(Asset);
			}
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
		
		sql.append(" SELECT ");
		sql.append(" ASSET_RFID");
		sql.append("  ,ASSET_CD");
		sql.append("  ,ASSET_NAME");
		sql.append("  ,ASSET_MODEL");
		sql.append("  ,ASSET_ORIGINAL");
		sql.append("  ,ASSET_MAINTAINCE");
		sql.append("  ,GROUP_CD");
		sql.append("  ,ASSET_SERIES");
		sql.append("  ,ASSET_DEPARTMENT");
		sql.append("  ,ASSET_ACCOUNTANT");
		sql.append("  ,ASSET_DATE_INVEST");
		sql.append("  ,ASSET_PRICE,ASSET_NOTE,ASSET_DATE_END");
		sql.append("  ,GROUP_NAME");
		sql.append("  , DEPARTMENT_NAME");
		sql.append("  , AG.CMPN_CD AS CMPN_CD");
		sql.append("  , AG.ASSET_STATUS AS ASSET_STATUS");
		sql.append("  , AG.INLINE");
		sql.append("  , AG.CUSTOMER");
		sql.append("  , AG.CONTRACT");
		sql.append(" FROM");
		sql.append(" 	ASSETS_GENERAL AG");
		sql.append(" INNER JOIN DEPRATMENT DPT");
		sql.append(" ON  AG.ASSET_DEPARTMENT = DPT.DEPT_CD");
		sql.append("  LEFT JOIN GROUP_ASSET GS");
		sql.append("  ON GS.GROUP_ID = AG.GROUP_CD");
		sql.append(" WHERE");
		sql.append(" 	AG.DELETE_FG = '0'  ");
		
		if(form != null)
		{
			if(Common.isNotEmpty(form.getCompany_CD()))
			{
				sql.append(" 	AND AG.CMPN_CD = ").append("'" + form.getCompany_CD()+"'");
			}
			if(form.getRFID()!=null && form.getRFID().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_RFID = " ).append("'" + form.getRFID().trim()+"'");
			}
			if(form.getName()!=null && form.getName().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_NAME = " ).append("N'" +form.getName().trim() +"'");
			}
			if(form.getModel()!=null && form.getModel().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_MODEL = " ).append("'" + form.getModel().trim()+"'");
			}
			if(form.getSeries()!=null && form.getSeries().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_SERIES = " ).append("'" +form.getSeries().trim() +"'");
			}
			if(form.getDepartment_cd()!=null && form.getDepartment_cd().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DEPARTMENT = " ).append("'" +form.getDepartment_cd().trim() +"'");
			}
			if(form.getAccountant_CD()!=null && form.getAccountant_CD().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_ACCOUNTANT = " ).append("'" +form.getAccountant_CD().trim() +"'");
			}
			if(form.getDateStart_Start()!=null && form.getDateStart_Start().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DATE_INVEST >= " ).append("'" + form.getDateStart_Start().trim()+"'");
			}
			if(form.getDateStart_End()!=null && form.getDateStart_End().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DATE_INVEST <= " ).append("'" + form.getDateStart_End().trim()+"'");
			}
			if(form.getDateEnd_Start()!=null && form.getDateEnd_Start().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DATE_INVEST <= " ).append("'" +form.getDateEnd_Start().trim() +"'");
			}
			if(form.getDateEnd_End()!=null && form.getDateEnd_End().trim().length()>0)
			{
				sql.append(" OR");
				sql.append(" 	AG.ASSET_DATE_END <= " ).append("'" +form.getDateEnd_End().trim() +"'");
			}
			if(form.getPriceStart()!=null && form.getPriceStart().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_PRICE >=" ).append("'" + form.getPriceStart()+"'");
			}
			if(form.getPriceEnd()!=null && form.getPriceEnd().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_PRICE <= " ).append("'" + form.getPriceEnd()+"'");
			}
			if(form.getGroup_id()!=null && form.getGroup_id().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.GROUP_CD = " ).append("'" + form.getGroup_id()+"'");
			}
		}
		
		if(assetModel != null)
		{
			if(Common.isNotEmpty(assetModel.getCompany_CD()))
			{
				sql.append(" 	AND AG.CMPN_CD = ").append("'" + assetModel.getCompany_CD()+"'");
			}
			if(assetModel.getRFID()!=null && assetModel.getRFID().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_RFID = " ).append("'" + assetModel.getRFID().trim()+"'");
			}
			if(assetModel.getName()!=null && assetModel.getName().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_NAME = " ).append("N'" +assetModel.getName().trim() +"'");
			}
			if(assetModel.getModel()!=null && assetModel.getModel().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_MODEL = " ).append("'" + assetModel.getModel().trim()+"'");
			}
			if(assetModel.getSeries()!=null && assetModel.getSeries().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_SERIES = " ).append("'" +assetModel.getSeries().trim() +"'");
			}
			if(assetModel.getDepartment_cd()!=null && assetModel.getDepartment_cd().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_DEPARTMENT = " ).append("'" +assetModel.getDepartment_cd().trim() +"'");
			}
			if(assetModel.getAccountant_CD()!=null && form.getAccountant_CD().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.ASSET_ACCOUNTANT = " ).append("'" +assetModel.getAccountant_CD().trim() +"'");
			}
			
			if(assetModel.getGroup_id()!=null && assetModel.getGroup_id().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	AG.GROUP_CD = " ).append("'" + assetModel.getGroup_id()+"'");
			}
		}
		
		return sql.toString();
	}
}
