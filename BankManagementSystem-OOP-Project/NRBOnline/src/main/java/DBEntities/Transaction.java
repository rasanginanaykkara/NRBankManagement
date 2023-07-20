package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ConnectionVariables.SQLConnector;

public abstract class Transaction {
	public final static DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
	protected int transactionId;
	protected int hostAccount;
	protected LocalDateTime date;
	protected float amount;
	protected Transaction(int transactionId) {
		this.transactionId = transactionId;		
	}
	public float getAmount() {
		return amount;
	}
	public int getHostAccount() {
		return hostAccount;
	}public LocalDateTime getDate() {
		return date;
	}public static DateTimeFormatter getTimestampformat() {
		return timeStampFormat;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public Transaction(int hostAccount, float amount) {		
		this.amount = amount;
		this.hostAccount = hostAccount;
		date = LocalDateTime.now();		
	}
	abstract boolean makePayment();	
	
	public void addInstanceToDB() {
		String query = "INSERT into \"transaction\" (host_account, amount , date) VALUES "+
				"("+this.hostAccount+","+this.amount+",'"+timeStampFormat.format(date)+"')";
		Connection conn = SQLConnector.getDBConnection();
		
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("un success trans parent");
			else
				System.out.println("Success trans parent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadInstanceFromDB() {
		String query = "SELECT * FROM \"transaction\" WHERE transaction_id = "+this.transactionId;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while (rs.next()) {
				this.amount = rs.getFloat("amount");
				this.date = LocalDateTime.parse(rs.getString("date"),timeStampFormat);
				this.hostAccount = rs.getInt("host_account");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int getLastTransactionId() {
		int transId = 0;
		String query = "SELECT TOP 1 transaction_id FROM \"transaction\" ORDER BY transaction_id DESC";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				transId = rs.getInt("transaction_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transId;
		
	}
	
	
}
