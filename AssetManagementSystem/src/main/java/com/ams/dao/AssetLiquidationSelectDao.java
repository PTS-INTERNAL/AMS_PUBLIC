package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.CompanyModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class AssetLiquidationSelectDao {
	AssetLiquidationModel asset;
	
	public AssetLiquidationSelectDao()
	{
		asset=null;
	}
	

	public AssetLiquidationSelectDao(AssetLiquidationModel asset)
	{
		this.asset = asset;
	}
	
	public List<AssetLiquidationModel> excute() throws SQLException, ParseException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetLiquidationModel> lstAss =new ArrayList<AssetLiquidationModel>();
		while (result.next()) {
			AssetLiquidationModel ass = new AssetLiquidationModel();
			ass.setLiquidation_Cd(result.getString("LIQUIDATION_CD"));
			if(result.getString("LIQUIDATION_CD")!=null ||result.getString("LIQUIDATION_CD")!=""  )
			{
				AssetGeneralModel assmodel=new AssetGeneralModel();
				assmodel.setRFID(result.getString("ASSET_RFID"));
			    AssetGeneralSelectDao selectass=new AssetGeneralSelectDao(assmodel);
				ass.setAsset(selectass.excute().get(0));
			}
			
			ass.setDateProposal(Common.ConvertStringToDateStr(result.getString("INSERT_DT"),"yyyyMMdd","dd/MM/yyyy"));
			ass.setReason(result.getString("REASON"));
			ass.setStatus(result.getString("STATUS"));
			ass.setNote(result.getString("NOTE"));
			ass.setList_ID(result.getString("LIST_ID"));
			if(Common.isNotEmpty(result.getString("APPROVE_DT")))
			{
				try {
				ass.setApproveDT(Common.ConvertStringToDateStr(result.getString("APPROVE_DT"),"yyyyMMdd","dd/MM/yyyy"));
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				ass.setApproveDT(result.getString("APPROVE_DT"));

			}
			ass.setUserApprove(result.getString("USER_APPROVE"));
			ass.setUserInsert(result.getString("USER_INSERT"));
			ass.setInsertDT(result.getString("INSERT_DT"));
			ass.setReason_not_allow(result.getString("REASON_NOT_ALLOW"));
			lstAss.add(ass);
		}
		return lstAss;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT AL.LIQUIDATION_CD,AL.ASSET_RFID,AL.USER_INSERT,AL.INSERT_DT,AL.USER_UPDATE, AL.UPDATE_DT,AL.USER_APPROVE,AL.APPROVE_DT,AL.STATUS,AL.NOTE,AL.LIST_ID,AL.REASON, AL.REASON_NOT_ALLOW FROM ASSETS_LIQUIDATION as AL ");
		
		sql.append(" INNER JOIN ASSETS_GENERAL as ASS on AL.ASSET_RFID=ASS.ASSET_RFID");
		
		sql.append(" WHERE 1=1 ");	
		if(asset != null)
		{
			if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
			{
				sql.append(" AND ASS.CMPN_CD =  ").append("'" +asset.getAsset().getCompany_CD()+ "'");	
			}
			if(Common.isNotEmpty(asset.getLiquidation_Cd()))
			{
				sql.append(" AND AL.LIQUIDATION_CD =  ").append("'" +asset.getLiquidation_Cd()+ "'");	
			}
			if(Common.isNotEmpty(asset.getList_ID()))
			{
				sql.append(" AND AL.LIST_ID =  ").append("'" +asset.getList_ID()+ "'");	
			}
			if(Common.isNotEmpty(asset.getAsset().getId()))
			{
				sql.append(" AND ASS.RFID =  ").append("'" +asset.getAsset().getRFID()+ "'");	
			}
		}
		return sql.toString();
	}
	
}
