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
 * Servlet implementation class CategoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CategoryServlet" })
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		String category = request.getParameter("brand");
		String categoryPattern = category + "%"; // Correctly format the search pattern

		List<ProductModel> categoryProducts = productservice.getCategoryProduct(categoryPattern);

		// Matching products found, show the filtered list
		request.setAttribute("productList", categoryProducts);
		request.getRequestDispatcher("/WEB-INF/Pages/User/shop.jsp").forward(request, response);

	}
}
