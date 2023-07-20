package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBEntities.Loan;
@WebServlet("/ReqLoan")
public class RequestLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nic = request.getParameter("nic");
			String guaranter = request.getParameter("guaranter");
			Float basevalue = Float.parseFloat(request.getParameter("basevalue")) ;
			int installment = Integer.parseInt(request.getParameter("installments"));
			Loan loan = new Loan(nic,guaranter,basevalue,installment);
			loan.saveInstanceToDB();
			RequestDispatcher dis = getServletContext().getRequestDispatcher("/views/Loan-Request.jsp");
			dis.forward(request, response);
	}

}
