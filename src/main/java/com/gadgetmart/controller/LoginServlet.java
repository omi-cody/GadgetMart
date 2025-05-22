package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.services.UserServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userservice;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		this.userservice = new UserServices();
		// TODO Auto-generated constructor stub
	}

	/**
	 * * Handles HTTP POST requests sent to the servlet for user login. Retrieves
	 * user ID and password from request parameters. Performs user authentication
	 * using the database controller. Sets session attributes based on user role and
	 * redirects to respective dashboards. If login fails, sets appropriate error
	 * messages and forwards to the login page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Pages/User/login.jsp").forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String loginUser = request.getParameter(StringUtil.LOGIN_USER);
		String loginPass = request.getParameter(StringUtil.LOGIN_PASSWORD);

		// perform the authentication
		int loginResult = userservice.getUserLoginInfo(loginUser, loginPass);
		String img_Url = userservice.getPhotoLink(loginUser);

		System.out.println(loginResult);

		if (loginResult == 1) {
			// User is an admin
			HttpSession adminSession = request.getSession();
			adminSession.setAttribute(StringUtil.ISADMIN, true);
			adminSession.setAttribute(StringUtil.SESSION_DATA, loginUser);
			adminSession.setAttribute(StringUtil.SESSION_IMG, img_Url);
			adminSession.setMaxInactiveInterval(30 * 60);

			Cookie adminCookie = new Cookie(StringUtil.COOKIE_USER, loginUser);
			adminCookie.setMaxAge(30 * 60);
			response.addCookie(adminCookie);
			// Redirect to admin dashboard
			response.sendRedirect(request.getContextPath() + "/DashboardServlet");
		} else if (loginResult == 2) {
			// User is a regular user
			HttpSession userSession = request.getSession();
			userSession.setAttribute(StringUtil.ISADMIN, false);
			userSession.setAttribute(StringUtil.SESSION_DATA, loginUser);
			userSession.setAttribute(StringUtil.SESSION_IMG, img_Url);
			userSession.setMaxInactiveInterval(30 * 60);

			Cookie userCookie = new Cookie(StringUtil.COOKIE_USER, loginUser);
			userCookie.setMaxAge(30 * 60);
			response.addCookie(userCookie);
			// Redirect to user dashboard
			response.sendRedirect(request.getContextPath() + "/HomeServlet");
		} else if (loginResult == 3)

		{
			String errorMessage = StringUtil.ROLE_USER_NOT;

			request.setAttribute(StringUtil.ERROR, errorMessage);

			request.getRequestDispatcher(StringUtil.LOGIN_PAGE).forward(request, response);

		} else if (loginResult == 4) {
			String errorMessage = StringUtil.PASS_NOT;

			request.setAttribute(StringUtil.ERROR, errorMessage);

			request.getRequestDispatcher(StringUtil.LOGIN_PAGE).forward(request, response);

		}

		else if (loginResult == 0) {
			String errorMessage = StringUtil.ACCOUNT_NOT;

			request.setAttribute(StringUtil.ERROR, errorMessage);

			request.getRequestDispatcher(StringUtil.LOGIN_PAGE).forward(request, response);

		}

		else {

			String errorMessage = StringUtil.SERVER_NOT;

			request.setAttribute(StringUtil.ERROR, errorMessage);

			request.getRequestDispatcher(StringUtil.LOGIN_PAGE).forward(request, response);

		}

	}

}
