package Servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Servlet implementation class DPGetter
 */
@WebServlet("/DPGetter")
public class DPGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DPGetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		String url = (String)request.getParameter("url");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		FileInputStream fileInputStream = new FileInputStream(url);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		int pointer = 0;
		while((pointer = bufferedInputStream.read())!= -1) {
			bufferedOutputStream.write(pointer);
		}
		servletOutputStream.close();
		fileInputStream.close();
		bufferedOutputStream.close();
		bufferedInputStream.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
