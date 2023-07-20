package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBEntities.AtoATransaction;


@WebServlet("/AtoAtransactions")

public class to_account extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public to_account() {
        super();
        
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int from_account = Integer.parseInt(request.getParameter("faccount"));
		int to_account =Integer.parseInt(request.getParameter("taccount"));
		float amount = Float.parseFloat(request.getParameter("amount")); 
	
		AtoATransaction atoATransaction = new AtoATransaction(from_account, amount, to_account);
		atoATransaction.makePayment();		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/transactions.jsp");
		dispatcher.forward(request,response);
	}
	

}
