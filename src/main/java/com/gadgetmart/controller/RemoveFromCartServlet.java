package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.services.CartServices;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveFromCartServlet" })
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CartServices cartservice = new CartServices();   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * Handles HTTP POST requests sent to the servlet for removing items from the
	 * cart. Retrieves the product ID of the item to be removed from the request
	 * parameter. Deletes the item from the cart in the database. Redirects the user
	 * to the cart display page after successful removal.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");

		int rowsAffected = cartservice.deletecart(productId);

		if (rowsAffected > 0) {
			response.sendRedirect(request.getContextPath() + "/DisplayCart");
		}

		else {

			response.sendRedirect("/home");
		}

	}
}
