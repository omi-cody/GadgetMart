package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.UserModel;
import com.gadgetmart.services.UserServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfileServlet" })
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userservice = new UserServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Handles HTTP POST requests sent to the servlet for updating user profiles.
	 * Forwards the request to the doPut method for processing.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPut(request, response);
	}
	
	/**
	 * Handles HTTP PUT requests sent to the servlet for updating user profiles.
	 * Retrieves user details from request parameters and updates the user profile
	 * in the database. Redirects the user to the appropriate page with success or
	 * error messages.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userID = request.getParameter("userid");
		String fullName = request.getParameter("fulName");
		String Address = request.getParameter("address");

		UserModel usermodel = new UserModel(userID, fullName, Address);

		int result = userservice.userProfileUpdate(usermodel);

		if (result == 1) {

			String successMessage = "Profile Sucessfully Updated";

			request.getSession().setAttribute(StringUtil.ERROR_VAL, successMessage);

			response.sendRedirect(request.getContextPath() + "/ProfileServlet?success=true");

		} else if (result == 0) {

			request.getRequestDispatcher("/ProfileServlet").forward(request, response);

		} else {

			response.sendRedirect(request.getContextPath() + "/LogoutServlet");
		}

	}
	

}
