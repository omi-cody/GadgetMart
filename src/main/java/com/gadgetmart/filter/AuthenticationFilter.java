package com.gadgetmart.filter;

import java.io.IOException;

import com.gadgetmart.util.StringUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);

		boolean isLoggedIn = session != null && session.getAttribute(StringUtil.SESSION_DATA) != null;
		boolean isAdmin = session != null && (Boolean.TRUE.equals(session.getAttribute(StringUtil.ISADMIN)));
		boolean isLoginPage = uri.endsWith(StringUtil.LOGIN_ENDS) || uri.endsWith(StringUtil.LOGIN_SERVLET_ENDS);
		boolean isRegisterPage = uri.endsWith(StringUtil.REGISTER_ENDS)
				|| uri.endsWith(StringUtil.REGISTER_SERVLET_ENDS);
		boolean isLogoutServlet = uri.endsWith(StringUtil.LOGOUT_SERVLET_ENDS);
		boolean isProfileServlet = uri.endsWith(StringUtil.PROFILE_ENDS);
		boolean iscartPageServlet = uri.endsWith(StringUtil.CART_SERVLET_ENDS);
		boolean isOrderListServlet = uri.endsWith(StringUtil.ORDER_LIST_ENDS);
		boolean isCustomerMailServlet = uri.endsWith(StringUtil.INQUIRYDETAIL_SERVLET_END);
		boolean isMailListServlet = uri.endsWith(StringUtil.INQUIRYLIST_SERVLET_END);
		boolean isAddProductServlet = uri.endsWith(StringUtil.ADDPRODUCT_SERVLET_ENDS);
		boolean isCateogoryServlet = uri.endsWith(StringUtil.CATEGORY_SERVLET_ENDS);
		boolean isDashboardServlet = uri.endsWith(StringUtil.DASHBOARD_ENDS);
		boolean isDeleteInquiryByAdminServlet = uri.endsWith(StringUtil.DELETEINQUIRYADMIN_SERVLET_END);
		boolean isDeleteProdcutByAdminServlet = uri.endsWith(StringUtil.DELETEPRODUCTADMIN_SERVLET_END);
		boolean isfetchCustomerOrderServlet = uri.endsWith(StringUtil.CUSTOMERORDER_SERVLET_END);
		boolean isfetchProductAdminServlet = uri.endsWith(StringUtil.PRODUCTFORADMIN_SERVLET_END);
		boolean isfetchProductDetailAdminServlet = uri.endsWith(StringUtil.PRODUCTDETAILADMIN_SERVLET_END);
		boolean isInquiryServlet = uri.endsWith(StringUtil.INQUIRY_SERVLET_END);
		boolean isMemberServlet = uri.endsWith(StringUtil.MEMBER_SERVLET_END);
		boolean isOrderServlet = uri.endsWith(StringUtil.ORDER_SERVLET_END);
		boolean isProductDetailServlet = uri.endsWith(StringUtil.PRODUCTDETAIL_SERVLET_END);
		boolean isSearchServlet = uri.endsWith(StringUtil.SEARCH_SERVLET_END);
		boolean isShopServlet = uri.endsWith(StringUtil.SHOP_SERVLET_END);
		boolean isUpdatePhotoServlet = uri.endsWith(StringUtil.UPDATEPHOTO_SERVLET_END);
		boolean isProductByIdServlet = uri.endsWith("/ProductIdSent");
		boolean isUpdatePasswordServlet = uri.endsWith(StringUtil.UPDATEPASS_ENDS);
		boolean isUpdateProductServlet = uri.endsWith(StringUtil.UPDATEPRODUCT_ENDS);
		boolean isUpdateProfileServlet = uri.endsWith(StringUtil.UPDATEPROFILE_ENDS);
		boolean isUpdateOrderServlet = uri.endsWith(StringUtil.UPDATEORDER_ENDS);
		boolean isCheckoutServlet = uri.endsWith(StringUtil.CHECKOUT_ENDS);
		
		boolean adminOnly_page = isDashboardServlet || isOrderListServlet || isCustomerMailServlet || isAddProductServlet || isMailListServlet || isDeleteInquiryByAdminServlet
				
				|| isDeleteProdcutByAdminServlet || isfetchProductAdminServlet || isfetchProductDetailAdminServlet || isUpdateOrderServlet || isUpdateProductServlet || isProductByIdServlet;
		
		boolean userOnly_Page = isProfileServlet || isCateogoryServlet || isfetchCustomerOrderServlet || isInquiryServlet || isMemberServlet || isOrderServlet 
				|| isProductDetailServlet || isSearchServlet || isShopServlet ||isUpdatePhotoServlet || isUpdatePasswordServlet ||isUpdateProfileServlet || isCheckoutServlet || iscartPageServlet;

		boolean isStaticResource = uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/images/");
		if (isStaticResource || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".gif") || uri.endsWith(".png")) {
			chain.doFilter(request, response); // Skip filtering for static resources
			return;
		}

		if (isLoggedIn) { // for login
			if (isAdmin) {
				if (isLoginPage || isRegisterPage) {
					res.sendRedirect(req.getContextPath() + "/DashboardServlet");
					return;
				} else if (adminOnly_page || isLogoutServlet) { //Not allowing admin to access user pages

					chain.doFilter(request, response);
				}

				else { // if admin try to access user page then redirect to dashboard

					res.sendRedirect(req.getContextPath() + "/DashboardServlet");
				}

			}

			else { // Normal user logic
				if (isLoginPage || isRegisterPage || adminOnly_page) {
					res.sendRedirect(req.getContextPath() + "/HomeServlet");
					return;
				}

				else {

					chain.doFilter(request, response);
				}
			}
		} else { // Not logged in

			if (userOnly_Page|| adminOnly_page) {
				res.sendRedirect(req.getContextPath() + "/login");
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
	}



}
