package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CORBA.COMM_FAILURE;

import com.ams.database.DatabaseConnection;
import com.ams.model.AssetGeneralModel;
import com.ams.model.InventoryCheckingRealtimeModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class InventoryCheckingResultSelectDao {
	
	InventoryCheckingRealtimeModel asset;
	
	public InventoryCheckingResultSelectDao(InventoryCheckingRealtimeModel asset)
	{
		this.asset = asset;
	}
	
	public List<InventoryCheckingRealtimeModel> excute() throws SQLException, ParseException
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
			Asset.setInventory_Checking_Cd(result.getString("INV_CHECKING_CD"));
			Asset.getAsset().setRFID(result.getString("ASSET_RFID"));
			Asset.getAsset().setName(result.getString("ASSET_NAME"));
			Asset.getAsset().setModel(result.getString("ASSET_MODEL"));
			Asset.getAsset().setSeries(result.getString("ASSET_SERIES"));
			Asset.setIvn_Session_Cd(result.getString("IVN_SESS_CD"));
			Asset.setStatus(result.getString("STATUS").trim());
			Asset.getAsset().setId(result.getString("ASSET_CD"));
			Asset.getGroup().setGroup_name(result.getString("GROUP_NA"));
			Asset.getDeptChecking().setDept_name(result.getString("DEPT_CHECK_NA"));
			Asset.getDeptChecking().setDept_cd(result.getString("DEPT_CHECK_CD"));
			Asset.getDeptUsing().setDept_name(result.getString("DEPT_USE_NA"));
			Asset.getDeptUsing().setDept_cd(result.getString("DEPT_USE_NA"));
            Asset.getAsset().setUser_insert_cd(result.getString("USER_INSERT"));
            Asset.setExpain_dt(result.getString("EXPLAIN_DT"));
            Asset.setStatus_Explain(result.getString("STATUS_EXPLAIN"));
           
            
            if(Common.isNotEmpty(result.getString("EXPLAIN")))
            {
            	 Asset.setExplain(result.getString("EXPLAIN"));
            }
            else
            {
            	 Asset.setExplain("");
            }
            
            Asset.setUser_explain(result.getString("USER_EXPLAIN"));
            String dateInsert = result.getString("INSERT_DT");
            if(Common.isNotEmpty(dateInsert))
            {
            	String dateUserInsert =Common.ConvertStringToDateStr(dateInsert, ParamsConstants.DATE_DB_FORMAT	, ParamsConstants.DATE_VIEW_FORMAT);
            	Asset.getAsset().setInsert_dt(dateUserInsert);
            }
            else
            {
            	Asset.getAsset().setInsert_dt("");
            }
            Asset.setStatus(result.getString("STATUS_CHECKING"));
            
			lstAsset.add(Asset);
		}
		
		return lstAsset;
	}
	
	public String getSql()
	{
		StringBuilder sql  = new StringBuilder();
		
		sql.append(" SELECT S.* FROM (SELECT INV_CHECKING_CD, IC.STATUS_EXPLAIN, IC.IVN_SESS_CD,IC.EXPLAIN, IC.EXPLAIN_DT, IC.USER_EXPLAIN, AG.ASSET_RFID ,");
		sql.append("    AG.ASSET_CD ,");
		sql.append("    AG.ASSET_NAME ,");
		sql.append("    AG.ASSET_MODEL ,");
		sql.append("    GROUP_CD ,");
		sql.append("    AG.ASSET_SERIES ,");
		sql.append("    ASSET_DEPARTMENT, ");
		sql.append("  GA.GROUP_NAME AS GROUP_NA,");
		sql.append("    DPT.DEPARTMENT_NAME AS DEPT_CHECK_NA, ");
		sql.append("     DPT.DEPT_CD AS DEPT_CHECK_CD, ");
		sql.append("    DPT2.DEPT_CD AS DEPT_USE_CD, ");
		sql.append("     DPT2.DEPARTMENT_NAME AS DEPT_USE_NA, ");
		sql.append("     ISNULL(IC.ASSET_RFID, 0) AS STATUS, ");
		sql.append("    ISNULL(IC.STATUS_CHECKING, '0') AS STATUS_CHECKING, ");
		sql.append("    IC.USER_INSERT,");
		sql.append("    IC.INSERT_DT");
		sql.append(" FROM ASSETS_GENERAL AG");
		sql.append(" LEFT JOIN INVENTORY_CHECKING IC");
		sql.append(" ON AG.ASSET_RFID = IC.ASSET_RFID");
		sql.append(" AND AG.ASSET_SERIES = IC.ASSET_SERIES");
		sql.append(" AND AG.ASSET_MODEL = IC.ASSET_MODEL ");
		if(Common.isNotCheckEmpty(asset.getInventorySession().getInvenotrySessionCD()))
		{
			sql.append(" AND IC.IVN_SESS_CD = ").append("'" + asset.getInventorySession().getInvenotrySessionCD()+"'");
		}
		sql.append(" LEFT JOIN GROUP_ASSET GA ON GA.GROUP_ID = AG.GROUP_CD ");
		sql.append(" 		LEFT JOIN DEPRATMENT DPT on DPT.DEPT_CD = IC.DEPT_CHECKING ");
		sql.append(" 		LEFT JOIN DEPRATMENT DPT2 on DPT2.DEPT_CD = AG.ASSET_DEPARTMENT ");
		sql.append(" WHERE");
		sql.append(" 	AG.DELETE_FG = '0'  ");
		if(Common.isNotEmpty(asset.getAsset().getDateStart_Start()))
		{
			sql.append(" AND");
			sql.append(" 	IC.INSERT_DT >= " ).append("'" + asset.getAsset().getDateStart_Start().trim()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getDateStart_End()))
		{
			sql.append(" AND");
			sql.append(" 	IC.INSERT_DT <= " ).append("'" + asset.getAsset().getDateStart_End().trim()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getCompany_CD()))
		{
			sql.append(" 	AND AG.CMPN_CD = ").append("'" + asset.getAsset().getCompany_CD()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getDepartment_cd()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_DEPARTMENT = " ).append("'" +asset.getAsset().getDepartment_cd() +"'");
		}
		if(Common.isNotEmpty(asset.getInventory_Checking_Cd()))
		{
			sql.append(" AND");
			sql.append(" 	INV_CHECKING_CD = " ).append("'" +asset.getInventory_Checking_Cd() +"'");
		}
			
		if(Common.isNotEmpty(asset.getAsset().getRFID()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_RFID = " ).append("'" +asset.getAsset().getRFID() +"'");
		}
			
		if(Common.isNotEmpty(asset.getAsset().getName()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_NAME = " ).append("N'" +asset.getAsset().getName().trim() +"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getModel()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_MODEL = " ).append("'" + asset.getAsset().getModel().trim()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getSeries()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_SERIES = " ).append("'" +asset.getAsset().getSeries().trim() +"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getAccountant_CD()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_ACCOUNTANT = " ).append("'" +asset.getAsset().getAccountant_CD().trim() +"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getPriceStart()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_PRICE >=" ).append("'" + asset.getAsset().getPriceStart()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getPriceEnd()))
		{
			sql.append(" AND");
			sql.append(" 	AG.ASSET_PRICE <= " ).append("'" + asset.getAsset().getPriceEnd()+"'");
		}
		if(Common.isNotEmpty(asset.getAsset().getGroup_id()))
		{
			sql.append(" AND");
			sql.append(" 	AG.GROUP_CD = " ).append("'" + asset.getAsset().getGroup_id()+"'");
		}
		
		sql.append(" UNION ALL ");
		sql.append(" SELECT INV_CHECKING_CD, IC.STATUS_EXPLAIN, IC.IVN_SESS_CD,IC.EXPLAIN, IC.EXPLAIN_DT, IC.USER_EXPLAIN, IC.ASSET_RFID, ");
		sql.append("    ISNULL('','') AS ASSET_CD, ");
		sql.append("     IC.ASSET_NAME, ");
		sql.append("    IC.ASSET_MODEL, ");
		sql.append("    ISNULL('','') AS GROUP_CD, ");
		sql.append("    IC.ASSET_SERIES, ");
		sql.append("     ISNULL('','') AS ASSET_DEPARTMENT, ");
		sql.append("     ISNULL('','')  AS GROUP_NA, ");
		sql.append("    DPT.DEPARTMENT_NAME AS DEPT_CHECK_NA, ");
		sql.append("    DPT.DEPT_CD AS DEPT_CHECK_CD, ");
		sql.append("    ISNULL('','') AS DEPT_USE_CD, ");
		sql.append("    ISNULL('','') AS DEPT_USE_NA, ");
		sql.append("    ISNULL(IC.ASSET_RFID, 0) AS STATUS, ");
		sql.append("    ISNULL(IC.STATUS_CHECKING, '0') AS STATUS_CHECKING, ");
		sql.append("    IC.USER_INSERT, ");
		sql.append("    IC.INSERT_DT ");
		sql.append(" FROM INVENTORY_CHECKING IC ");
		sql.append(" LEFT JOIN DEPRATMENT DPT ON DPT.DEPT_CD = IC.DEPT_CHECKING ");
		sql.append(" WHERE IC.STATUS_CHECKING = '2'");
		if(Common.isNotCheckEmpty(asset.getInventorySession().getInvenotrySessionCD()))
		{
			sql.append(" AND IC.IVN_SESS_CD = ").append("'" + asset.getInventorySession().getInvenotrySessionCD()+"'");
		}
		if(asset != null)
		{			
			if(Common.isNotEmpty(asset.getAsset().getRFID()))
			{
				sql.append(" AND");
				sql.append(" 	IC.ASSET_RFID = " ).append("'" +asset.getAsset().getRFID() +"'");
			}
			if(Common.isNotEmpty(asset.getInventory_Checking_Cd()))
			{
				sql.append(" AND");
				sql.append(" 	INV_CHECKING_CD = " ).append("'" +asset.getInventory_Checking_Cd() +"'");
			}
			
		}
		sql.append(" ) S WHERE 1=1 ");
		
		if(Common.isNotEmpty(asset.getStatus()))
		{
			if(asset.getStatus().equals("9999"))
			{
				sql.append(" AND 1=1 ");
			}
			else
			{
				sql.append(" AND STATUS_CHECKING = ").append("'" +asset.getStatus() +"'");
			}
		}
		
		
		
		sql.append(" ORDER BY ASSET_DEPARTMENT ");
		return sql.toString();
	}

}
