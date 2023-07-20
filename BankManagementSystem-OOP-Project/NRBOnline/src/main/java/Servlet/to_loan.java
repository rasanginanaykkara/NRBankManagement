package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBEntities.AtoLTransaction;

@WebServlet("/AtoLtransactions")

public class to_loan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public to_loan() {
        super();
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int from_account = Integer.parseInt(request.getParameter("faccount"));
		int loan_number =Integer.parseInt(request.getParameter("lnumber"));
		
		AtoLTransaction atoLTransaction = new AtoLTransaction(from_account,loan_number);
		atoLTransaction.makePayment();
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/transactions.jsp");
		dispatcher.forward(request,response);
	}

}
