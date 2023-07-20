package ConnectionVariables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLConnector {
	private  Connection mainConnection;
	private static SQLConnector connector;
	private SQLConnector() {		
		String url = "jdbc:sqlserver://localhost:1433;user=root;password=1234;encrypt=false;databaseName=NRB;";		
		try {			
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			mainConnection = DriverManager.getConnection(url);
			System.out.println("connected");			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	public static Connection getDBConnection() {
		if(connector == null) {
			connector = new SQLConnector();
		}
		return connector.mainConnection;
	}
}
