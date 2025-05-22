package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.ProductServices;

/**
 * Servlet implementation class ProductDetailAdmin
 */
@WebServlet("/ProductDetailAdmin")
public class ProductDetailAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productid = request.getParameter("productId");

		System.out.println("id of admin panel is " + productid);

		ArrayList<ProductModel> selectedProducts = productservice.getProductbyID(productid);

		System.out.println("size is " + selectedProducts.size());

		if (selectedProducts != null && !selectedProducts.isEmpty()) {

			HttpSession adminSession = request.getSession();

			adminSession.setAttribute("selectedProducts", selectedProducts);

			request.getRequestDispatcher("/WEB-INF/Pages/Admin/adminProductDetail_view.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
