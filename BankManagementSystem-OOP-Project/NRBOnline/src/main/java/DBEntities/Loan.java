package DBEntities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ConnectionVariables.SQLConnector;

public class Loan {
	public final static int NOT_GUARENTEED = 0;
	public final static int GUARENTEED = 1;
	public final static int APPROVED = 2;
	public final static int DECLINED = -1;
	public final static float INTEREST_RATIO = (float)1.25;
	
	private int loanNumber;
	private String loanOwnerNIC;
	private String loanGuaranter;
	private float baseValue;
	private float total;
	private int status;
	private int installmentNos;
	
	private ArrayList<Installment> installments;
	
	public Loan(int loanNumber) {
		this.loanNumber = loanNumber;
		installments = new ArrayList<>();
	}
	
	public Loan(String loanOwnerNIC, String loanGuaranter, float baseValue, int installmentsNos) {
		installments = new ArrayList<>();
		this.loanOwnerNIC = loanOwnerNIC;
		this.loanGuaranter = loanGuaranter;
		this.baseValue = baseValue;
		this.status = Loan.NOT_GUARENTEED;
		this.installmentNos = installmentsNos;
		setInstallments(installmentsNos);
		
	}
	public void setInstallments(int installmentsNos) {
		this.total = baseValue * INTEREST_RATIO;
		Calendar calender = Calendar.getInstance();
		
		for(int i = 0; i<installmentsNos; i++) {
			calender.add(Calendar.MONTH, 1);
			String date = Installment.dateFormat.format(calender.getTime());
			installments.add(new Installment((i+1), Installment.NOT_PAID, date));
		}
		
	}
	public float getInstallmentValue() {
		return (total/installments.size());
	}
	public int getinstallmentNos() {
		String query = "SELECT TOP 1 installment_number FROM installment WHERE loan_number = "+this.loanNumber+
				" ORDER BY installment_number DESC";
		Connection conn = SQLConnector.getDBConnection();
		int nos = 0;
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				nos = rs.getInt("installment_number");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return nos;
	}
	public void loadInstance() {
		String query = "SELECT * FROM loan WHERE loan_number = "+this.loanNumber;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				this.baseValue = rs.getFloat("base_value");
				this.loanGuaranter = rs.getString("loan_guarantor");
				this.loanOwnerNIC = rs.getString("loan_owner");
				this.status = rs.getInt("is_activated");
				this.total = rs.getFloat("total");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadInstallments();
	}
	public static int getLastLoanNumber() {
		int lastLoanNumber = 0;
		String query  =  "SELECT TOP 1 loan_number FROM loan ORDER BY loan_number DESC";
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				lastLoanNumber = rs.getInt("loan_number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastLoanNumber;		
	}
	public void saveInstanceToDB() {
		String query =  "INSERT INTO loan("
				+ "loan_owner,loan_guarantor,base_value,"
				+ "total,is_activated) VALUES('"
				+ loanOwnerNIC
				+ "','"+loanGuaranter+"',"+baseValue+","+total+","+NOT_GUARENTEED+")";
		System.out.println(query);
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println("loan adding failed");
			else
				System.out.println("loan adding succeded");
			loanNumber = getLastLoanNumber();
			for(int i = 0; i<installments.size();i++) {
				installments.get(i).saveToDb(loanNumber);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadInstallments() {
		int nos = getinstallmentNos();
		for(int i =0;i<nos;i++) {
			Installment installment = new Installment();
			installment.loadFromDB(loanNumber, (i+1));
			installments.add(installment);
		}
	}
	public void updateStatus(int code) {
		status = code;
		String query = "UPDATE loan SET is_activated = "+code+" WHERE loan_number = "+loanNumber;
		Connection conn = SQLConnector.getDBConnection();
		try {
			Statement stmnt = conn.createStatement();
			if(stmnt.execute(query))
				System.out.println(" update unsuccess");
			else
				System.out.println("update success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Installment> getInstallments() {
		return installments;		
	}
	public void payIn() {
		Installment ins = getUnpaidInstallment();
		if(ins != null) {
			ins.markAsPaid(loanNumber);			
		}	
		
	}
	public Installment getUnpaidInstallment() {
		for(int i = 0; i<installments.size(); i++) {
			if(installments.get(i).isPayed() == Installment.NOT_PAID) {
				return installments.get(i);
			}
		}
		return null;
	}
	
	
	
	
}
