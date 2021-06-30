package com.ams.dao.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.LoanCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class LoanCouponUpdateApproveReturnAccountantDao {

	LoanCouponModel br=null;
	public LoanCouponUpdateApproveReturnAccountantDao(LoanCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, br.getStatus());
			sqlStatement.setString(2, br.getUpdate_user());
			sqlStatement.setString(3, br.getUpdate_dt());
			sqlStatement.setString(4, br.getCoupon_cd());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE LOAN_COUPON ");		
		sql.append("       SET				");
		sql.append("       		STATUS 			= ?		");	
		sql.append("       		,RETURN_ACCOUNT_APPROVE_USER 			= ?		");		
		sql.append("       		,DT_RETURN_ACCOUNT_APPROVE 			= ?		");		
		sql.append("       WHERE COUPON_CD 		= ?		");	
		
		return sql.toString();
	}
}
