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
import com.gadgetmart.services.DashboardServices;
import com.gadgetmart.util.StringUtil;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DashboardServlet" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DashboardServices dashboardservice = new DashboardServices();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		


			HttpSession userSession = request.getSession();
			String userId = (String) userSession.getAttribute(StringUtil.SESSION_DATA);


			int orderItemCount = dashboardservice.getOrderCount();

			int pendingCount = dashboardservice.getPendingCount();

			int deliveredCount = dashboardservice.getdeliveredCount();
			
			int canceledCount = dashboardservice.getcanceledCount();
			int totalProductOrderCount = dashboardservice.getTotalproductorderCount();
			
			List<Integer> eachCount =dashboardservice.getEachProductCount();
			
			List<Integer> ordereachCount =dashboardservice.getorderEachProductCount();
			
			for (Integer integer : ordereachCount) {
				System.out.println(integer);
			}


			request.setAttribute("orderItemCount", orderItemCount);

			request.setAttribute("pendingCount", pendingCount);

			request.setAttribute("deliveredCount", deliveredCount);
			
			request.setAttribute("canceledCount", canceledCount);
			
			request.setAttribute("eachCount", eachCount);
			
			request.setAttribute("ordereachCount", ordereachCount);
			
			request.setAttribute("totalproductOrder", totalProductOrderCount);
			
			request.getRequestDispatcher("/WEB-INF/Pages/Admin/adminDashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
