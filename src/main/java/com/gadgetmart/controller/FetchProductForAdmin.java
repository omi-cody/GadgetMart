package com.gadgetmart.controller;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.ProductServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class FetchProductForAdmin
 */
@WebServlet("/FetchProductForAdmin")
public class FetchProductForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchProductForAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ProductModel> allProducts = productservice.getAllProductInfo();

		System.out.println("(FetchProductForAdmin)product size is: " + allProducts.size());

		// Set product list as a request attribute
		request.setAttribute("allProducts", allProducts);
		// Forward the request to the admin page
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/adminProduct_view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
