package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import DBEntities.AtoATransaction;
import DBEntities.AtoLTransaction;

@WebServlet("/TransHistory")

public class trans_history extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public trans_history() {
        super();
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userID");
		ArrayList<AtoLTransaction> aToLList = AtoLTransaction.getAllTransactionsPerUser(userId);
		ArrayList<AtoATransaction> aToAList = AtoATransaction.getAllTransactionsPerUser(userId);
		request.setAttribute("aList", aToAList);
		request.setAttribute("lList",aToLList);
		
		for(int i =0; i<aToLList.size();i++) {
			System.out.println("invoking");
			System.out.println(aToAList.get(i).getAmount());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/trans_history.jsp");
		dispatcher.forward(request,response);
	}

}
