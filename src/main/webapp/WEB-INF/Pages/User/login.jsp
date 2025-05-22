<%@page import="com.gadgetmart.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
HttpSession userSession = request.getSession();
%>
<%
String username = (String) userSession.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GadgetMart</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/Css/login.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/popup.css">
<script src="${pageContext.request.contextPath}/Js/popup.js"></script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${not empty requestScope.ERROR_MESSAGE}">
		<div id="errorMsg" class="errorMessage">
			<c:out value="${requestScope.ERROR_MESSAGE}" />
		</div>
	</c:if>
	
	<div class="body-container">
		<div class="container">
			<div class="logo-box">
				<img alt=""
					src="${pageContext.request.contextPath}/resources/images/system/GadgetMart.gif">
			</div>

			<div class="login-box">
				<h1>Login</h1>
				<form action="<%=contextPath%>/login" method="post">
					<div class="input-box">
						<input type="text" name="login_username" id="username"
							placeholder="Username"> <i class='bx bxs-user'></i>
					</div>
					<div class="input-box">
						<input type="password" name="login_password" id="password"
							placeholder="Password"> <i class='bx bxs-lock-alt'></i>
					</div>
					<button class="lgn-btn" type="submit">Login</button>
					<div class="forget">
						<a href="${pageContext.request.contextPath}/Pages/User/reset_password.jsp">Forgot Password?</a>
					</div>
					<div class="register-link">
						<p>
							Don't have an account ? <a
								href="${pageContext.request.contextPath}/register">Register</a>
						</p>
					</div>

				</form>
			</div>
		</div>
	</div>
	<c:out value="${valError}" />
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>