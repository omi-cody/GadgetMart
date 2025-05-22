package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.services.InquiryServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class DeleteInquiryByAdmin
 */
@WebServlet("/DeleteInquiryByAdmin")
public class DeleteInquiryByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryServices inquiryservice = new InquiryServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInquiryByAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("InquiryId");

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");

		// Get the productId from the request
		String inquiryId = req.getParameter("InquiryId");
		

		// Delete the product and get the result
		int result = inquiryservice.deleteInquiry(inquiryId);

		// Debugging: Print productId and result to console
		System.out.println("Product ID: " + inquiryId);

		System.out.println("Delete Result: " + result);

		// Check the result of the deletion operation
		if (result == 1) {
			// Deletion successful: Set success message attribute and redirect
			req.getSession().setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + "/InquiryListServlet");
		} else {
		
			resp.sendRedirect(req.getContextPath() + "/InquiryListServlet");
		}

	}

}
