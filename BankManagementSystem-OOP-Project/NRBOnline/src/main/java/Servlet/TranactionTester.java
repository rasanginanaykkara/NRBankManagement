package Servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import DBEntities.AtoATransaction;
import DBEntities.AtoLTransaction;
import DBEntities.SavingsAccount;

/**
 * Servlet implementation class TranactionTester
 */
@WebServlet("/trans")
public class TranactionTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranactionTester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
//		AtoATransaction trans = new AtoATransaction(1005705,3000,1005703);
//		trans.makePayment();
		
//		AtoATransaction trans = new AtoATransaction(6);
//		trans.undoPayment();
		
//		AtoLTransaction trans = new AtoLTransaction(1005705,140300);
//		trans.makePayment();
		
//		ArrayList<AtoLTransaction> list = AtoLTransaction.getAllTransactionsPerUser("199902700422");
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		ArrayList<AtoLTransaction> list = AtoLTransaction.getAllTransactionsPerUser("199902700422");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
