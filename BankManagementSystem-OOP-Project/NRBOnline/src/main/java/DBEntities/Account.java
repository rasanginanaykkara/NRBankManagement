package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionVariables.SQLConnector;

public abstract class Account {
	public final static int ACTIVE = 1;
	public final static int DEACTIVE = 2;
	public final static int FREEZED = 3;
	public final static int SAVINGS = 1;
	public final static int FIXED = 2;
	
	
	protected int accountNumber;
	protected String userID;
	protected float balance;
	protected int status;
	protected float rate;
	public Account() {
		
	}
	public Account(String userID, float balance , int status) {
		
		this.userID = userID;
		this.balance = balance;
		this.status = status;
	}
	
	public Account(int accountNumber, String userID) {
		Connection connection = SQLConnector.getDBConnection();
		String query = "SELECT * FROM account WHERE user_id = '"+userID+"' AND accountNumber = "+accountNumber+"";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				this.accountNumber = accountNumber;
				this.userID = userID;
				this.balance = resultSet.getFloat("balance");
				this.status = resultSet.getInt("isActivated");
			}else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addInstanceToDB() {
		Connection connection = SQLConnector.getDBConnection();
		String query = "INSERT INTO account (user_id, balance, is_activated)"
				+ " VALUES ('"+this.userID+"',"+this.balance+","+this.status+")";
		System.out.println(query);
		try {
			Statement statement = connection.createStatement();
			boolean val = statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getLastAccountNo() {
		Connection connection = SQLConnector.getDBConnection();
		String idGettingQuery = "SELECT TOP 1 account_number FROM account ORDER BY account_number DESC";
		int lastId = -1;
		Statement idGetter;
		try {
			idGetter = connection.createStatement();
			ResultSet idSet = idGetter.executeQuery(idGettingQuery);
			if(idSet.next()) {
				lastId = idSet.getInt("account_number");
			}else {
				lastId = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastId;
	}
	public void depositMoney(int accountNumber, float amount) {
		System.out.println("invoke deposit method");
		if(loadInstance(accountNumber)) {
			Connection conn = SQLConnector.getDBConnection();
			String query = "UPDATE account SET balance = "+(balance + amount)
					+ " WHERE account_number = "+accountNumber+"";
			
			System.out.println(query);
			try {
				Statement stmnt = conn.createStatement();
				if(stmnt.execute(query))
					System.out.println("balance update successful");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			System.out.println("account cannot be loaded");
	}
	public void deleteAccount() {
		Connection conn = SQLConnector.getDBConnection();
		String query = "DELETE FROM account WHERE account_number = "+accountNumber;
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("account deletion successful");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	abstract public boolean loadInstance(int accountNumber);
	
}
