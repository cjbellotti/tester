package com.tester.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class JDBCComponent extends BaseComponent {

	public JDBCComponent(IContext ctx) {
		super(ctx);
	}

	@Override
	public String task(String data) throws ComponentException {

		String result = "";
		
		String driver = this.getCtx().processData(this.getProperties().getProperty("DRIVER"));
		String url = this.getCtx().processData(this.getProperties().getProperty("HOST"));
		String dbName = this.getCtx().processData(this.getProperties().getProperty("DB_NAME"));
		String username = this.getCtx().processData(this.getProperties().getProperty("USERNAME"));
		String password = this.getCtx().processData(this.getProperties().getProperty("PASSWORD"));
		String query = this.getCtx().processData(this.getProperties().getProperty("QUERY"));
		
		try {
			Class.forName(driver).newInstance();
			
			Connection conn = DriverManager.getConnection(url + dbName, username, password);
			
			if (!conn.isClosed()) {
		
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				result =  this.formatResultToXml(rs);
				rs.close();
			} else 
				throw new ComponentException("JDBCComponent: ConnectionException: Error connecting to database");
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ComponentException("JDBCComponent: GeneralException: " + e.getMessage());
		} catch (SQLException e) {
			throw new ComponentException("JDBCComponent: SQLException: " + e.getMessage());
		}

		return result;
	}

	@Override
	public void init() {
	}

	public String formatResultToXml(ResultSet rs) {
		String xml = "<ResultSet>";
		
		try {
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			
			while (rs.next()) {
				xml += "<Row>";
				
				for (int i = 1; i <= columnCount; i++) {
					
					String columnName = rsmd.getColumnName(i);
					
					xml += "<" + columnName.toUpperCase() + ">";
					
					xml += rs.getString(columnName);
					
					xml += "</" + columnName.toUpperCase() + ">";
					
				}
				
				xml += "</Row>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xml += "</ResultSet>";

		return xml;
	}

}
