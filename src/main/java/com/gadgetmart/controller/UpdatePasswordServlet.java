package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.services.UserServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdatePasswordServlet" })
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userservice = new UserServices();
   
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Handles HTTP POST requests sent to the servlet for updating user passwords.
	 * Retrieves user ID, current password, new password, and confirmation password
	 * from request parameters. Validates the complexity of the new password and
	 * checks if it matches the confirmation password. Updates the user password in
	 * the database if validation passes. Redirects the user to the profile page
	 * with success or error messages accordingly.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userId");
		String currentPass = request.getParameter("currpass");
		String newPass = request.getParameter("newpass");
		String confPass = request.getParameter("confpass");

		// Validate password complexity and match with retype password
		if (newPass.length() <= 8 || !newPass.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).*$")) {
			String errorMessage = StringUtil.PASSW_NON;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			System.out.println("Updatepassservlet error 1");
			return;
		}

		if (!newPass.equals(confPass)) {
			String errorMessage = StringUtil.PASSW_NON_NOT;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
			System.out.println("Updatepassservlet error 2");
			return;
		}

		int passChangeResult = userservice.changeLoginInfo(userID, currentPass, newPass);

		if (passChangeResult == 1) {

			String successPass = "Password Sucessfully Changed";

			request.getSession().setAttribute("pass", successPass);

			response.sendRedirect(request.getContextPath() + "/ProfileServlet?success=true");

		} else if (passChangeResult == 4) {

			String successnoPass = "Your Current Password Did not Matches";

			request.getSession().setAttribute("nopass", successnoPass);

			response.sendRedirect(request.getContextPath() + "/ProfileServlet?success=true");

		} else {
			System.out.println("Updatepassservlet error 4");
			response.sendRedirect(request.getContextPath() + "/LogoutServlet");
		}
	}
	

}
