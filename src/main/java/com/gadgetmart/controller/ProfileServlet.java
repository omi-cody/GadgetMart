package com.gadgetmart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.UserModel;
import com.gadgetmart.services.UserServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProfileServlet" })
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userservice = new UserServices();
     
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession userSession = request.getSession();
		String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
		

		if (userId == null) {
			// Redirect to login page or display an error message
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String successMessage = (String) request.getSession().getAttribute(StringUtil.ERROR_VAL);
		if (successMessage != null) {
			request.setAttribute(StringUtil.ERROR_VAL, successMessage);
			request.getSession().removeAttribute(StringUtil.ERROR_VAL);

		}

		String successMessagepass = (String) request.getSession().getAttribute("pass");
		if (successMessagepass != null) {
			request.setAttribute("succpass", successMessagepass);
			request.getSession().removeAttribute("pass");

		}

		String successMessagenopass = (String) request.getSession().getAttribute("nopass");
		if (successMessagenopass != null) {
			request.setAttribute("nosuccpass", successMessagenopass);
			request.getSession().removeAttribute("nopass");

		}

		String noServer = (String) request.getSession().getAttribute("noserver");
		if (noServer != null) {
			request.setAttribute("noser", successMessagenopass);
			request.getSession().removeAttribute("noserver");

		}

		List<UserModel> userDetails = userservice.getProfileInfo(userId);
		String img_Url = userservice.getPhotoLink(userId);
		System.out.println(img_Url);

		request.setAttribute("imgUsers", img_Url);
		request.setAttribute("userDetail", userDetails);
		request.getRequestDispatcher("/WEB-INF/Pages/User/profile.jsp").forward(request, response);
	}

	

}
