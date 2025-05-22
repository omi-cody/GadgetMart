package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CustomerInquiryModel;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.InquiryServices;

/**
 * Servlet implementation class InquiryDetailServlet
 */
@WebServlet("/InquiryDetailServlet")
public class InquiryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryServices inquiryservice = new InquiryServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inquiryId = request.getParameter("InquiryId");
		System.out.println("id of admin panel is " + inquiryId);
		ArrayList<CustomerInquiryModel> matchedInquiry = inquiryservice.getInquirybyID(inquiryId);
		if (matchedInquiry != null && !matchedInquiry.isEmpty()) {

			HttpSession adminSession = request.getSession();

			adminSession.setAttribute("MatchingInquiry", matchedInquiry);
			
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/customer_mail.jsp").forward(request, response);

			

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
