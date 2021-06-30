package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class BorrowCouponUpdateDisApproveStockDao {

	BorrowCouponModel br=null;
	public BorrowCouponUpdateDisApproveStockDao(BorrowCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, br.getStatus());
			sqlStatement.setString(2, br.getReasonNotAllow());
			sqlStatement.setString(3, br.getUpdate_user());
			sqlStatement.setString(4, br.getUpdate_dt());
			sqlStatement.setString(5,br.getCoupon_cd());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE BORROW_COUPON ");		
		sql.append("       SET				");
		sql.append("       		STATUS 			= ?		");		
		sql.append("       		,REASON_NOT_ALLOW		= ?		");		
		sql.append("       		,STOCK_APPROVE_USER 			= ?		");		
		sql.append("       		,STOCK_APPROVE_DT 			= ?		");		
		sql.append("       WHERE COUPON_CD 		= ?		");	
		
		return sql.toString();
	}
}
