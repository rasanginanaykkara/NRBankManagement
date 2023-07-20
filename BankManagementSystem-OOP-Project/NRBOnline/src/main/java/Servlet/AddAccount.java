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
 * Servlet implementation class AddAccount
 */
@WebServlet("/accountAdder")
public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccount() {
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
	
		int type = Integer.parseInt(request.getParameter("type"));
		float initial = Float.parseFloat(request.getParameter("initial"));
		String userId = request.getParameter("uid");
		
		if(type == Account.SAVINGS) {
			String typeCode = request.getParameter("typeCode");
			SavingsAccount savingsAccount =new SavingsAccount(userId,initial,Account.ACTIVE, typeCode);
			savingsAccount.addInstanceToDB();
		}else if(type == Account.FIXED){
			int months = Integer.parseInt(request.getParameter("months"));
			FixedDeposit fixedDeposit = new FixedDeposit(userId, initial,Account.ACTIVE, months);
			fixedDeposit.addInstanceToDB();
		}
	}

}
