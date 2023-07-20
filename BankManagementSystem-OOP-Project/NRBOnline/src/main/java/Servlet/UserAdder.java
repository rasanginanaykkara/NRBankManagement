package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import DBEntities.User;

/**
 * Servlet implementation class UserAdder
 */
@WebServlet("/UserAdder")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class UserAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		
		request.setAttribute("value", 234);
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part filePart = request.getPart("file");
		
		String nic =  (String)request.getParameter("nic");
		String email =  (String)request.getParameter("email");
		String name =  (String)request.getParameter("name");
		String address =  (String)request.getParameter("address");
		String imageURL =  "C:/NRBData/ProfilePictures/"+nic+".jpg";
		String password =  (String)request.getParameter("password");		
		User user = new User(nic,email,name,address,imageURL,password,2);
		user.addInstanceToDB();
	    String fileName = filePart.getSubmittedFileName();
	    filePart.write(imageURL);	    
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");		
		request.setAttribute("user", user);
		dispatcher.forward(request, response);		
	}

}
