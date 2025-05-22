package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.OrdersModel;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.OrderServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateOrder" })
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OrderServices orderservice = new OrderServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("oId");
		String status = request.getParameter("status");
		System.out.println("Order id is " + orderId);
		if (orderId != null && !orderId.isEmpty()) {
			doPut(request, response);
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("oId");
		String status = request.getParameter("status");

		OrdersModel order = new OrdersModel(orderId,status);
		int success = orderservice.updateOrder(orderId, order);

		System.out.println("int: " + success);
		response.sendRedirect(request.getContextPath() + "/OrderlistServlet");
	}

}
