package com.ams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ams.database.DatabaseConnection;
import com.ams.model.ServiceModel;



public class ServiceSelectDao {
	
	ServiceModel serviceModel;
	
	public ServiceSelectDao()
	{
		
	}
	
	public ServiceSelectDao(ServiceModel serviceModel)
	{
		this.serviceModel = serviceModel;
	}
	
	public List<ServiceModel> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		//System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<ServiceModel> lstRole =new ArrayList<ServiceModel>();
		while (result.next()) {
			ServiceModel role = new ServiceModel();
			role.setService_name(result.getString("SERVICE_NAME"));
			role.setService_tx(result.getString("SERVICE_TX"));
			role.setService_mapping(result.getString("URL_MAPPING"));
			role.setService_id(result.getString("SERVICE_CD"));
			lstRole.add(role);
		}
		return lstRole;
	}

	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 		SERVICE_TX ");
		sql.append(" 		,URL_MAPPING ");
		sql.append(" 		,SERVICE_NAME ");
		sql.append(" 		,DELETE_FG ");
		sql.append(" 		,SERVICE_CD ");
		sql.append(" FROM ");
		sql.append(" 		SERVICES  ");
		sql.append(" WHERE  DELETE_FG = '0'");
		if(serviceModel!=null)
		{
			if(serviceModel.getService_tx()!=null && serviceModel.getService_tx().trim().length()>0)
			{
				sql.append(" AND  SERVICE_TX = ").append("'"+serviceModel.getService_tx()+"'");
			}
		}
		
		
		return sql.toString();
	}

}
