<%@page import="java.awt.desktop.UserSessionEvent"%>
<%@page import="com.gadgetmart.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//get the session and request object
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute(StringUtil.SESSION_DATA);
String imgUrl = (String) userSession.getAttribute(StringUtil.SESSION_IMG);
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/header.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<nav>
		<img alt="logo"
			src="${pageContext.request.contextPath}/resources/images/system/GadgetMartLogo.png"
			class="logo">
		<ul>
			<c:choose>
				<c:when test="${not empty sessionScope.userID}">
					<li><a href="<%=contextPath + StringUtil.SERVLET_URL_HOME%>">Home</a></li>
					<li><a href="<%=contextPath + StringUtil.SERVLET_URL_SHOP%>">Shop</a></li>
					<li><a
						href="${pageContext.request.contextPath}/MemberServlet">About
							Us</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<%=contextPath + StringUtil.SERVLET_URL_HOME%>">Home</a></li>
					<li><a href="<%=contextPath + StringUtil.SERVLET_URL_SHOP%>">Shop</a></li>
					<li><a
						href="${pageContext.request.contextPath}/MemberServlet">About
							Us</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<form action="${pageContext.request.contextPath}/SearchServlet">
			<input type="search" class="search" name="keyword"
				placeholder="Search...">
			<button class="search-btn"
				style="position: relative; border-style: none; background-color: transparent; cursor: pointer;">
				<i class='bx bx-search'
					style="font-size: 45px; position: absolute; bottom: 16px; left: 450px;"></i>
			</button>
		</form>

		<img alt="User Profile" src="<%= imgUrl %>" class="profile-pic"
			onclick="toggleMenu()"
			onerror="this.src='${pageContext.request.contextPath}/resources/images/system/icons/user.png';">

		<div class="submenu-wrap" id="subMenu">
			<div class="submenu">
				<!-- When user is  logged in -->
				<c:choose>
					<c:when test="${not empty sessionScope.userID}">
						<a href="${pageContext.request.contextPath}/ProfileServlet"
							class="submenu-link"> <img alt="#"
							src="${pageContext.request.contextPath}/resources/images/system/icons/user.png"
							class="icon">
							<p>Profile</p> <span>></span>
						</a>
						<a href="${pageContext.request.contextPath}/DisplayCart"
							class="submenu-link">
							<div class="cart-item-pos">
								<img alt="#"
									src="${pageContext.request.contextPath}/resources/images/system/icons/shopping-cart.png"
									class="icon"><span class="cart-count">${itemCount}</span>
							</div>
							<p>Cart</p> <span>></span>
						</a>
						<a href="${pageContext.request.contextPath}/FetchCustomerOrder"
							class="submenu-link"> <img alt="#"
							src="${pageContext.request.contextPath}/resources/images/system/icons/shopping-bag.png"
							class="icon">
							<p>Order</p> <span>></span>
						</a>
						<a href="${pageContext.request.contextPath}/LogoutServlet"
							class="submenu-link"> <img alt="#"
							src="${pageContext.request.contextPath}/resources/images/system/icons/logout.png"
							class="icon">
							<p>Logout</p> <span>></span>

						</a>
					</c:when>

					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login"
							class="submenu-link"> <img alt="#"
							src="${pageContext.request.contextPath}/resources/images/system/icons/login.png"
							class="icon">
							<p>Login</p> <span>></span>
						</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<script type="text/javascript">
		let subMenu = document.getElementById("subMenu");
		function toggleMenu() {
			subMenu.classList.toggle("open-menu");
		}
	</script>
</body>
</html>