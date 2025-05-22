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
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.CartServices;
import com.gadgetmart.services.ProductServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home","/"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CartServices cartservice = new CartServices();
	private final ProductServices productservice = new ProductServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		
		int cartItemCount = cartservice.getCartItemCount(userId);
		userSession.setAttribute("itemCount", cartItemCount);
		
		List<ProductModel> topProducts = productservice.getTopProduct();
		request.setAttribute("productList", topProducts);
		request.getRequestDispatcher(StringUtil.USER_HOME).forward(request, response);

	}

	
}
