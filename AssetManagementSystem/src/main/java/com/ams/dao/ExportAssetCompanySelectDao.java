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
import com.ams.model.ExportAssetCompanyModel;

public class ExportAssetCompanySelectDao {
      public String cmpn_cd;
      
      public ExportAssetCompanySelectDao(String cmpn_cd)
      {
    	  this.cmpn_cd = cmpn_cd;
      }
      
      public List<ExportAssetCompanyModel> excute() throws SQLException {
  		
  		DatabaseConnection conn = new DatabaseConnection();
  		Connection connectString = conn.getConnection();
  		Statement stmt = connectString.createStatement();
  		ResultSet result = null;
  		//System.out.println(getSQL());
  		result = stmt.executeQuery(getSQL());
  		List<ExportAssetCompanyModel> lstAsset =new ArrayList<ExportAssetCompanyModel>();
  		while(result.next())
  		{
  			ExportAssetCompanyModel asset  = new ExportAssetCompanyModel();
  			asset.setCount(result.getString("SL"));
  			asset.setAssetName(result.getString("ASSET_NAME"));
  			asset.setAssetModel(result.getString("ASSET_MODEL"));
  			asset.setMode(result.getString("MODE"));
  			asset.setArea(result.getString("AEREA"));
  			asset.setKeys(result.getString("KEYS"));
  			lstAsset.add(asset);
  		}
  		return lstAsset;
  	}

