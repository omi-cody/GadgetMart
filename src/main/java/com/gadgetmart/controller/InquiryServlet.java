package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.InquiryModel;
import com.gadgetmart.services.InquiryServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/InquiryServlet")
public class InquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryServices inquiryservice = new InquiryServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieving user session
				HttpSession userSession = request.getSession();
				String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
				// Redirect to login page if user is not logged in
				if (userId == null) {

					response.sendRedirect(request.getContextPath() + "/login");
					return;
				}
				// Retrieving inquiry subject and message from request parameters
				String subject = request.getParameter("subject");
				String message = request.getParameter("message");
				String inquiryID = UUID.randomUUID().toString();

				String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				InquiryModel inq = new InquiryModel(inquiryID, userId, subject, createdAt, message);

				int result = inquiryservice.addUserInquiry(inq);

				System.out.println(result);
				// Handling the result of adding the inquiry
				if (result == 1) {

					String errorMessage = "Your Message is Sucessfully Delivered !!!";
					request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
					request.getRequestDispatcher("/WEB-INF/Pages/User/about.jsp").forward(request, response);

				} else if (result == 0) {
					// If adding the inquiry fails, set error message attribute and forward to about
					// page
					String errorMessage = "Something Went Wrong Please Try Again Later !!!";
					request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
					request.getRequestDispatcher("/WEB-INF/Pages/User/about.jsp").forward(request, response);

				} else {
					// If there is an unexpected error, redirect to logout servlet
					response.sendRedirect(request.getContextPath() + "/LogoutServlet");

				}
				

	}

}
