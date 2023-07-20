package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBEntities.Account;
import DBEntities.FixedDeposit;
import DBEntities.SavingsAccount;

/**
 * Servlet implementation class DepositMoney
 */
@WebServlet("/deposit")
public class DepositMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositMoney() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accType = Integer.parseInt(request.getParameter("accType"));
		int accNumber = Integer.parseInt(request.getParameter("accNum"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		if(accType == Account.SAVINGS) {
			SavingsAccount savingsAccount = new SavingsAccount();			
			savingsAccount.depositMoney(accNumber, amount);
			
		}else if(accType == Account.FIXED) {
			FixedDeposit fixedDeposit = new FixedDeposit();
			fixedDeposit.depositMoney(accNumber, amount);
		}
	}

}
