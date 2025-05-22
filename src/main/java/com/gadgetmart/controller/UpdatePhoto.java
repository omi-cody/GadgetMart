package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.UserModel;
import com.gadgetmart.services.UserServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class UpdatePhoto
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdatePhoto" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdatePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userservice = new UserServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handle get if needed
	}

	/**
	 * Handles HTTP POST requests sent to the servlet for updating user profile
	 * photos. Retrieves user ID from session, uploads the new profile image, and
	 * updates the user profile image URL in the database. Redirects the user to the
	 * appropriate page with success or error messages.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);

		Part image = request.getPart("profileImage");

		String imageFilename = image.getSubmittedFileName();

		String DataBase_img_url = request.getContextPath() + "/resources/images/users/" + imageFilename;

		UserModel user = new UserModel(DataBase_img_url, userId);

		int result = userservice.addusersimg(user);

		if (result == 1) {
			//check if  a filename is exists (not empty or null)
			if(!imageFilename.isEmpty() && imageFilename != null) {
				//construct the full image save path by combining the directory path and filename
				String savePath = StringUtil.IMAGE_DIR_USERS;
				
				image.write(savePath + imageFilename); //Save the uploaded image to the specified path
				
			}

			response.sendRedirect(request.getContextPath() + "/ProfileServlet?success=true");
		} else if (result == 0) {
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
		} else {
			String errorMessage = StringUtil.SERVER_NOT;
			request.setAttribute(StringUtil.ERROR_VAL, errorMessage);
			request.getRequestDispatcher(StringUtil.REGISTER_PAGE).forward(request, response);
		}
	}
	

}
