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
import com.gadgetmart.services.ProductServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SearchServlet" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		String keyword = request.getParameter("keyword");
		String searchPattern = "%" + keyword + "%"; // Correctly format the search pattern
		
		List<ProductModel> searchedProducts = productservice.getProductbySearch(searchPattern);
		
		if (searchedProducts.isEmpty()) {
			// No matching products found, show all products
			List<ProductModel> allProducts = productservice.getAllProductInfo();
			request.setAttribute("productList", allProducts);
			request.setAttribute("search_keyword", keyword);
			request.getRequestDispatcher("/WEB-INF/Pages/User/shop.jsp").forward(request, response);

		} else {
			// Matching products found, show the filtered list
			request.setAttribute("productList", searchedProducts);
			request.setAttribute("search_keyword", keyword);
			request.getRequestDispatcher("/WEB-INF/Pages/User/shop.jsp").forward(request, response);
		}
	}

	

}
