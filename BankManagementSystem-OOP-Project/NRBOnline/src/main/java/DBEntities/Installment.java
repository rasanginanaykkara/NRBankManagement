package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ConnectionVariables.SQLConnector;

public class Installment {
	private int installmentNo;
	private int isPayed;
	private Date dueDate;
	public final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public final static int PAID = 1;
	public final static int NOT_PAID = 0;
	public Installment() {
		
	}
	public Installment(int installmentNo, int isPayed, String date) {
		
		try {
			this.installmentNo = installmentNo;
			this.isPayed = isPayed;
			this.dueDate  = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getDueDate() {
		return dueDate;
	}public int getInstallmentNo() {
		return installmentNo;
	}public int isPayed() {
		return isPayed;
	}public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setInstallmentNo(int installmentNo) {
		this.installmentNo = installmentNo;
	}public void setPayed(int isPayed) {
		this.isPayed = isPayed;
	}
	public void saveToDb(int loanNo) {
		String query = "INSERT INTO installment VALUES ("+loanNo+","+installmentNo+","
						+isPayed+",'"
						+dateFormat.format(dueDate)+"')";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query)) {
				System.out.println("installment entring failed");
			}else {
				System.out.println("installment entering seccess");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}public void deleteFromDB(int loanNo) {
		String query = "DELETE FROM installment WHERE loan_number  = "+loanNo
				+" AND installment_number = "+this.installmentNo;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("deletion failed");
			else
				System.out.println("deletion successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void markAsPaid(int loanNo) {
		String query = "UPDATE installment SET "+
						"is_payed = "+Installment.PAID+
						" WHERE loan_number = "+loanNo+
						" AND installment_number = "+this.installmentNo;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("update failed");
			else
				System.out.println("update successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadFromDB(int loanNo, int installmentNo) {
		String query = "SELECT * FROM installment WHERE loan_number = "
						+loanNo+" AND installment_number = "
						+installmentNo;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				this.installmentNo = installmentNo;
				this.isPayed = rs.getInt("is_payed");
				this.dueDate = dateFormat.parse(rs.getString("due_date"));				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
