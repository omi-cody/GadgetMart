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
import com.gadgetmart.model.CustomerOrderModel;
import com.gadgetmart.model.CustomerTransaction;
import com.gadgetmart.services.OrderServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class FetchCustomerOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/FetchCustomerOrder" })
public class FetchCustomerOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OrderServices orderservice = new OrderServices();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCustomerOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();

		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);


		List<CustomerOrderModel> listings = orderservice.getCustomerOrder(userId);

		request.setAttribute("Orderlistings", listings);
		

		request.getRequestDispatcher("/WEB-INF/Pages/User/customerOrder.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
