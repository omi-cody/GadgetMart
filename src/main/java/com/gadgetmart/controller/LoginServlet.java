package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseConfig dbconfig;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        this.dbconfig = new DatabaseConfig();
        // TODO Auto-generated constructor stub
    }

	/**
	 *  * Handles HTTP POST requests sent to the servlet for user login. Retrieves user
	 * ID and password from request parameters. Performs user authentication using
	 * the database controller. Sets session attributes based on user role and
	 * redirects to respective dashboards. If login fails, sets appropriate error
	 * messages and forwards to the login page.
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String loginUser = request.getParameter(StringUtil.LOGIN_USER);
		String loginPass = request.getParameter(StringUtil.LOGIN_PASSWORD);
		
		//perform the authentication
		int loginResult = dbconfig.getUserLoginInfo(loginUser, loginPass);
		
		
		
		
		
		
	}

}
