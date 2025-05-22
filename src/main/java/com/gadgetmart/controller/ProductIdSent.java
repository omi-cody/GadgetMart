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
 * Servlet implementation class ProductIdSent
 */
@WebServlet("/ProductIdSent")
public class ProductIdSent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductIdSent() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String updateid = request.getParameter("productId");

		System.out.println("updatefield" + updateid);

		ArrayList<ProductModel> selectedProducts = productservice.getProductbyID(updateid);

		System.out.println("size is " + selectedProducts.size());

		if (selectedProducts != null && !selectedProducts.isEmpty()) {

			HttpSession adminSession = request.getSession();

			adminSession.setAttribute("selectedProducts", selectedProducts);

			response.sendRedirect(request.getContextPath() + "/UpdateProductServlet");

		}
	}

}
