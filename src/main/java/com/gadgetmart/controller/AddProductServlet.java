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
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductServices productService = new ProductServices();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/Pages/Admin/add_product.jsp").forward(request, response);
    }
    
    /**
	 * Handles HTTP POST requests sent to the servlet for adding a new product to
	 * the database. Retrieves product information such as ID, name, price,
	 * specifications, and image from the request parameters. Saves the product
	 * image to a specified location on the server filesystem. Calculates discount
	 * amount based on the provided discount percentage. Creates a new ProductsModel
	 * object representing the product with the obtained information. Adds the
	 * product to the database using the DatabaseController instance. Redirects the
	 * user to appropriate pages based on the result of adding the product.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter(StringUtil.PID);

		String productName = request.getParameter(StringUtil.PNAME);

		String priceStr = request.getParameter(StringUtil.PPRICE);

		String screenSize = request.getParameter(StringUtil.PSIZE);

		String ram = request.getParameter(StringUtil.PRAM);

		String processor = request.getParameter(StringUtil.PPROC);

		String storage = request.getParameter(StringUtil.PSTOR);

		String warranty = request.getParameter(StringUtil.PWAR);

		String camera = request.getParameter(StringUtil.PCAM);

		String discountStr = request.getParameter(StringUtil.PDIS);

		String features = request.getParameter(StringUtil.PFEAT);

		Part image = request.getPart(StringUtil.PIMG);
		//Get the image file name from the product object
		String imageFilename = image.getSubmittedFileName();
		
		String DataBase_img_url= request.getContextPath() + "/resources/images/product_images/" + imageFilename;
		
		
		

	


		BigDecimal price = null;

		if (priceStr != null && !priceStr.isEmpty()) {

			price = new BigDecimal(priceStr);

		}

		BigDecimal discount = null;

		if (discountStr != null && !discountStr.isEmpty()) {

			discount = new BigDecimal(discountStr);

		}

		BigDecimal discountamount =price.multiply(discount).divide(BigDecimal.valueOf(100));
		BigDecimal discountPrice =price.subtract(discountamount);

		ProductModel product = new ProductModel(productId, productName, screenSize, processor, ram, features, discount, storage, camera, warranty, price, DataBase_img_url, discountPrice);

		int result = productService.addProducts(product);
System.out.println(result);
		if (result == 1) {
			//check if  a filename is exists (not empty or null)
			if(!imageFilename.isEmpty() && imageFilename != null) {
				//construct the full image save path by combining the directory path and filename
				String savePath = StringUtil.IMAGE_DIR_PRODUCT;
				image.write(savePath + imageFilename); //Save the uploaded image to the specified path
				
			}
			String errorMessage = StringUtil.ADD_PRODUCT_SUCCESS;
			request.getSession().setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher("/FetchProductForAdmin").forward(request, response);
			// need to change

		} else if (result == 0) {
			String errorMessage = StringUtil.SERVER_NOT;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.ADDPRODUCT_SERVLET_ENDS).forward(request, response);

		} else {

			String errorMessage = StringUtil.ADD_PRODUCT_ERROR;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.ADDPRODUCT_SERVLET_ENDS).forward(request, response);
	

		}

	}

}
