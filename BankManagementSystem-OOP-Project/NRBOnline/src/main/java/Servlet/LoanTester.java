package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import DBEntities.Loan;
import DBEntities.User;

/**
 * Servlet implementation class LoanTester
 */
@WebServlet("/loan-tester")
public class LoanTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanTester() {
        super();
        // TODO Auto-generated constructor stub
    }
    //String loanOwnerNIC, String loanGuaranter, float baseValue, int installmentsNos
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String loanOwnerNIC = "199902700422";
//		String loanGuaranter = "199902700422";
//		float baseValue = 5000;
//		int installments = 3;
//		Loan loan = new Loan(loanOwnerNIC,loanGuaranter,baseValue,installments);
//		loan.saveInstanceToDB();
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Loan loan = new Loan(140300);
//		loan.loadInstance();
//		System.out.println(loan.getInstallments().size());
//		System.out.println(loan.getinstallmentNos());
//		System.out.println(loan.getInstallmentValue());
//		loan.updateStatus(Loan.APPROVED);
//		loan.payIn();
		ArrayList<User> list = User.getAllusers();
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i).getEmail());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
	}

}
