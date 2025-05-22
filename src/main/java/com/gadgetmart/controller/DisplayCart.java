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
 * Servlet implementation class DisplayCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DisplayCart" })
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CartServices cartservice = new CartServices();  
	private final ProductServices productservice = new ProductServices();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();

		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);

		System.out.println("userId: " + userId);

		List<CartModel> cartItems = cartservice.getCartProduct(userId);

		ArrayList<ProductModel> productsInCart = new ArrayList<>();

		int itemCount = cartItems.size();

		userSession.setAttribute("itemCount", itemCount);

		for (CartModel cartItem : cartItems) {

			String productId = cartItem.getProductId();

			// Get the count of items in the cart 

			String quantity = cartItem.getQuantity();

			userSession.setAttribute("productId", productId);

			ArrayList<ProductModel> product = productservice.getProductbyID(productId);

			for (ProductModel p : product) {
				p.setQuantity(quantity);
			}

			productsInCart.addAll(product);

			if (productsInCart.isEmpty()) {

				System.out.println("The cart is empty.");

			} else {

				System.out.println("The cart is not empty. It contains " + productsInCart.size() + " products.");

			}

		}

		request.setAttribute("productsInCart", productsInCart);

		request.getRequestDispatcher(StringUtil.CART_VIEW_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
