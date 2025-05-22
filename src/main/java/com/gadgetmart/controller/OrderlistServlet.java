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
import com.gadgetmart.model.CustomerTransaction;
import com.gadgetmart.model.OrdersModel;
import com.gadgetmart.services.OrderServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class OrderlistServlet
 */
@WebServlet( "/OrderlistServlet" )
public class OrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OrderServices orderservice = new OrderServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession userSession = request.getSession();

		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);


		List<CustomerTransaction> listings = orderservice.getAllOrdersDetails();

		request.setAttribute("listings", listings);
		

		request.getRequestDispatcher("/WEB-INF/Pages/Admin/adminOrder_list.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
