package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetManagementTable;
import com.ams.model.InventoryChecking;

public class InventoryCheckingSelectDao {
	
	InventoryChecking InventoryChecking = null;
	public InventoryCheckingSelectDao()
	{
		
	}
	public InventoryCheckingSelectDao(InventoryChecking inventoryChecking)
	{
		this.InventoryChecking = inventoryChecking;
	}
	
	public List<AssetManagementTable> excuteAssetManagementTable() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSQLAssetManagementTable());
		result = stmt.executeQuery(getSQLAssetManagementTable());
		List<AssetManagementTable> lstChecking =new ArrayList<AssetManagementTable>();
		while (result.next()) {
			AssetManagementTable assetTable = new AssetManagementTable();
			assetTable.setInventorySessionCD(result.getString("INVENTORY_CHECK"));
			assetTable.setAsset_Name(result.getString("ASSET_NAME"));
			assetTable.setDepartment(result.getString("ASSET_DEPARTMENT"));
			assetTable.setInventory_Date(result.getString("NGAYCAPNHAT"));
			assetTable.setStatus(result.getString("STATUS"));
			assetTable.setHientrang(result.getString("HIENTRANG"));
			lstChecking.add(assetTable);
		}
		
		return lstChecking;
	}
	
	public List<InventoryChecking> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<InventoryChecking> lstChecking =new ArrayList<InventoryChecking>();
		while (result.next()) {
			InventoryChecking invChecking = new InventoryChecking();
			invChecking.setAsset_Rfid(result.getString("ASSET_RFID"));
			invChecking.setInventorySessionChecking_CD(result.getString("INVENTORY_SESSION_CD"));
			invChecking.setInventory_Date(result.getString("INVENTORY_DATE"));
			invChecking.setInventorySessionCD(result.getString("INVENTORY_CHECK"));
			invChecking.setUserChecking(result.getString("USER_CHECK"));
			
			lstChecking.add(invChecking);
		}
		
		return lstChecking;
	}
	
	public String getSQLAssetManagementTable()
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (  ");
		sql.append("SELECT TBL1.*,  ");
		sql.append("		FORMAT(GETDATE(),'yyyy/MM/dd') AS NGAYCAPNHAT, ");
		sql.append("		ISNULL(IV.STATUS, 4) AS STATUS,ISNULL(INVENTORY_CHECK, '"+InventoryChecking.getInventorySessionCD()+"') AS INVENTORY_CHECK ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT  ");
			sql.append("	ASSET_RFID, ");
			sql.append("	ASSET_NAME, ");
			sql.append("	ASSET_DEPARTMENT, ");
			sql.append("	N'BIÊN CHẾ' as  HIENTRANG ");
		sql.append("FROM ASSETS_GENERAL ");
		sql.append("WHERE CMPN_CD =  ").append("'"+InventoryChecking.getCompany_cd()+"' ");
		sql.append("UNION  ");
		sql.append("SELECT  ");
			sql.append("	BA.ASSET_RFID AS ASSET_RFID ");
			sql.append("   ,ASSET_NAME  ");
			sql.append("   ,BORROW_DEPT  as ASSET_DEPARTMENT, ");
		sql.append("	   N'MƯỢN' as  HIENTRANG ");
		sql.append("FROM  ");
		sql.append("BORROW_ASSET BA, ASSETS_GENERAL AG ");
		sql.append("WHERE BA.ASSET_RFID = AG.ASSET_RFID ");
		sql.append("AND BA.BORROW_CMPN_CD =   ").append("'"+InventoryChecking.getCompany_cd()+"' ");
		sql.append("UNION ");
		sql.append("SELECT  ");
			   sql.append(" RA.ASSET_RFID AS ASSET_RFID ");
			   sql.append(",ASSET_NAME  ");
			   sql.append(",DEP.DEPARTMENT_NAME  as ASSET_DEPARTMENT, ");
			  sql.append(" N'THUÊ' as  HIENTRANG ");
		sql.append("FROM RENT_ASSET RA, DEPRATMENT DEP ");
		sql.append("WHERE DEP.DEPT_CD = DEPARTMENT_CD ");
		sql.append("AND RA.CMPN_CD = "+"'"+InventoryChecking.getCompany_cd()+"') TBL1 ");
		sql.append("LEFT JOIN  ");
		sql.append("INVENTORY IV ");
		sql.append("ON IV.ASSET_RFID = TBL1.ASSET_RFID ");
		sql.append("AND IV.INVENTORY_CHECK = "+"'"+InventoryChecking.getInventorySessionCD()+"' ");
		sql.append("UNION ");
		sql.append("SELECT  ");
				sql.append("ASSET_RFID, ");
				sql.append("ASSET_NAME, ");
				sql.append("DEPARTMENT, ");
				sql.append("N'BÁO MỚI' as  HIENTRANG, ");
				sql.append("FORMAT(GETDATE(),'yyyy/MM/dd') AS NGAYCAPNHAT, ");
				sql.append("STATUS,ISNULL(CHECKING_CD, '"+InventoryChecking.getInventorySessionCD()+"') AS INVENTORY_CHECK ");
		sql.append("FROM CHECKING_ASSET_NEW CAN ");
		sql.append("WHERE CAN.CMPN_CD =  "+"'"+InventoryChecking.getCompany_cd()+"'");
		sql.append("AND CAN.CHECKING_CD = "+"'"+InventoryChecking.getInventorySessionCD()+"' ");
		sql.append(" ) AS TBL2 ");
		sql.append(" WHERE 1=1 ");
		if(InventoryChecking.getAsset_name()!=null && InventoryChecking.getAsset_name().trim().length()>0)
		{
			sql.append(" AND ASSET_NAME = '" + InventoryChecking.getAsset_name()+"'");
		}
		if(InventoryChecking.getDepartment()!=null && InventoryChecking.getDepartment().trim().length()>0)
		{
			sql.append(" AND ASSET_DEPARTMENT = '" + InventoryChecking.getDepartment()+"'");
		}
	return sql.toString();
	
				
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		InventoryChecking inv = this.InventoryChecking;
		
		sql.append(" SELECT *");
		sql.append(" FROM");
		sql.append(" 	INVENTORY");
		sql.append(" WHERE");
		sql.append(" 	1 = 1");
		if( inv != null)
		{
			if(inv.getAsset_Rfid() != null && inv.getAsset_Rfid().trim().length()>0)
			{
				sql.append(" 	AND ASSET_RFID = ").append("'" + inv.getAsset_Rfid()+ "'");
			}
			if(inv.getInventorySessionCD()!=null && inv.getInventorySessionCD().trim().length()>0)
			{
				sql.append(" 	AND INVENTORY_CHECK = ").append("'" + inv.getInventorySessionCD()+ "'");
			}
			if(inv.getInventorySessionChecking_CD()!=null && inv.getInventorySessionChecking_CD().trim().length()>0)
			{
				sql.append(" 	AND INVENTORY_SESSION_CD = ").append("'" + inv.getInventorySessionChecking_CD()+ "'");
			}
			if(inv.getInventory_Date()!=null && inv.getInventory_Date().trim().length()>0)
			{
				sql.append(" 	AND INVENTORY_DATE = ").append("'" + inv.getInventory_Date()+ "'");
			}
			if(inv.getUserChecking()!=null && inv.getUserChecking().trim().length()>0)
			{
				sql.append(" 	AND USER_CHECK = ").append("'" + inv.getUserChecking()+ "'");
			}
		}
		
		return sql.toString();
	}
	

}
