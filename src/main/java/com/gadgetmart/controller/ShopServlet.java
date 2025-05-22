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
 * Servlet implementation class ShopServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ShopServlet" })
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices producservice = new ProductServices();
	private final CartServices cartservice = new CartServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
    

	/**
	 *Handles HTTP GET requests sent to the servlet for fetching products.
	 * Retrieves user session and user ID.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		
		int cartItemCount = cartservice.getCartItemCount(userId);
		userSession.setAttribute("itemCount", cartItemCount);
		
		List<ProductModel> allProducts = producservice.getAllProductInfo();
		request.setAttribute("productList", allProducts);
		request.getRequestDispatcher("/WEB-INF/Pages/User/shop.jsp").forward(request, response);
	}


}
