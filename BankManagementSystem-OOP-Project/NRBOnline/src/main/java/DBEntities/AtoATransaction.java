package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionVariables.SQLConnector;

public class AtoATransaction extends Transaction{
	private int payeeAccount;
	public AtoATransaction(int transactionId) {
		super(transactionId);		
	}
	public AtoATransaction(int hostAccount, float amount,int payeeAccount) {
		super(hostAccount,amount);
		this.payeeAccount = payeeAccount;		
	}
	public int getPayeeAccount() {
		return payeeAccount;
	}

	@Override
	public boolean makePayment() {
		SavingsAccount host = new SavingsAccount();
		
		SavingsAccount payee = new SavingsAccount();
		
		boolean v = host.loadInstance(hostAccount);
		if(host.withdrawMoney(amount)) {
			payee.depositMoney(payeeAccount, amount);
			addInstanceToDB();
			return true;
		}
		return false;
			
		
	}
	public boolean undoPayment() {
		loadInstanceFromDB();
		SavingsAccount host = new SavingsAccount();
		
		SavingsAccount payee = new SavingsAccount();
		
		boolean v = host.loadInstance(payeeAccount);
		if(host.withdrawMoney(amount)) {
			payee.depositMoney(hostAccount, amount);
			deleteInstanceFromDB();
			return true;
		}
		return false;
	}
	@Override
	public void loadInstanceFromDB() {
		super.loadInstanceFromDB();
		String query  = "SELECT * FROM \"to_account_trans\" WHERE tansaction_id = "+this.transactionId;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				this.payeeAccount = rs.getInt("payee_account");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void deleteInstanceFromDB() {
		String query = "DELETE FROM to_account_trans WHERE tansaction_id = "+transactionId+";"
		+"DELETE FROM \"transaction\" WHERE transaction_id = "+transactionId+";";
		Connection conn = SQLConnector.getDBConnection();
		Statement stmnt;
		try {
			stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("deleted");
			else
				System.out.println("deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void addInstanceToDB() {		
		super.addInstanceToDB();
		String query = "INSERT INTO to_account_trans VALUES ("
				+Transaction.getLastTransactionId()+","+
						this.payeeAccount+")";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("AtoA  load failed");
			else
				System.out.println("AtoA load passed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<AtoATransaction> getAllTransactionsPerUser(String userId){
		ArrayList<AtoATransaction> list = new ArrayList<>();
		String query = "SELECT t.transaction_id FROM to_account_trans lt JOIN \"transaction\" t ON "
				+"lt.tansaction_id = t.transaction_id "
				+ "JOIN account a ON a.account_number = t.host_account "
				+ "JOIN \"user\" u ON a.user_id = u.nic WHERE u.nic = '"
				+ userId+"'";
		System.out.println(query);
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("transaction_id");
				AtoATransaction atoATransaction = new AtoATransaction(id);
				atoATransaction.loadInstanceFromDB();
				list.add(atoATransaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	

}
