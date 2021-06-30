package com.ams.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ams.database.DatabaseConnection;
import com.ams.model.UserModel;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class SystemControl {
	
	public static String CompanyCDUser = null;
	public static String CompanyCDCurrent= null;
	public static String EmployeeCD = null;

	public SystemControl(HttpServletRequest request)
	{
		CompanyCDUser = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_CMPN_CD);
		CompanyCDCurrent = (String) request.getSession().getAttribute(SessionConstants.SUB_SYSTEM_CD);
		EmployeeCD = (String) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
	}
	
	
	public String getParameter(String parameter_tx)
	{
		try {
			return excute(CompanyCDCurrent, parameter_tx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String excute(String cmpn_cd, String parameter_tx) throws SQLException
	{
		//Kết nối cơ sở dữ liệu
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
	
		result = stmt.executeQuery(getSql(cmpn_cd,parameter_tx));
		String value = "";
		while (result.next()) {
			value = result.getString("CONTROL_VALUE");
		}
		
		return value;
	}
	
	public String getSql(String cmpn_cd, String parameter_tx)
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT *");
		sql.append(" FROM");
		sql.append(" SYSTEM_CONTROL");
		sql.append(" WHERE");
		sql.append(" 1=1 ");
		//sql.append(" AND CMPN_CD =").append("'"+cmpn_cd+"'");
		sql.append(" AND CONTROL_TX =").append("'"+parameter_tx+"'");
		
		
		return sql.toString();
	}
}
