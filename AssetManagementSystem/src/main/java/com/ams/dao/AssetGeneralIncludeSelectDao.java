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


public class AssetGeneralIncludeSelectDao {
	AssetGeneralFormSearch form = new AssetGeneralFormSearch();
	public AssetGeneralIncludeSelectDao(AssetGeneralFormSearch form)
	{
		this.form  = form;
	}
	public AssetGeneralIncludeSelectDao()
	{
		
	}
	
	public List<AssetGeneralModel> excute() throws SQLException
	{
		
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println("-------------");
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<AssetGeneralModel> lstAsset =new ArrayList<AssetGeneralModel>();
		while (result.next()) {
			AssetGeneralModel Asset = new AssetGeneralModel();
			Asset.SetSTT(result.getString("STT"));
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
			Asset.setMode(result.getString("MODE"));
			Asset.setOriginal(result.getString("ASSET_ORIGINAL"));
			Asset.setInline(result.getString("INLINE"));
			Asset.setCustomer(result.getString("CUSTOMER"));
			Asset.setContract(result.getString("CONTRACT"));
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
		sql.append(" SELECT ROW_NUMBER() OVER(ORDER BY TABLE_TOTAL.ASSET_NAME) as STT ,TABLE_TOTAL.* FROM ( SELECT TABLE_GEN.* FROM (SELECT ");
		sql.append("  CASE");
		sql.append("  WHEN BS.BORROW_CD IS NOT NULL THEN 'BORROW'");
		//sql.append("  WHEN LS.LOAN_CD IS NOT NULL THEN 'LOAN'");
		sql.append("   ELSE 'GEN'");
		sql.append("   END AS MODE");
		sql.append("   , TABLE1.*");
		sql.append(" FROM (SELECT ");
		sql.append(" ASSET_RFID");
		sql.append("  ,ASSET_CD");
		sql.append("  ,ASSET_NAME");
		sql.append("  ,ASSET_MODEL");
		sql.append("  ,GROUP_CD");
		sql.append("  ,ASSET_ORIGINAL");
		sql.append("  ,ASSET_SERIES");
		sql.append("  ,ASSET_DEPARTMENT");
		sql.append("  ,ASSET_ACCOUNTANT");
		sql.append("  ,ASSET_DATE_INVEST");
		sql.append("  ,ASSET_PRICE,ASSET_NOTE,ASSET_DATE_END");
		sql.append("  ,GROUP_NAME");
		sql.append("  , DEPARTMENT_NAME");
		sql.append("  , AG.CMPN_CD AS CMPN_CD");
		sql.append("  , AG.INLINE AS INLINE");
		sql.append("  , AG.CUSTOMER AS CUSTOMER");
		sql.append("  , AG.CONTRACT AS CONTRACT");
		sql.append(" FROM");
		sql.append(" 	ASSETS_GENERAL AG");
		sql.append(" INNER JOIN DEPRATMENT DPT");
		sql.append(" ON  AG.ASSET_DEPARTMENT = DPT.DEPT_CD");
		sql.append("  LEFT JOIN GROUP_ASSET GS");
		sql.append("  ON GS.GROUP_ID = AG.GROUP_CD");
		sql.append(" WHERE");
		sql.append(" 	AG.DELETE_FG = '0'  ");
		sql.append(" 	AND AG.CMPN_CD = ").append("'" + form.getCompany_CD()+"'");
		if(form != null)
		{
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
		sql.append(" 	) AS TABLE1 ");
		sql.append(" 	 LEFT JOIN BORROW_ASSET BS");
		sql.append(" 	 ON BS.ASSET_CD = TABLE1.ASSET_CD");
		sql.append(" 	 AND BS.STATUS >= '3'");
		sql.append(" 	 AND BS.STATUS < '5'");
		sql.append(" 	 AND BS.ASSET_RFID= TABLE1.ASSET_RFID ");
		sql.append(" 	 AND BS.DEPT_MASTER = TABLE1.ASSET_DEPARTMENT ");
//		sql.append(" 	 LEFT JOIN LOAN_ASSET LS ");
//		sql.append(" 	 ON LS.ASSET_CD = TABLE1.ASSET_CD ");
//		sql.append(" 	 AND LS.STATUS >= '2'");
//		sql.append(" 	 AND LS.STATUS < '5'");
//		sql.append(" 	 AND LS.ASSET_RFID= TABLE1.ASSET_RFID ");
//		sql.append(" 	 AND LS.DEPT_CLIENT = TABLE1.ASSET_DEPARTMENT ");
		
		sql.append(" UNION ALL ");
		sql.append(" SELECT 'LOAN' AS MODEL,TABLE2.* FROM  ");
		sql.append(" ((SELECT ASSET_RFID , ");
		sql.append("           ASSET_CD , ");
		sql.append("           ASSET_NAME , ");
		sql.append("           ASSET_MODEL , ");
		
		sql.append("           GROUP_CD , ");
		sql.append("  			'' AS ASSET_ORIGINAL,");
		sql.append("          ASSET_SERIES , ");
		sql.append("           ASSET_DEPARTMENT , ");
		sql.append("           ASSET_ACCOUNTANT , ");
		sql.append("           ASSET_DATE_INVEST , ");
		sql.append("           ASSET_PRICE, ");
		sql.append("           ASSET_NOTE, ");
		sql.append("           ASSET_DATE_END , ");
		sql.append("          GROUP_NAME , ");
		sql.append("           DEPARTMENT_NAME , ");
		sql.append("           AG.CMPN_CD AS CMPN_CD, ");
		sql.append("           AG.INLINE AS INLINE ");
		sql.append("  , AG.CUSTOMER AS CUSTOMER");
		sql.append("  , AG.CONTRACT AS CONTRACT");
		sql.append("    FROM ASSETS_GENERAL AG ");
		sql.append("    INNER JOIN DEPRATMENT DPT ON AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
		sql.append("    LEFT JOIN GROUP_ASSET GS ON GS.GROUP_ID = AG.GROUP_CD ");
		sql.append("    WHERE AG.DELETE_FG = '0' ");
		if(form != null)
		{
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
		
		sql.append("      ) AS TABLE2 ");
		sql.append(" INNER JOIN LOAN_ASSET LS ON LS.ASSET_CD = TABLE2.ASSET_CD ");
		sql.append(" AND LS.STATUS >= '2' ");
		sql.append(" AND LS.STATUS < '5' ");
		sql.append(" 	AND LS.CMPN_MASTER = ").append("'" + form.getCompany_CD()+"'");
		sql.append(" AND LS.ASSET_RFID= TABLE2.ASSET_RFID ");
		sql.append(" AND TABLE2.CMPN_CD =");
		sql.append(" 		  CASE LS.CMPN_MASTER WHEN LS.CMPN_CLIENT THEN ");
		sql.append(" 		   LS.CMPN_MASTER 	  ");
		sql.append(" 		   ELSE  LS.CMPN_CLIENT");
		sql.append(" 		  END)    ");
		sql.append(" 	 UNION ALL ");
		sql.append(" 	 SELECT  ");
		sql.append(" 	 'RENT' AS MODEL, ");
		sql.append(" 	  NULL AS ASSET_RFID , ");
		sql.append(" 	  NULL AS ASSET_CD ,");
		sql.append(" 	   ASSET_NAME , ");
		sql.append(" 	 MODEL AS ASSET_MODEL ,  ");
		sql.append(" 	  NULL AS GROUP_CD , ");
		sql.append("  			'' AS ASSET_ORIGINAL,");
		sql.append(" 	  SERIES AS ASSET_SERIES , ");
		sql.append(" 	  DEPARTMENT_CD AS ASSET_DEPARTMENT , ");
		sql.append(" 	 ACCOUNTANT_CD AS ASSET_ACCOUNTANT , ");
		sql.append(" 	 DATE_START AS ASSET_DATE_INVEST ,  ");
		sql.append(" 	 NULL ASSET_PRICE, ");
		sql.append(" 	  NULL ASSET_NOTE, ");
		sql.append(" 	 DATE_END_SCHEDUAL AS ASSET_DATE_END , ");
		sql.append(" 	    NULL AS GROUP_NAME , ");
		sql.append(" 	  DPT.DEPARTMENT_NAME AS DEPARTMENT_NAME  , ");
		sql.append(" 	   RA.CMPN_CD AS CMPN_CD, ");
		sql.append("      '0' AS INLINE ");
		sql.append("  , '' AS CUSTOMER");
		sql.append("  ,'' AS CONTRACT");
		sql.append(" 	 FROM RENT_ASSET RA , DEPRATMENT DPT ");
		sql.append(" 	  WHERE RA.CMPN_CD =").append("'" + form.getCompany_CD()+"'");;
		sql.append(" 	 AND RA.DEPARTMENT_CD = DPT.DEPT_CD ");
		sql.append(" 	 AND RA.STATUS_RENT < '2' ");
		if(form != null)
		{
			
			if(form.getName()!=null && form.getName().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.ASSET_NAME = " ).append("N'" +form.getName().trim() +"'");
			}
			if(form.getModel()!=null && form.getModel().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.MODEL = " ).append("'" + form.getModel().trim()+"'");
			}
			if(form.getSeries()!=null && form.getSeries().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.SERIES = " ).append("'" +form.getSeries().trim() +"'");
			}
			if(form.getDepartment_cd()!=null && form.getDepartment_cd().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.DEPARTMENT_CD = " ).append("'" +form.getDepartment_cd().trim() +"'");
			}
			if(form.getAccountant_CD()!=null && form.getAccountant_CD().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.ACCOUNTANT_CD = " ).append("'" +form.getAccountant_CD().trim() +"'");
			}
			if(form.getDateStart_Start()!=null && form.getDateStart_Start().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.DATE_START >= " ).append("'" + form.getDateStart_Start().trim()+"'");
			}
			if(form.getDateStart_End()!=null && form.getDateStart_End().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.DATE_START <= " ).append("'" + form.getDateStart_End().trim()+"'");
			}
			if(form.getDateEnd_Start()!=null && form.getDateEnd_Start().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.DATE_END_SCHEDUAL <= " ).append("'" +form.getDateEnd_Start().trim() +"'");
			}
			if(form.getDateEnd_End()!=null && form.getDateEnd_End().trim().length()>0)
			{
				sql.append(" AND");
				sql.append(" 	RA.DATE_END_SCHEDUAL <= " ).append("'" +form.getDateEnd_End().trim() +"'");
			}
			
		}
		
		
		sql.append(" 	) TABLE_GEN ");
		if(Common.isNotCheckEmpty(form.getStatus()))
		{
			if(form.getStatus().equals("ALL") == false)
			{
				sql.append(" 	WHERE MODE  = " ).append("'" +form.getStatus() +"'");
			}
		}
		sql.append(" 	) AS TABLE_TOTAL	");
		if(form != null)
		{
			if(form.getRowPage()!=null && form.getRowPage().trim().length()>0)
			{
				sql.insert(0, " select * from("  );
				sql.append("  ) as BYPAGE WHERE STT >= ");
				sql.append(Integer.parseInt(form.getRowPage())*13-12);
				sql.append("  AND STT <= ");
				sql.append(Integer.parseInt(form.getRowPage())*13);
			}
		}
		sql.append(" ORDER BY  GROUP_NAME, ASSET_NAME");
		return sql.toString();
	}
}
