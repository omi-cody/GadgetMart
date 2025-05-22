package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.services.ProductServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productservice = new ProductServices();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Pages/Admin/admin_ProductUpdate.jsp").forward(request, response);
		
	}

	/**
	 * Handles HTTP POST requests sent to the servlet for updating product
	 * information. Retrieves product ID and forwards the request to the doPut
	 * method for processing.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String produpdId = request.getParameter("pID");
		
//		String productName = request.getParameter("pname");
		System.out.println("p id is " + produpdId);
		if (produpdId != null && !produpdId.isEmpty()) {
			doPut(request, response);
		}
	}

	/**
	 * Handles HTTP PUT requests sent to the servlet for updating product
	 * information. Retrieves product details from request parameters and updates
	 * the product in the database. Redirects the user to the appropriate page with
	 * success or error messages.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("pID");

		String productName = request.getParameter("pname");

		String priceStr = request.getParameter("pprice");
		System.out.println("price is " + priceStr);

		String screenSize = request.getParameter("psize");

		String ram = request.getParameter("pram");

		String processor = request.getParameter("pproc");

		String battery = request.getParameter("pbat");

		String storage = request.getParameter("pstor");

		String warranty = request.getParameter("pwar");

		String camera = request.getParameter("pcam");

		String discountStr = request.getParameter("pdis");

		String features = request.getParameter("pfeat");

		Part image = request.getPart("pimage2");

		String imageFilename = null;
		
		String imageUrl = request.getParameter("existingImage");

		if (image != null && image.getSize() > 0) {
			imageFilename = image.getSubmittedFileName();

		} else {
			imageFilename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1 );

		}

		System.out.println("name of file " + imageFilename);

		String DataBase_img_url = request.getContextPath() + "/resources/images/product_images/" + imageFilename;

		BigDecimal price = null;

		if (priceStr != null && !priceStr.isEmpty()) {

			price = new BigDecimal(priceStr);

		}
		System.out.println("id is " + productId);

		BigDecimal discount = null;

		if (discountStr != null && !discountStr.isEmpty()) {

			discount = new BigDecimal(discountStr);

		}

		BigDecimal discountamount = price.multiply(discount).divide(BigDecimal.valueOf(100));
		ProductModel updatedProduct = new ProductModel(productId, productName, screenSize, processor, ram, features,
				discount, storage, camera, warranty, price, DataBase_img_url, discountamount);
		System.out.println("Product ID: " + updatedProduct.getProductID());
		System.out.println("Product Name: " + updatedProduct.getProductName());
		System.out.println("Screen Size: " + updatedProduct.getScreenSize());
		System.out.println("Processor: " + updatedProduct.getProcessor());
		System.out.println("RAM: " + updatedProduct.getRam());
		System.out.println("Features: " + updatedProduct.getFeatures());
		System.out.println("Discount: " + updatedProduct.getDiscount());
		System.out.println("Storage: " + updatedProduct.getStorage());
		System.out.println("Camera: " + updatedProduct.getCamera());
		System.out.println("Warranty: " + updatedProduct.getWarranty());
		System.out.println("Price: " + updatedProduct.getPrice());
		System.out.println("Database Image URL: " + updatedProduct.getImageUrl());

		int success = productservice.updateProductById(productId, updatedProduct);

		System.out.println("int: " + success);

		if (success == 1) {
			// check if a filename is exists (not empty or null)
			if (!imageFilename.isEmpty() && imageFilename != null) {
				// construct the full image save path by combining the directory path and
				// filename
				String savePath = StringUtil.IMAGE_DIR_PRODUCT;

				image.write(savePath + imageFilename); // Save the uploaded image to the specified path

			}
			String sucessMessage = StringUtil.UPDTAE_PRODUCT_SUCCESS;
			request.getSession().setAttribute(StringUtil.MESSAGE_ERROR, sucessMessage);
			response.sendRedirect(request.getContextPath() + "/FetchProductForAdmin");
		} else {
			// If the update was not successful, you can also set an error message
			String errorMessage = StringUtil.UPDATE_PRODUCT_ERROR;
			request.getSession().setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			response.sendRedirect(request.getContextPath() + "/UpdateProductServlet");
		}

	}

}
