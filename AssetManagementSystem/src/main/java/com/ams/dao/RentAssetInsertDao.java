package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.RentAsset;


public class RentAssetInsertDao {
	
	RentAsset rentAsset = null;
	
	public RentAssetInsertDao(RentAsset asset)
	{
		this.rentAsset = asset;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
			
		sqlStatement.setString(1,rentAsset.getRent_cd());//RENT_CD 
		sqlStatement.setString(2,rentAsset.getCompany_master().getCompany_cd()); //CMPN_CD 
		sqlStatement.setString(3,rentAsset.getDept_master().getDept_cd());//DEPARTMENT_CD 
		sqlStatement.setString(4,rentAsset.getAsset().getName());//ASSET_NAME 
		sqlStatement.setString(5,rentAsset.getAsset().getAccountant_CD());//ACCOUNTANT_CD 
		sqlStatement.setString(6,rentAsset.getComany_client().getCompany_name());//BUSSINESS_NAME 
		sqlStatement.setString(7,rentAsset.getComany_client().getCompany_address());//BUSSINESS_ADDRESS 
		sqlStatement.setString(8,rentAsset.getDate_start());//RENT_DATE 
		sqlStatement.setString(9,rentAsset.getDate_end_schedual());//PAY_DATE_1 
		sqlStatement.setString(10,null);//PAY_DATE_1 
		sqlStatement.setString(11,rentAsset.getAsset().getModel());//PAY_DATE_1 
		sqlStatement.setString(12,rentAsset.getAsset().getSeries());//PAY_DATE_1 
		sqlStatement.setString(13,"1");//PAY_DATE_1 

		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO ");
		sql.append(" RENT_ASSET ");
		sql.append(" (");
		sql.append(" 		   RENT_CD ");
		sql.append("	      ,CMPN_CD ");
		sql.append("	      ,DEPARTMENT_CD ");
		sql.append("	      ,ASSET_NAME ");
		sql.append("	      ,ACCOUNTANT_CD ");
		sql.append("	      ,BUSSINESS_NAME ");
		sql.append("	      ,BUSSINESS_ADDRESS ");
		sql.append("	      ,DATE_START ");
		sql.append("	      ,DATE_END_SCHEDUAL ");
		sql.append("	      ,DATE_END_REAL ");
		sql.append("	      ,MODEL ");
		sql.append("	      ,SERIES ");
		sql.append("	      ,STATUS_RENT");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 			? "); //RENT_CD 
		sql.append("	      ,? "); //CMPN_CD 
		sql.append("	      ,? ");//DEPARTMENT_CD 
		sql.append("	      ,?  ");//ASSET_NAME 
		sql.append("	      ,?  ");//ACCOUNTANT_CD 
		sql.append("	      ,?  ");//BUSSINESS_NAME 
		sql.append("	      ,?  ");//BUSSINESS_ADDRESS 
		sql.append("	      ,?  ");//RENT_DATE 
		sql.append("	      ,?  ");//PAY_DATE_1 
		sql.append("	      ,?  ");//PAY_DATE_2 
		sql.append("	      ,?  ");//PAY_DATE_1 
		sql.append("	      ,?  ");//PAY_DATE_2 
		sql.append("	      ,?  ");//PAY_DATE_2 
		sql.append(" )");
		
		return sql.toString();
	}

}
