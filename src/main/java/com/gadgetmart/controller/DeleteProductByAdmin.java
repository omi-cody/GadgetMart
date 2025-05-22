package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.services.ProductServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class DeleteProductByAdmin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProductByAdmin" })
public class DeleteProductByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductByAdmin() {
        super();
        
    }

    /**
	 * Handles HTTP GET requests sent to the servlet. This method currently returns
	 * a message indicating that the servlet is served at a specific context path.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * Handles HTTP POST requests sent to the servlet. This method retrieves the ID
	 * of the product to be deleted from the request parameters. If the ID is not
	 * null or empty, it triggers the doDelete method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String deleteId = request.getParameter("productId");

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	/**
	 * Handles HTTP DELETE requests sent to the servlet. This method receives the ID
	 * of the product to be deleted from the request parameters. It triggers the
	 * deletion operation in the database controller and redirects the user based on
	 * the result.
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");

		// Get the productId from the request
		String productId = req.getParameter("productId");
		

		// Delete the product and get the result
		int result = productservice.deleteProductInfo(productId);

		// Debugging: Print productId and result to console
		System.out.println("Product ID: " + productId);

		System.out.println("Delete Result: " + result);

		// Check the result of the deletion operation
		if (result == 1) {
			// Deletion successful: Set success message attribute and redirect
			req.getSession().setAttribute(StringUtil.MESSAGE_ERROR,StringUtil.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtil.ADMIN_FETCH_PROD);
		} else {
			// Deletion failed: Set error message attribute and redirect
			req.getSession().setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtil.ADMIN_FETCH_PROD);
		}

	}

}
