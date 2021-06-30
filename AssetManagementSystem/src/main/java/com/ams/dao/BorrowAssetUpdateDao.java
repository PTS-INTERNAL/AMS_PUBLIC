package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ams.database.DatabaseConnection;

public class BorrowAssetUpdateDao {
	public void execute(String borrowCd, String reason, int status) throws SQLException {
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection conn = dbConnection.getConnection();
		PreparedStatement updateStm = conn.prepareStatement(getUpdateQuery());
		updateStm.setInt(1, status);
		updateStm.setString(2, reason);
		updateStm.setString(3, borrowCd);
		updateStm.execute();
	}
	
	private String getUpdateQuery() {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE");
		sqlBuilder.append("   BORROW_ASSET ");
		sqlBuilder.append("SET");
		sqlBuilder.append("   STATUS=?, REASON_NOT_ALLOW=? ");
		sqlBuilder.append("WHERE");
		sqlBuilder.append("   BORROW_CD=?");
		
		return sqlBuilder.toString();
	}
}
