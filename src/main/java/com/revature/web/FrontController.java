package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI().replace("/FrontControllerDemo/", "");
		
		switch(URI) {
		
		case "login":
			// Call the login method and pass the request and response object
			RequestHelper.processLogin(request, response);
			break;
		case "error":
			// Call some method that processes a 404 error
			RequestHelper.processError(request, response);
			break;
		case "employees":
			RequestHelper.processEmployees(request, response);
			break;
		default:
			RequestHelper.processError(request, response);
			break;
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
