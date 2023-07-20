package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionVariables.SQLConnector;

public class AtoLTransaction extends Transaction{
	private int loanAccount;
	private Loan loan;
	public AtoLTransaction(int transactionId) {
		super(transactionId);		
	}
	public AtoLTransaction(int hostAccount,int loanAccount) {
		super(hostAccount,0);
		this.loanAccount = loanAccount;
		this.loan = new Loan(loanAccount);
		loan.loadInstance();
		super.amount = loan.getInstallmentValue();
		
			
	}
	public int getLoanAccount() {
		return loanAccount;
	}

	@Override
	public boolean makePayment() {
		SavingsAccount host = new SavingsAccount();	
		host.loadInstance(hostAccount);
		if(host.withdrawMoney(amount)) {
			loan.payIn();
			addInstanceToDB();
			return true;
		}
		return false;			
		
	}

	@Override
	public void loadInstanceFromDB() {
		super.loadInstanceFromDB();
		String query  = "SELECT * FROM \"to_loan_trans\" WHERE transaction_id = "+this.transactionId;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				this.loanAccount = rs.getInt("loan_number");
				this.loan = new Loan(loanAccount);
				loan.loadInstance();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void addInstanceToDB() {		
		super.addInstanceToDB();
		String query = "INSERT INTO to_loan_trans VALUES ("
				+Transaction.getLastTransactionId()+","+
						this.loanAccount+")";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("AtoL  load failed");
			else
				System.out.println("AtoL load passed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<AtoLTransaction> getAllTransactionsPerUser(String userId){
		ArrayList<AtoLTransaction> list = new ArrayList<>();
		String query = "SELECT t.transaction_id FROM to_loan_trans lt JOIN \"transaction\" t ON "
				+"lt.transaction_id = t.transaction_id "
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
				AtoLTransaction atoLTransaction = new AtoLTransaction(id);
				atoLTransaction.loadInstanceFromDB();
				list.add(atoLTransaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
