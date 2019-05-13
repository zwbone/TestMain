package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class TestConnSQLServer {

	private final static Logger logger = Logger.getLogger(TestConnSQLServer.class);
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JDBCUtil jdbcUtil = new JDBCUtil();
		Connection connection = jdbcUtil.GetConnection();
		StringBuffer sql = new StringBuffer("SELECT A.UserID,A.UserName,A.LoginID,B.AgentID FROM [dbCallcenter].[dbo].[tbUser] as A inner join [dbCallcenter].[dbo].[tbAgent] as B on A.LoginID=B.LoginID WHERE A.UserID='0205017059'");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql.toString());
		while (rs.next()) {
			rs.getString("AgentID");
			System.out.println("AgentID = "+rs.getString("AgentID"));
			//logger.info(rs.getString("AgentID"));
		}
	}

}
