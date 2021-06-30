package com.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserRoleMapping;

public class UserRoleMappingInsertDao {
	UserRoleMapping role;
	
	public UserRoleMappingInsertDao(UserRoleMapping role)
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
		sqlStatement.setString(1,role.getRole_cd());
		sqlStatement.setString(2,role.getCompany_cd());
		sqlStatement.setString(3,role.getUser_cd());
		sqlStatement.setString(4,role.getSerive_cd());
		sqlStatement.setString(5,role.getR_access());
		sqlStatement.setString(6,role.getR_read());
		sqlStatement.setString(7,role.getR_write());
		sqlStatement.setString(8,role.getR_update());
		sqlStatement.setString(9,role.getR_delete());		
		sqlStatement.setString(10,role.getR_print());
		sqlStatement.setString(11,role.getR_export());
		
		result = sqlStatement.executeUpdate();
		return result;
	}
	
	
	
	public String getSql(UserRoleMapping role)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO ");
		sql.append(" 	USER_ROLE_MAPPING");
		sql.append(" ( ");
		sql.append(" 	ROLE_CD");
		sql.append(" 	,CMPN_CD");
		sql.append(" 	,USER_CD");
		sql.append(" 	,SERVICE_CD");
		sql.append(" 	,R_ACCESS");
		sql.append(" 	,R_READ");
		sql.append(" 	,R_WRITE");
		sql.append(" 	,R_UPDATE");
		sql.append(" 	,R_DELETE");
		sql.append(" 	,R_PRINT");
		sql.append(" 	,R_EXPORT");
		sql.append(" ) ");
		sql.append(" 	VALUES");
		sql.append(" ( ");
		sql.append(" 	?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" 	,?");
		sql.append(" ) ");
		
		return sql.toString();
	}
}
