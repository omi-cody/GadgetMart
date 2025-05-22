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
import com.gadgetmart.model.CartModel;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.CartServices;
import com.gadgetmart.services.ProductServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckOutServlet" })
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
	private final CartServices cartservice = new CartServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles HTTP POST requests sent to the servlet for processing checkout.
	 * Retrieves user and cart information from session and request parameters.
	 * Fetches cart items from the database based on the user ID. Retrieves
	 * corresponding product details for each cart item. Populates a list with
	 * selected products for checkout. Sets attributes for chosen products and total
	 * amount in the request. Redirects the user to the order confirmation page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieving user session and user ID
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		String total = request.getParameter("grandTotal");

		// Remove duplicate declaration of cardId
		String cardId = "";

		List<CartModel> cartItems = cartservice.getCartProduct(userId);

		ArrayList<ProductModel> chooseproductsInCart = new ArrayList<>();

		for (CartModel cartItem : cartItems) {

			String productId = cartItem.getProductId();

			String quantity = cartItem.getQuantity();

			// Assign the value of cartId
			cardId = cartItem.getCartId();

			ArrayList<ProductModel> product = productservice.getProductbyID(productId);

			for (ProductModel p : product) {

				p.setQuantity(quantity);
			}

			chooseproductsInCart.addAll(product);
		}

		// for debugging purpose..
		if (chooseproductsInCart.isEmpty()) {

			System.out.println("The Serv cart is empty.");

		} else {
			System.out.println("The serv cart is not empty. It contains " + chooseproductsInCart.size() + " products.");
		}

		request.setAttribute("chooseproductsInCart", chooseproductsInCart);

		request.setAttribute("grandTotal", total);

		// Set cardId in the session
		userSession.setAttribute("cardId", cardId);

		request.getRequestDispatcher(StringUtil.ORDER_CONFIRM).forward(request, response);
	}
	
}
