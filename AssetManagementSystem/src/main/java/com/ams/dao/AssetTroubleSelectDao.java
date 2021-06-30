package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.BorrowAssetModel;
import com.ams.model.CompanyModel;
import com.ams.model.Department;
import com.ams.model.TroubleAssetModel;
import com.ams.util.Common;

public class AssetTroubleSelectDao {
	TroubleAssetModel asset = null;

	public AssetTroubleSelectDao() {

	}

	public AssetTroubleSelectDao(TroubleAssetModel asset) {
		this.asset = asset;
	}

	public List<TroubleAssetModel> excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		/* System.out.println(getSQL()); */
		result = stmt.executeQuery(getSQL());
		List<TroubleAssetModel> lstAsset =new ArrayList<TroubleAssetModel>();
		while (result.next()) {
			TroubleAssetModel assetValue = new TroubleAssetModel();
			assetValue.getAsset().setId(result.getString("ASSET_CD"));
			assetValue.getAsset().setName(result.getString("ASSET_NAME"));
			assetValue.getAsset().setSeries(result.getString("ASSET_SERIES"));
			assetValue.getAsset().setModel(result.getString("ASSET_MODEL"));
			assetValue.getAsset().setDepartment_name(result.getString("DEPARTMENT_NAME"));
			assetValue.getAsset().setGroup_name(result.getString("GROUP_NAME"));
			assetValue.setUserUse(result.getString("GROUP_NAME"));
			assetValue.setTrouble(result.getString("TROUBLE"));
			assetValue.setDateTrouble(result.getString("DATE_TROUBLE"));
			assetValue.setTimeTrouble(result.getString("TIME_TROUBLE"));
			assetValue.setReason(result.getString("REASON"));
			assetValue.setNote(result.getString("NOTE"));
			assetValue.setUserUse(result.getString("USER_USE"));
			assetValue.setTroubleID(result.getString("TROUBLE_CD"));
			assetValue.setTroubleStatus(result.getString("TROUBLE_STATUS"));
			lstAsset.add(assetValue);
		}
		return lstAsset;
	}
	public TroubleAssetModel FindTroubleByID(String ID) throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSQL());
		result = stmt.executeQuery(getSQL());
		TroubleAssetModel lstAsset =new TroubleAssetModel();
		while (result.next()) {
			
			if(result.getString("TROUBLE_CD").trim().equals(ID.trim()))
			{
				
				TroubleAssetModel assetValue = new TroubleAssetModel();
				assetValue.getAsset().setId(result.getString("ASSET_CD"));
				assetValue.getAsset().setName(result.getString("ASSET_NAME"));
				assetValue.getAsset().setSeries(result.getString("ASSET_SERIES"));
				assetValue.getAsset().setModel(result.getString("ASSET_MODEL"));
				assetValue.getAsset().setDepartment_name(result.getString("DEPARTMENT_NAME"));
				assetValue.getAsset().setGroup_name(result.getString("GROUP_NAME"));
				assetValue.setUserUse(result.getString("GROUP_NAME"));
				assetValue.setTrouble(result.getString("TROUBLE"));
				assetValue.setDateTrouble(result.getString("DATE_TROUBLE"));
				assetValue.setTimeTrouble(result.getString("TIME_TROUBLE"));
				assetValue.setReason(result.getString("REASON"));
				assetValue.setNote(result.getString("NOTE"));
				assetValue.setUserUse(result.getString("USER_USE"));
				assetValue.setTroubleID(result.getString("TROUBLE_CD"));
				assetValue.setTroubleStatus(result.getString("TROUBLE_STATUS"));
				return assetValue;		
			}
			else
			{
				
			}
			
		}
		return lstAsset;
	}

	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("   TROUBLE_CD,");
		sql.append("   TA.ASSET_CD ,");
		sql.append("   AG.ASSET_NAME ,");
		sql.append("   AG.ASSET_MODEL ,");
		sql.append("   AG.ASSET_SERIES,");
		sql.append("   DEPT.DEPARTMENT_NAME,");
		sql.append("   GA.GROUP_NAME,");
		sql.append("   USER_USE ,");
		sql.append("   DATE_TROUBLE,");
		sql.append("   TIME_TROUBLE,");
		sql.append("   TROUBLE ,");
		sql.append("   REASON,");
		sql.append("   NOTE, ");
		sql.append(" USER_USE, ");
		sql.append(" TROUBLE_STATUS  ");
		sql.append(" FROM ");
		sql.append(" TROUBLE_ASSET TA INNER JOIN ASSETS_GENERAL AG ON TA.ASSET_CD = AG.ASSET_CD AND TA.ASSET_RFID = AG.ASSET_RFID INNER JOIN DEPRATMENT DEPT  ON AG.ASSET_DEPARTMENT = DEPT_CD LEFT JOIN  GROUP_ASSET GA ON GA.GROUP_ID = AG.GROUP_CD");
		sql.append(" WHERE  1=1 ");
		if(asset!=null)
		{
			if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
			{
				sql.append("   AND AG.CMPN_CD = ").append("'"+asset.getAsset().getCompany_CD()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getAsset().getModel()))
			{
				sql.append("   AND AG.MODEL = ").append("'"+asset.getAsset().getModel()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getDept().getDept_cd()))
			{
				sql.append("   AND AG.ASSET_DEPARTMENT = ").append("'"+asset.getDept().getDept_cd()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getAsset().getSeries()))
			{
				sql.append("   AND AG.ASSET_SERIES = ").append("'"+asset.getAsset().getSeries()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getAsset().getGroup_id()))
			{
				sql.append("   AND AG.GROUP_CD = ").append("'"+asset.getAsset().getGroup_id()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getDateTrouble()))
			{
				sql.append("   AND DATE_TROUBLE >= ").append("'"+asset.getDateTrouble()+"'");
			}
			if(Common.isNotCheckEmpty(asset.getDateTroubleEnd()))
			{
				sql.append("   AND DATE_TROUBLE < ").append("'"+asset.getDateTroubleEnd()+"'");
			}
			
		}
		
		
		return sql.toString();
	}
}
