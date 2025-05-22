package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CartModel;
import com.gadgetmart.model.OrdersModel;
import com.gadgetmart.model.Product_OrderModel;
import com.gadgetmart.services.CartServices;
import com.gadgetmart.services.OrderServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet( "/OrderServlet" )
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OrderServices orderservice = new OrderServices(); 
	private final CartServices cartservice = new CartServices(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Handles HTTP POST requests sent to the servlet for processing orders.
	 * Retrieves session attributes for card ID and user ID. Generates a unique
	 * order ID. Retrieves order details such as total, address, city, and payment
	 * method from request parameters. Creates an OrdersModel object with the order
	 * details and adds it to the database. Retrieves cart details associated with
	 * the card ID. Adds order line items for each cart item to the database.
	 * Redirects the user to a thank you page after successful order processing.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cardId = (String) session.getAttribute("cardId");
		
		System.out.println(session);

		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		String orderid = UUID.randomUUID().toString();
		
		if (cardId == null || userId == null) {
		    System.out.println("cardId or userId is null");
		    response.sendRedirect(request.getContextPath() + "/login.jsp"); // or show an error page
		    return;
		}


		String total = request.getParameter("grandTotal");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String pay = request.getParameter("pay");

		System.out.println("cardId is" + cardId);
		System.out.println("total is" + total);

		String status = "pending";

		OrdersModel orders = new OrdersModel(orderid, userId, total, status, city, address, pay);

		int orderResult = orderservice.addOrder(orders);

		if (orderResult != 1) {
			return;
		}

		List<CartModel> cartDetails = cartservice.getCartDetail(cardId);

		boolean success = true;

		for (CartModel cartItem : cartDetails) {
			String productId = cartItem.getProductId();
			String Linequantity = cartItem.getQuantity();

			Product_OrderModel ordersprod = new Product_OrderModel(orderid, productId, Linequantity);

			int lineItemResult = orderservice.addOrderLineItem(ordersprod);

			if (lineItemResult != 1) {
				success = false;
			}
		}

		if (success) {
			for (CartModel cartItem : cartDetails) {
				String productId = cartItem.getProductId();

				int lineItemResult = cartservice.deletecart(productId);

				if (lineItemResult != 1) {
					System.out.println("Cart Not Deleted");
				}
			}
			request.getRequestDispatcher("/WEB-INF/Pages/User/thankyou.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/Pages/User/thankyou.jsp").forward(request, response);
		}
	}


}
