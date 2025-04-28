<%@page import="com.gadgetmart.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String contextPath = request.getContextPath();
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<li><a href="<%=contextPath + StringUtil.USER_HOME%>">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/Pages/shop.jsp">Shop</a></li>
			<li><a href="${pageContext.request.contextPath}/Pages/about.jsp">About
					Us</a></li>
		</ul>
		<form action="">
			<input type="search" class="search" placeholder="Search...">
			<button class="search-btn"
				style="position: relative; border-style: none; background-color: transparent; cursor: pointer;">
				<i class='bx bx-search'
					style="font-size: 45px; position: absolute; bottom: 16px; left: 450px;"></i>
			</button>
		</form>
		<img alt="profile"
			src="${pageContext.request.contextPath}/resources/images/system/profile.jpg"
			class="profile-pic" onclick="toggleMenu()">

		<div class="submenu-wrap" id="subMenu">
			<div class="submenu">
				<a href="#" class="submenu-link"> <img alt="#"
					src="${pageContext.request.contextPath}/resources/images/system/icons/user.png"
					class="icon">
					<p>Profile</p> <span>></span>
				</a> <a href="#" class="submenu-link"> <img alt="#"
					src="${pageContext.request.contextPath}/resources/images/system/icons/shopping-cart.png"
					class="icon">
					<p>Cart</p> <span>></span>
				</a> <a href="<%=contextPath + StringUtil.LOGIN_PAGE%>"
					class="submenu-link"> <img alt="#"
					src="${pageContext.request.contextPath}/resources/images/system/icons/login.png"
					class="icon">
					<p>Login</p> <span>></span>
				</a> <a href="#" class="submenu-link"> <img alt="#"
					src="${pageContext.request.contextPath}/resources/images/system/icons/logout.png"
					class="icon">
					<p>Logout</p> <span>></span>
				</a>
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