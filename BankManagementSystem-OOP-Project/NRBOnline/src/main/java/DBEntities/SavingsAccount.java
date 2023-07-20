package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionVariables.SQLConnector;

public class SavingsAccount extends Account{	
	private String accType;
	public SavingsAccount() {
		
	}
	
	public SavingsAccount(String userID, float balance , int status,String accType) {
		super(userID, balance,status);		
		this.accType = accType;
	}
	public void addInstanceToDB() {
		Connection conn = SQLConnector.getDBConnection();		
		super.addInstanceToDB();			
		String query = "INSERT INTO savings_account VALUES ("+Account.getLastAccountNo()+",'"+this.accType+"')";
		System.out.println(query);
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("Savings account created successfuly");
			else
				System.out.println("Savings account creation failed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean loadInstance(int accountNumber) {
		boolean ret = false;
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * FROM account a "
				+ "JOIN savings_account s ON a.account_number = s.account_number "
				+"JOIN account_type t ON t.type_name = s.type_name "
				+ "WHERE a.account_number = "+accountNumber;
		try {
			Statement stmnt = conn.createStatement();
			ResultSet resultSet = stmnt.executeQuery(query);
			if(resultSet.next()) {
				this.accountNumber = accountNumber;
				this.balance = resultSet.getFloat("balance");
				this.userID = resultSet.getString("user_id");
				this.status = resultSet.getInt("is_activated");
				this.accType = resultSet.getString("type_name");
				this.rate = resultSet.getFloat("interest_rate");
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Account with follwing details are being loaded to the ram ");
		System.out.println(this.accountNumber);
		System.out.println(this.userID);
		System.out.println(this.status);
		System.out.println(this.balance);
		System.out.println(ret);
		return ret;
		
	}
	@Override
	public void deleteAccount() {
		Connection conn = SQLConnector.getDBConnection();
		String query = "DELETE FROM savings_account WHERE account_number = "+accountNumber;
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("savings account deletion successful");
			super.deleteAccount();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}		
	}
	public boolean withdrawMoney(float amount) {
		boolean ret = false;
		if(this.balance<amount) {
			return false;
		}else {
			balance -= amount;
			String query ="UPDATE account SET balance =  "+balance+" WHERE account_number = "+accountNumber;
			Connection conn = SQLConnector.getDBConnection();
			try {
				Statement stmnt = conn.createStatement();
				if(stmnt.execute(query))
					System.out.println("withdraw failed");
				else {
					ret = true;
					System.out.println("withdraw passed");
				}
					System.out.println("withdraw passed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return ret;
	}
	

}
