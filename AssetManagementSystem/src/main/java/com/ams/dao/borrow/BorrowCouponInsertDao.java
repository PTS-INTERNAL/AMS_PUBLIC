package com.ams.dao.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class BorrowCouponInsertDao {

	BorrowCouponModel br=null;
	public BorrowCouponInsertDao(BorrowCouponModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1,Common.getDateCurrent("YYYYMMddHHmmSS"));
			sqlStatement.setString(2, br.getNumber_no());
			sqlStatement.setString(3, "");
			sqlStatement.setString(4, "1");
			sqlStatement.setString(5, br.getCmpn_master().getCompany_cd());
			sqlStatement.setString(6, br.getDept_master().getDept_cd());
			sqlStatement.setString(7, br.getCmpn_client().getCompany_cd());
			sqlStatement.setString(8, br.getDept_client().getDept_cd());
			sqlStatement.setString(9, br.getDate_start());
			sqlStatement.setString(10,br.getDate_end_schedule());
			sqlStatement.setString(11,br.getReason());
			sqlStatement.setString(12,"0");
			sqlStatement.setString(13,br.getInsert_dt());
			sqlStatement.setString(14,br.getInsert_user());//ASSET_CD
			sqlStatement.setString(15, br.getInsert_dt());
			sqlStatement.setString(16, br.getInsert_user());
			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO BORROW_COUPON ");		
		sql.append("       (	COUPON_CD			");
		sql.append("       		,NUMBER_NO 			");	
		sql.append("       		,NO_CD 				");		
		sql.append("       		,STATUS 			");		
		sql.append("       		,CMPN_CD_MASTER 	");		
		sql.append("       		,DEPT_CD_MASTER 	");	
		sql.append("       		,CMPN_CD_CLIENT 	");		
		sql.append("       		,DEPT_CD_CLIENT 	");		
		sql.append("       		,DATE_START 		");		
		sql.append("       		,DATE_END_SCHEDULE 	");		
		sql.append("       		,REASON 			");	
		sql.append("       		,DELETE_FG 			");		
		sql.append("       		,INSERT_DT 			");		
		sql.append("       		,INSERT_USER 		");	
		sql.append("       		,UPDATE_DT 			");		
		sql.append("       		,UPDATE_USER 		");		
		sql.append("       	) 						");	
		sql.append("  VALUES ");
		sql.append("         (	 ? ");
		sql.append("        	,? ");
		sql.append("         	,? ");
		sql.append("        	,? ");
		sql.append("        	,? ");
		
		sql.append("        	,? ");
		sql.append("        	,? ");
		sql.append("         	,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("       		,? ");
		sql.append("      		,? ");
		sql.append("        	,? ");
		
		sql.append("       		,?	) ");
		return sql.toString();
	}
}
