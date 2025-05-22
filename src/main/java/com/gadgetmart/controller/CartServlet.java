package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CartModel;
import com.gadgetmart.services.CartServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CartServices cartservice = new CartServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Handles HTTP POST requests sent to the servlet for managing user's cart.
	 * Retrieves user and product information from request parameters. Checks if the
	 * product already exists in the user's cart to prevent duplication. Adds the
	 * product to the user's cart if it's not a duplicate. Redirects the user to
	 * appropriate pages based on the result of adding the product to the cart.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		String userId = (String) session.getAttribute(StringUtil.SESSION_DATA);
		String productId = request.getParameter(StringUtil.PROD_ID_CART);
		String cartId = StringUtil.CARD_ID;
		String quantity = request.getParameter(StringUtil.QUANT);
		

		// Checking for duplicate products in the cart
		if (cartservice.checkDuplicateCartProduct(userId, productId, StringUtil.CHECK_DUP_CART)) {
			String errorMessage = StringUtil.CART_ERROR_MSG;
			request.setAttribute(StringUtil.ERROR, errorMessage);
			request.getRequestDispatcher("/ShopServlet").forward(request, response);
			return;
		}
		
		if (userId == null) {
			request.getRequestDispatcher("/WEB-INF/Pages/User/login.jsp").forward(request, response);
			
		}else {
			// Creating a CartsModel object for the new cart item
			CartModel cartmodel = new CartModel(cartId, userId, productId, quantity);
			// Adding the product to the user's cart
			int result = cartservice.addProductsCart(cartmodel);

			// Handling redirection based on the result of adding the product to the cart
			if (result == 1) {
				// Redirecting to fetch products servlet
				response.sendRedirect(request.getContextPath() + StringUtil.FETCH_PRODUCT_SERVLET);

			} else if (result == 0) {
				request.getRequestDispatcher(StringUtil.REDIRECT_SERVLET).forward(request, response);
				String errorMessage = StringUtil.SERVER_NOT;
				request.setAttribute(StringUtil.ERROR_VAL, errorMessage);

			}
			
		}
		

	}

}
