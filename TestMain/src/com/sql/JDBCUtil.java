package com.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 杩炴帴鏁版嵁搴�
 *
 */
public class JDBCUtil{

	private String propertiesfile = "db.properties";
	
	public String getPropertiesfile() {
		return propertiesfile;
	}

	public void setPropertiesfile(String propertiesfile) {
		this.propertiesfile = propertiesfile;
	}

	public JDBCUtil(String propertiesfile) {
		super();
		this.propertiesfile = propertiesfile;
	}

	public JDBCUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connection GetConnection() throws Exception{
		Connection con = null;
		Properties props = new Properties();
		try {
			InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream(propertiesfile);
			props.load(in);
			String drivers = props.getProperty("db.driverClassName");
			String url = props.getProperty("db.url");
			try {
				Class.forName(drivers);
				con = DriverManager.getConnection(url);
			}catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException=="+e.getMessage());
			} catch (SQLException e) {
				System.out.println("SQLException=="+e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception=="+e.getMessage());
				throw new Exception("Exception");
			}
		} catch (FileNotFoundException e1) {
			System.out.println("--FileNotFoundException "+e1.getMessage());
		} catch (IOException e1) {
			System.out.println("--IOException "+e1.getMessage());
		} catch (Exception e) {
			System.out.println("--Exception "+e.getMessage());
			throw new Exception("--Exception");
		}
		return con;
	}
	
	public boolean closeConn(Connection conn)
	{
		try {
			if(conn !=null && !conn.isClosed())
			{
				conn.close();
				return true;
			}
		} catch (Exception e) {
			System.out.println("close Exception"+e.getMessage());
			return false;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
