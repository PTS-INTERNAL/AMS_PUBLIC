package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowAssetModel;
import com.ams.model.MovementAssetModel;
import com.ams.util.Common;



public class MovementAssetUpdateDao {
	MovementAssetModel mov = null;
	
	public MovementAssetUpdateDao()
	{
		
	}
	
	public MovementAssetUpdateDao(MovementAssetModel mov)
	{
		this.mov = mov;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSQL());
		//System.out.println(getSQL());
		
		
		sqlStatement.setString(1,mov.getNumber_on());//NUMBER_ON ");
		sqlStatement.setString(2,mov.getCmpn_master().getCompany_cd());//CMPN_MASTER ");
		sqlStatement.setString(3,mov.getCmpn_master().getCompany_name());//CMPN_NAME_MASTER ");
		sqlStatement.setString(4,mov.getDept_master().getDept_cd());//DEPT_MASTER ");
		sqlStatement.setString(5,mov.getDept_master().getDept_name());//DEPT_NAME_MASTER ");
		sqlStatement.setString(6,mov.getCmpn_client().getCompany_cd());//CMPN_CLIENT ");
		sqlStatement.setString(7,mov.getCmpn_client().getCompany_name());//CMPN_NAME_CLIENT ");
		sqlStatement.setString(8,mov.getDept_client().getDept_cd());//DEPT_CLIENT ");
		sqlStatement.setString(9,mov.getDept_client().getDept_name());//DEPT_NAME_CLIENT ");
		sqlStatement.setString(10,mov.getAsset().getId());//ASSET_CD ");
		sqlStatement.setString(11,mov.getAsset().getRFID());//ASSET_RFID ");
		sqlStatement.setString(12,mov.getAsset().getName());//ASSET_NAME ");
		sqlStatement.setString(13,mov.getAsset().getSeries());//ASSET_SERIES ");
		sqlStatement.setString(14,mov.getAsset().getModel());//ASSET_MODEL ");
		sqlStatement.setString(15,mov.getAsset().getAccountant_CD());//ASSET_ACCOUNTANT ");
		sqlStatement.setString(16,mov.getMovementCoupon().getDate_start());//DATE_START ");
		sqlStatement.setString(17,mov.getMovementCoupon().getDate_end_schedule());//DATE_END ");
		sqlStatement.setString(18,mov.getMovementCoupon().getDate_end_real());//DATE_PAY ");
		sqlStatement.setString(19,mov.getNote());//NOTE ");
		sqlStatement.setString(20,mov.getMovementCoupon().getReason());//REASON ");
		sqlStatement.setString(21,mov.getAsseseries());//ASSESSERIES ");
		sqlStatement.setString(22,mov.getUserInsert());//USER_INSERT ");
		sqlStatement.setString(23,mov.getInsertDt());//INSERT_DT ");
		sqlStatement.setString(24,mov.getUserUpdate());//USER_UPDATE ");
		sqlStatement.setString(25,mov.getUpdateDt());//UPDATE_DT ");
		sqlStatement.setString(26,mov.getUserApprove());//USER_APPROVE ");
		sqlStatement.setString(27,mov.getApproveDt());//APPROVE_DT ");
		sqlStatement.setString(28,"");//USER_ACCOUNTANT_APPROVE ");
		sqlStatement.setString(29,"");//ACCOUNTANT_APPROVE_DT ");
		sqlStatement.setString(30,"");//USER_STOCK_APPROVE ");
		sqlStatement.setString(31,"");//STOCK_APPROVE_DT ");
		sqlStatement.setString(32,mov.getStatus());//STATUS ");
		sqlStatement.setString(33,"");//REASON_NOT_ALLOW ");
		sqlStatement.setString(34,"");//STATUS_ALLOW ");
		sqlStatement.setString(35,mov.getMovementCoupon().getCoupon_cd());//COUPON_CD ");
		sqlStatement.setString(36,"0");//DELETE_FG ");
		sqlStatement.setString(37,"");//COUPON_CD_PALLENT) ");
		sqlStatement.setString(38,mov.getId());//MOVEMENT_CD ");
		
		result = sqlStatement.executeUpdate();
		
		return result;
	}
	
	public String getSQL()
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE MOVEMENT_ASSET SET  ");
		sql.append("            	NUMBER_ON= ? ");
		sql.append("            	,CMPN_MASTER= ? ");
		sql.append("           		,CMPN_NAME_MASTER= ? ");
		sql.append("           		,DEPT_MASTER= ? ");
		sql.append("           		,DEPT_NAME_MASTER= ? ");
		sql.append("           		,CMPN_CLIENT= ? ");
		sql.append("          		,CMPN_NAME_CLIENT= ? ");
		sql.append("          		,DEPT_CLIENT= ? ");
		sql.append("         		,DEPT_NAME_CLIENT= ? ");
		sql.append("        		,ASSET_CD= ? ");
		sql.append("        		,ASSET_RFID= ? ");
		sql.append("       			,ASSET_NAME= ? ");
		sql.append("       			,ASSET_SERIES= ? ");
		sql.append("      			,ASSET_MODEL= ? ");
		sql.append("        		,ASSET_ACCOUNTANT= ? ");
		sql.append("         		,DATE_START= ? ");
		sql.append("         		,DATE_END= ? ");
		sql.append("         		,DATE_PAY= ? ");
		sql.append("          		,NOTE= ? ");
		sql.append("         		,REASON= ? ");
		sql.append("         		,ASSESSERIES= ? ");
		sql.append("         		,USER_INSERT= ? ");
		sql.append("        		,INSERT_DT= ? ");
		sql.append("        		,USER_UPDATE= ? ");
		sql.append("       			,UPDATE_DT= ? ");
		sql.append("        		,USER_APPROVE= ? ");
		sql.append("       			,APPROVE_DT= ? ");
		sql.append("         		,USER_ACCOUNTANT_APPROVE= ? ");
		sql.append("         		,ACCOUNTANT_APPROVE_DT= ? ");
		sql.append("        		,USER_STOCK_APPROVE= ? ");
		sql.append("        		,STOCK_APPROVE_DT= ? ");
		sql.append("        		,STATUS= ? ");
		sql.append("        		,REASON_NOT_ALLOW= ? ");
		sql.append("        		,STATUS_ALLOW= ? ");
		sql.append("        		,COUPON_CD= ? ");
		sql.append("        		,DELETE_FG= ? ");
		sql.append("          		,COUPON_CD_PALLENT= ? ");
		
		sql.append("     WHERE 			MOVEMENT_CD= ? ");
	
		return sql.toString();
	}

}
