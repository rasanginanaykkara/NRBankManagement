package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionVariables.SQLConnector;

public class FixedDeposit extends Account{
	private int numOfMonths;
	public FixedDeposit() {
		
	}
	public FixedDeposit(String userID, float balance , int status,int numOfMonths) {
		super(userID,balance,status);
		this.numOfMonths = numOfMonths;
	}
	@Override
	public void addInstanceToDB() {
		Connection conn = SQLConnector.getDBConnection();		
		super.addInstanceToDB();			
		String query = "INSERT INTO fixed_deposit VALUES ("+Account.getLastAccountNo()+","+this.numOfMonths+")";
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("Fixed deposit created successfuly");
			else
				System.out.println("Fixed deposit creation failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean loadInstance(int accountNumber) {
		boolean ret = false;
		Connection conn = SQLConnector.getDBConnection();
		String query = "SELECT * FROM account a "
				+ "JOIN fixed_deposit f ON a.account_number = f.account_number "
				+" JOIN duration_type d ON f.num_of_months = d.num_of_months "
				+ "WHERE a.account_number = "+accountNumber;
		try {
			Statement stmnt = conn.createStatement();
			System.out.println(query);
			ResultSet resultSet = stmnt.executeQuery(query);
			if(resultSet.next()) {
				this.accountNumber = accountNumber;
				this.balance = resultSet.getFloat("balance");
				this.userID = resultSet.getString("user_id");
				this.status = resultSet.getInt("is_activated");
				this.numOfMonths = resultSet.getInt("num_of_months");
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
		return false;
	}
	@Override
	public void deleteAccount() {
		Connection conn = SQLConnector.getDBConnection();
		String query = "DELETE FROM fixed_deposit WHERE account_number = "+accountNumber;
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query)) 
				System.out.println("fixed deposit deletion successful");
			super.deleteAccount();
			
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
