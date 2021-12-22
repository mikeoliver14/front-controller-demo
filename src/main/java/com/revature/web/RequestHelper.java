package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {

	// a logger
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	// an employeeService instance
	private static EmployeeService eserv = new EmployeeService(new EmployeeDAO());
	// an object mapper
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		logger.info("User attempted to login with username " + username);
		
		Employee e = eserv.confirmLogin(username, password);
		
		// If the user is not null, save the user to the session, and print user's info to the screen using PrintWriter
		
		if (e!=null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println(om.writeValueAsString(e));
			
		} else {
			
			// if returned object is null, return a HTTP status called no content
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found");
			response.setStatus(204);
			
		}
		
	}
	
	public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// redirect the user to a custom 404 page
		request.getRequestDispatcher("error.html").forward(request, response);
		
		
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1. set the content type of the response
		response.setContentType("text/html");
		
		// 2. call the findAll() method from the Service layer and save it in a list
		List<Employee> allEmployees = eserv.findAll();
		
		// 3. Marshall the list of Java Objects to JSON (using Jackson as our object mapper)
		String jsonString = om.writeValueAsString(allEmployees);
		
		// 4. Call the Print Writer to write it out to the client in the response body
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		
		
	}
	
}
