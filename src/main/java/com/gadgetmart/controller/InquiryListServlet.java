package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CustomerInquiryModel;
import com.gadgetmart.model.CustomerTransaction;
import com.gadgetmart.services.InquiryServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class InquiryListServlet
 */
@WebServlet("/InquiryListServlet")
public class InquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryServices inquiryservice = new InquiryServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();

		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);


		List<CustomerInquiryModel> listings = inquiryservice.getAllInquiryDetails();

		request.setAttribute("Inquirylistings", listings);
		

		request.getRequestDispatcher("/WEB-INF/Pages/Admin/adminMail_list.jsp").forward(request, response);
	}

	

}
