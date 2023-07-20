package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import DBEntities.User;

/**
 * Servlet implementation class UserRemover
 */
@WebServlet("/UserRemover")
public class UserRemover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRemover() {
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
		String nic = (String) request.getParameter("nic");		
		File file = new File("C:/NRBData/ProfilePictures/"+nic+".jpg");
		//System.out.println(getServletContext().getRealPath("\\images\\profilePictures")+"\\"+nic+".jpg");
		if(file.delete()) {
			System.out.println("deletion successful");
		}
		User.deleteUser(nic);
	}

}
