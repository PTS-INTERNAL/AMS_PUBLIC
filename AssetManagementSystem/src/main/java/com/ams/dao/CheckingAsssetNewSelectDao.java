package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetManagementTable;
import com.ams.model.CheckingAssetNew;



public class CheckingAsssetNewSelectDao {
	
	CheckingAssetNew checkingAssetNew  =null;
	
	public CheckingAsssetNewSelectDao( CheckingAssetNew CheckingAssetNew)
	{
		this.checkingAssetNew = CheckingAssetNew;
	}
	
	
	
	public List<AssetManagementTable> excuteAssetManagementTable() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetManagementTable> lstChecking =new ArrayList<AssetManagementTable>();
		while (result.next()) {
			AssetManagementTable assetTable = new AssetManagementTable();
			assetTable.setInventorySessionCD(result.getString("CHECKING_CD"));
			assetTable.setAsset_Name(result.getString("ASSET_NAME"));
			assetTable.setDepartment(result.getString("DEPARTMENT"));
			assetTable.setInventory_Date(result.getString("DATE_CREATE"));
			assetTable.setUserName(result.getString("NAME"));
			assetTable.setUserChecking(result.getString("EMPLOYEE_CD"));
			assetTable.setStatus(result.getString("STATUS"));
			if(result.getString("STATUS").trim().equals("2"))
			{
				assetTable.setStatus_name("Báo mới");
			}
			
			
			lstChecking.add(assetTable);
		}
		
		return lstChecking;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT *");
		sql.append(" FROM");
		sql.append(" CHECKING_ASSET_NEW CSN");
		sql.append(" INNER JOIN ");
		sql.append(" USER_SYSTEM URS ");
		sql.append(" ON ");
		sql.append(" CSN.USER_INSERT = URS.EMPLOYEE_CD ");
		sql.append(" WHERE");
		sql.append(" 1=1");
		if(checkingAssetNew !=null)
		{
			if(checkingAssetNew.getChecking_session()!=null && checkingAssetNew.getChecking_session().trim().length()>0)
			{
				sql.append(" AND CSN.CHECKING_CD= ").append("'" + checkingAssetNew.getChecking_session() +"'");
			}
		}
		
		
		return sql.toString();
	}
}