  	public String getSQL()
  	{
  		StringBuilder sql   = new StringBuilder();

  		sql.append(" SELECT TABLE_TOTAL.*, ");
  		sql.append(" CONCAT(ASSET_NAME, ASSET_MODEL) AS KEYS ");
  		sql.append(" FROM ");
  		sql.append(" (SELECT COUNT (ASSET_NAME) AS SL, ");
  		sql.append("          ASSET_NAME, ");
  		sql.append("         ASSET_MODEL, ");
  		sql.append("          MODE, ");
  		sql.append("         AEREA ");
  		sql.append(" FROM ");
  		sql.append(" (SELECT CASE BS.CMPN_MASTER ");
  		sql.append("       WHEN BS.CMPN_CLIENT THEN 'IN' ");
  		sql.append("       ELSE 'OUT' ");
  		sql.append("   END AS AEREA, ");
  		sql.append("   CASE ");
  		sql.append("       WHEN BS.BORROW_CD IS NOT NULL THEN 'BORROW' ");
  		sql.append("       ELSE 'GEN' ");
  		sql.append("    END AS MODE, ");
  		sql.append("    TABLE1.* ");
  		sql.append(" FROM ");
  		sql.append(" (SELECT ASSET_RFID, ");
  		sql.append("          ASSET_CD, ");
  		sql.append("        ASSET_NAME, ");
  		sql.append("        ASSET_MODEL, ");
  		sql.append("         GROUP_CD, ");
  		sql.append("        ASSET_SERIES, ");
  		sql.append("        ASSET_DEPARTMENT, ");
  		sql.append("       ASSET_ACCOUNTANT, ");
  		sql.append("       ASSET_DATE_INVEST, ");
  		sql.append("       ASSET_PRICE, ");
  		sql.append("       ASSET_NOTE, ");
  		sql.append("      ASSET_DATE_END, ");
  		sql.append("     GROUP_NAME, ");
  		sql.append("     DEPARTMENT_NAME, ");
  		sql.append("        AG.CMPN_CD AS CMPN_CD ");
  		sql.append(" FROM ASSETS_GENERAL AG ");
  		sql.append(" INNER JOIN DEPRATMENT DPT ON AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
  		sql.append("  LEFT JOIN GROUP_ASSET GS ON GS.GROUP_ID = AG.GROUP_CD ");
  		sql.append("  WHERE AG.DELETE_FG = '0' ");
  		sql.append("    AND AG.CMPN_CD = '202007251213240' ) AS TABLE1 ");
  		sql.append("  LEFT JOIN BORROW_ASSET BS ON BS.ASSET_CD = TABLE1.ASSET_CD ");
  		sql.append(" AND BS.STATUS >= '3' ");
  		sql.append("  AND BS.STATUS < '5' ");
  		sql.append("  AND BS.ASSET_RFID= TABLE1.ASSET_RFID ");
  		sql.append("  AND BS.DEPT_MASTER = TABLE1.ASSET_DEPARTMENT ");
  		sql.append("  UNION ALL SELECT CASE LS.CMPN_MASTER ");
  		sql.append("                 WHEN LS.CMPN_CLIENT THEN 'IN' ");
  		sql.append("                 ELSE 'OUT' ");
  		sql.append("              END AS AEREA, ");
  		sql.append("              'LOAN' AS MODEL, ");
  		sql.append("              TABLE2.* ");
  		sql.append("  FROM ( ");
  		sql.append("        (SELECT ASSET_RFID, ");
  		sql.append("                ASSET_CD, ");
  		sql.append("               ASSET_NAME, ");
  		sql.append("               ASSET_MODEL, ");
  		sql.append("               GROUP_CD, ");
  		sql.append("              ASSET_SERIES, ");
  		sql.append("              ASSET_DEPARTMENT, ");
  		sql.append("              ASSET_ACCOUNTANT, ");
  		sql.append("               ASSET_DATE_INVEST, ");
  		sql.append("              ASSET_PRICE, ");
  		sql.append("              ASSET_NOTE, ");
  		sql.append("              ASSET_DATE_END, ");
  		sql.append("               GROUP_NAME, ");
  		sql.append("              DEPARTMENT_NAME, ");
  		sql.append("              AG.CMPN_CD AS CMPN_CD ");
  		sql.append("       FROM ASSETS_GENERAL AG ");
  		sql.append("       INNER JOIN DEPRATMENT DPT ON AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
  		sql.append("        INNER JOIN GROUP_ASSET GS ON GS.GROUP_ID = AG.GROUP_CD ");
  		sql.append("        WHERE AG.DELETE_FG = '0' ) AS TABLE2 ");
  		sql.append("     INNER JOIN LOAN_ASSET LS ON LS.ASSET_CD = TABLE2.ASSET_CD ");
  		sql.append("     AND LS.STATUS >= '2' ");
  		sql.append("     AND LS.STATUS < '5' ");
  		sql.append("     AND LS.CMPN_MASTER = '202007251213240' ");
  		sql.append("     AND LS.ASSET_RFID= TABLE2.ASSET_RFID ");
  		sql.append("     AND TABLE2.CMPN_CD = CASE LS.CMPN_MASTER ");
  		sql.append("                              WHEN LS.CMPN_CLIENT THEN LS.CMPN_MASTER ");
  		sql.append("                              ELSE LS.CMPN_CLIENT ");
  		sql.append("                          END) ");
  		sql.append("  UNION ALL SELECT 'OUT' AS AREA, ");
  		sql.append("                   'RENT' AS MODEL, ");
  		sql.append("                 NULL AS ASSET_RFID, ");
  		sql.append("                 NULL AS ASSET_CD, ");
  		sql.append("                 ASSET_NAME, ");
  		sql.append("                 MODEL AS ASSET_MODEL, ");
  		sql.append("                 NULL AS GROUP_CD, ");
  		sql.append("                 SERIES AS ASSET_SERIES, ");
  		sql.append("                DEPARTMENT_CD AS ASSET_DEPARTMENT, ");
  		sql.append("               ACCOUNTANT_CD AS ASSET_ACCOUNTANT, ");
  		sql.append("                  DATE_START AS ASSET_DATE_INVEST, ");
  		sql.append("                 NULL ASSET_PRICE, ");
  		sql.append("                     NULL ASSET_NOTE, ");
  		sql.append("                          DATE_END_SCHEDUAL AS ASSET_DATE_END, ");
  		sql.append("                           NULL AS GROUP_NAME, ");
  		sql.append("                          DPT.DEPARTMENT_NAME AS DEPARTMENT_NAME, ");
  		sql.append("                          RA.CMPN_CD AS CMPN_CD ");
  		sql.append("  FROM RENT_ASSET RA, ");
  		sql.append("      DEPRATMENT DPT ");
  		sql.append("   WHERE RA.CMPN_CD ='202007251213240' ");
  		sql.append("    AND RA.DEPARTMENT_CD = DPT.DEPT_CD ");
  		sql.append("    AND RA.STATUS_RENT < '2' ) AS TABLE_GEN ");
  		sql.append("  GROUP BY ASSET_NAME, ");
  		sql.append("     ASSET_MODEL, ");
  		sql.append("     MODE, ");
  		sql.append("     AEREA) AS TABLE_TOTAL ");


  		
  		
  		return sql.toString();
  	}
}
