package com.ams.dao.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.BorrowCouponModel;
import com.ams.model.LoanAssetModel;
import com.ams.util.Common;
import com.ams.util.ParamsConstants;

public class LoanAssetUpdateDeptDao {

	LoanAssetModel br=null;
	public LoanAssetUpdateDeptDao(LoanAssetModel br) {
		this.br = br;
	}
	
	public int excute() throws SQLException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		
			sqlStatement.setString(1, br.getAsset().getDepartment_cd());
			sqlStatement.setString(2, br.getId());

			
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" 	UPDATE LOAN_ASSET ");		
		sql.append("       SET				");
		sql.append("       		DEPT_MASTER  		= ?		");		
		sql.append("       WHERE LOAN_CD 			= ?		");	
		
		return sql.toString();
	}
}
