package DBEntities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionVariables.SQLConnector;

public class Test {
	public String name;
	public Test() {
		
		try {
			String query = "Select * from test";
			Statement stmt = SQLConnector.getDBConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				name = rs.getString("name");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
