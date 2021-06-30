package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserRoleMapping;

public class UserRoleMappingSelectDao {

	UserRoleMapping userRole;
	
	public UserRoleMappingSelectDao(UserRoleMapping userRole)
	{
		this.userRole = userRole;
	}

	
	public List<UserRoleMapping> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<UserRoleMapping> lstRole =new ArrayList<UserRoleMapping>();
		while (result.next()) {
			UserRoleMapping role = new UserRoleMapping();
			role.setService_name(result.getString("SERVICE_NAME"));
			role.setService_tx(result.getString("SERVICE_TX"));
			role.setUrl_mapping(result.getString("URL_MAPPING"));
			role.setR_access(result.getString("R_ACCESS"));
			role.setR_read(result.getString("R_READ"));
			role.setR_delete(result.getString("R_DELETE"));
			role.setR_export(result.getString("R_EXPORT"));
			role.setR_print(result.getString("R_PRINT"));
			role.setR_write(result.getString("R_WRITE"));
			role.setR_update(result.getString("R_UPDATE"));
			role.setRole_cd(result.getString("ROLE_CD"));
			role.setService_id(result.getString("SERVICE_CD"));
			lstRole.add(role);
		}
		return lstRole;
	}

	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 		 ROLE_CD  ");
		sql.append(" 		,SV.SERVICE_CD as SERVICE_CD  ");
		sql.append(" 		,SERVICE_TX ");
		sql.append(" 		,URL_MAPPING ");
		sql.append(" 		,SERVICE_NAME ");
		sql.append(" 		,R_ACCESS ");
		sql.append(" 		,R_READ ");
		sql.append(" 		,R_DELETE ");
		sql.append(" 		,R_EXPORT ");
		sql.append(" 		,R_PRINT ");
		sql.append(" 		,R_WRITE");
		sql.append(" 		,R_UPDATE");
		sql.append(" 		,ORDER_NUMBER");
		sql.append(" FROM ");
		sql.append(" 		SERVICES SV ");
		sql.append(" LEFT JOIN ");
		sql.append(" 		USER_ROLE_MAPPING URM   ");
		sql.append(" ON URM.SERVICE_CD = SV.SERVICE_CD");
		if(userRole !=null)
		{
			if(userRole.getCompany_cd() != null && userRole.getCompany_cd().trim().length()>0)
			{
				sql.append(" AND URM.CMPN_CD = ").append("'" + userRole.getCompany_cd() + "'");
			}
			if(userRole.getUser_cd() != null && userRole.getUser_cd().trim().length()>0)
			{
				sql.append(" AND URM.USER_CD = ").append("'" + userRole.getUser_cd() + "'");
			}
			if(userRole.getSerive_cd() != null && userRole.getSerive_cd().trim().length()>0)
			{
				sql.append(" AND URM.SERVICE_CD = ").append("'" + userRole.getSerive_cd() + "'");
			}
			if(userRole.getService_tx() != null && userRole.getService_tx().trim().length()>0)
			{
				sql.append(" AND SV.SERVICE_TX = ").append("'" + userRole.getService_tx() + "'");
			}
			if(userRole.getR_access() != null && userRole.getR_access().trim().length()>0)
			{
				sql.append(" AND URM.R_ACCESS = ").append("'" + userRole.getR_access() + "'");
			}
			if(userRole.getR_delete() != null && userRole.getR_delete().trim().length()>0)
			{
				sql.append(" AND URM.R_DELETE = ").append("'" + userRole.getR_delete() + "'");
			}
			if(userRole.getR_export() != null && userRole.getR_export().trim().length()>0)
			{
				sql.append(" AND URM.R_EXPORT = ").append("'" + userRole.getR_export() + "'");
			}
			if(userRole.getR_print() != null && userRole.getR_print().trim().length()>0)
			{
				sql.append(" AND URM.R_PRINT = ").append("'" + userRole.getR_print() + "'");
			}
			if(userRole.getR_read() != null && userRole.getR_read().trim().length()>0)
			{
				sql.append(" AND URM.R_READ = ").append("'" + userRole.getR_read() + "'");
			}
			if(userRole.getR_update() != null && userRole.getR_update().trim().length()>0)
			{
				sql.append(" AND URM.R_UPDATE = ").append("'" + userRole.getR_update() + "'");
			}
			if(userRole.getR_write() != null && userRole.getR_write().trim().length()>0)
			{
				sql.append(" AND URM.R_WRITE = ").append("'" + userRole.getR_write() + "'");
			}
			
		}
		sql.append(" WHERE 1=1 ");
		sql.append(" AND  SV.DELETE_FG ='0' ");
		
		
		sql.append(" ORDER BY ORDER_NUMBER ");
		
		return sql.toString();
	}

}
