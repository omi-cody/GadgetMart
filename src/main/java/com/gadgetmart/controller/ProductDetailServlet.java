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

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductDetailServlet" })
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doGet(request, response);
     
    }

    /**
	 * Handles HTTP GET requests sent to the servlet for retrieving product details.
	 * Retrieves product ID from request parameter. Retrieves matching product
	 * details from the database. Forwards the matching product details to the
	 * single product page for display.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productIde = request.getParameter("product_");
		List<ProductModel> matchedProducts = productservice.getProductbyID(productIde);

		request.setAttribute("MatchingProducts", matchedProducts);
		request.getRequestDispatcher("/WEB-INF/Pages/User/productDetail_view.jsp").forward(request, response);
	}
	
	
}
