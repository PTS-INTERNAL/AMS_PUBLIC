package com.ams.database;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ams.util.DatabaseCommon;


public class DatabaseConnection {
	Connection conn = null;
	public DatabaseConnection()
	{
		
	}
	
	public Connection getConnection() {
		
		try {
			   /*DB Customer*/
			   String ipVtec= "";
			   ipVtec = InetAddress.getByName("asset.viettien.com.vn").toString().replace("asset.viettien.com.vn/", "");
			   String DB_URL = "jdbc:sqlserver://"+ipVtec+"\\SQLEXPRESS:"+"14337"+";databaseName="+DatabaseCommon.DB_NAME+"";
			   DatabaseCommon.DB_IP = ipVtec;
			   

			 /*DB Local*/
			 // String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:"+"1433"+";databaseName=AMS_30_04";


			   
			   Class.forName(DatabaseCommon.DRIVERSQLSERVER).newInstance();
	           conn = DriverManager.getConnection(DB_URL, DatabaseCommon.DB_USER, DatabaseCommon.DB_PASS);
	            if(conn!=null){
	            	System.out.println("Ket Noi Thanh Cong!!");
	            }
	            else{
	            	System.out.println("Ket Noi That Bai!!");
	            }     
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return conn;
	}
	
	public boolean closeConnection() {
		
		try {
			this.conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
