package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class BorrowCouponUpdateDao {

	BorrowCouponModel br=null;
	public BorrowCouponUpdateDao(BorrowCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, br.getCmpn_master().getCompany_cd());
			sqlStatement.setString(2, br.getDept_master().getDept_cd());
			sqlStatement.setString(3, br.getCmpn_client().getCompany_cd());
			sqlStatement.setString(4, br.getDept_client().getDept_cd());
			sqlStatement.setString(5, br.getDate_start());
			sqlStatement.setString(6,br.getDate_end_schedule());
			sqlStatement.setString(7,br.getReason());
			sqlStatement.setString(8, br.getUpdate_dt());
			sqlStatement.setString(9, br.getUpdate_user());
			sqlStatement.setString(10,br.getCoupon_cd());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE BORROW_COUPON ");		
		sql.append("       SET				");
		sql.append("       		CMPN_CD_MASTER 		= ?		");		
		sql.append("       		,DEPT_CD_MASTER 	= ?	 	");	
		sql.append("       		,CMPN_CD_CLIENT 	= ?	 	");		
		sql.append("       		,DEPT_CD_CLIENT 	= ?	 	");		
		sql.append("       		,DATE_START     	= ?	 	");		
		sql.append("       		,DATE_END_SCHEDULE 	= ?		");		
		sql.append("       		,REASON  			= ?		");			
		sql.append("       		,UPDATE_DT 	 		= ?		");	
		sql.append("       		,UPDATE_USER  		= ?		");		
		sql.append("       WHERE COUPON_CD 			= ?		");	
		
		return sql.toString();
	}
}
