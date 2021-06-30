package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserRoleMapping;



public class UserRoleMappingUpdateDao {
	
	UserRoleMapping role;
	
	public UserRoleMappingUpdateDao(UserRoleMapping role)
	{
		this.role = role;
	}
	public int excute() throws SQLException
	{
		int result = 0;
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql(role));
		//System.out.println(getSql(role));
		sqlStatement.setString(1,role.getR_access());
		sqlStatement.setString(2,role.getR_read());
		sqlStatement.setString(3,role.getR_write());
		sqlStatement.setString(4,role.getR_update());
		sqlStatement.setString(5,role.getR_print());
		sqlStatement.setString(6,role.getR_delete());		
		sqlStatement.setString(7,role.getR_export());
		sqlStatement.setString(8,role.getRole_cd());
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	
	
	public String getSql(UserRoleMapping role)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE");
		sql.append(" 	USER_ROLE_MAPPING");
		sql.append(" SET ");
		sql.append(" 	R_ACCESS = ?");
		sql.append(" 	,R_READ = ?");
		sql.append(" 	,R_WRITE = ?");
		sql.append(" 	,R_UPDATE = ?");
		sql.append(" 	,R_PRINT = ?");
		sql.append(" 	,R_DELETE = ?");
		sql.append(" 	,R_EXPORT = ?");
		sql.append(" WHERE ");
		sql.append(" 	ROLE_CD = ?");
		
		
		return sql.toString();
	}
}
