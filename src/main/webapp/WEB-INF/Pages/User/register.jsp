<%@page import="jakarta.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String contextPath = request.getContextPath();
%>
<%@page import="com.gadgetmart.util.StringUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GadgetMart</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link href='${pageContext.request.contextPath}/Css/register.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/popup.css">
<script src="${pageContext.request.contextPath}/Js/popup.js"></script>
</head>
<body>
	<!-- Navigation Bar Start -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- Navigation Bar End -->
	<c:if test="${not empty requestScope.valError}">
		<div id="errorMsg" class="errorMessage">
			<c:out value="${requestScope.valError}" />
		</div>
	</c:if>
	<!-- Registration Form Start -->
	<div class="body-container">
		<div class="register-container">
			<div class="register-box">
				<h1>Register</h1>
				<form action="${pageContext.request.contextPath}/register" method="post">
					<div class="input-box">
						<div class="input-field">
							<input type="text" name="fullName" id="fullName"
								placeholder="Full Name"> <i class='bx bxs-user-detail'></i>
						</div>
						<div class="input-field">
							<input type="text" name="username" id="username"
								placeholder="Username"> <i class='bx bxs-user'></i>
						</div>
					</div>
					<div class="input-box">
						<div class="input-field">
							<input type="email" name="email" id="email" placeholder="Email">
							<i class='bx bxs-envelope'></i>
						</div>
						<div class="input-field">
							<input type="number" name="phoneNumber" id="phoneNumber"
								placeholder="Phone Number"> <i class='bx bxs-phone'></i>
						</div>
					</div>
					<div class="input-box">
						<div class="input-field">
							<input type="password" name="password" id="password"
								placeholder="Password"> <i class='bx bxs-lock-alt'></i>
						</div>
						<div class="input-field">
							<input type="password" name="confirmPassword"
								id="confirmPassword" placeholder="Confirm Password"> <i
								class='bx bxs-lock'></i>
						</div>
					</div>
					<div class="input-field">
						<label>Gender:</label> <span> <input type="radio"
							name="gender" id="male" checked="checked" value="Male">Male
						</span> <span> <input type="radio" name="gender" id="female"
							value="Female">Female
						</span>
					</div>
					<div class="reg-btn">
						<input type="submit" value="Register">
					</div>
					<div class="login-link">
						<p>
							Already have an account ? <a
								href="${pageContext.request.contextPath}/login">Login</a>
						</p>
					</div>

				</form>
			</div>
			<div class="logo-box">
				<img alt=""
					src="${pageContext.request.contextPath}/resources/images/system/GadgetMart.gif">
			</div>
		</div>
	</div>
	<!-- Registration Form End -->

	<!-- Footer  Start -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer  End -->

</body>

</html>