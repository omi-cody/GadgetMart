package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.UserModel;
import com.gadgetmart.util.StringUtil;
import com.gadgetmart.util.ValidationUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2 MB
maxFileSize = 1024 * 1024 * 10, //10 MB
maxRequestSize = 1024 * 1024 * 50) //50 MB
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseConfig dbconfig;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		this.dbconfig = new DatabaseConfig();
		// TODO Auto-generated constructor stub
	}

	/**
	 *  Handles HTTP POST requests sent to the servlet for user registration.
	 * Retrieves user registration details from request parameters. Validates user
	 * registration details such as user ID, full name, email, phone number, and
	 * password. Checks for data duplication in the database. Adds the new user to
	 * the database. Redirects the user to the login page after successful
	 * registration.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter(StringUtil.userFIELD);
		String fullName = request.getParameter(StringUtil.FULLNAME_FIELD);
		String email = request.getParameter(StringUtil.EMAIL_FIELD);
		String phoneNumber = request.getParameter(StringUtil.PHONE_FIELD);
		String password = request.getParameter(StringUtil.PASSW_FIELD);
		String retypePassword = request.getParameter(StringUtil.REPASSW_FIELD);
		String gender = request.getParameter(StringUtil.GENDER_FIELD);
		String role = StringUtil.ROLE_FIELD;
		
		if (userID == null || userID.trim().isEmpty() || userID.length()<=6) {
			String errorMessage = "not valid";
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
		}
		if (fullName == null || fullName.trim().isEmpty()) {
			String errorMessage = StringUtil.FULL_NAME_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
			
		}
		
		//validation of username
		if (!ValidationUtil.isUserIDValid(userID)) {
			String errorMessage = StringUtil.USER_ID_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		//validation of fullname
		if (!ValidationUtil.isText(fullName)) {
			String errorMessage = StringUtil.FULL_NAME_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
			
		}
		
		
		//validation of email
		if (!ValidationUtil.isEmail(email)) {
			String errorMessage = StringUtil.EMAIL_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		//validation of phone number
		if (phoneNumber.length() != 10) {
			String errorMessage = StringUtil.PHONE_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
			
		}
		//validation of password field
		if (!ValidationUtil.isPasswordValid(password)) {
			String errorMessage = StringUtil.PASSW_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
		}
		//checking if the retype password is same as password
		
		if (!password.matches(retypePassword)) {
			String errorMessage = StringUtil.PASSW_NON_NOT;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		//crating the usermodel object
		UserModel user = new UserModel(userID, fullName.toUpperCase(), email, password, phoneNumber, gender, role);
		
		int result = dbconfig.addUser(user);
		if (result == 1) {
			response.sendRedirect(request.getContextPath() + StringUtil.LOGIN_PAGE);
		}else if (result == 0) {
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
		} else {
			String errorMessage = StringUtil.SERVER_NOT;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);

		}
	
	}

}
