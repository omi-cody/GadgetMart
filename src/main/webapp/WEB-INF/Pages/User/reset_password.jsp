<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/resetPassword.css">
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartLogo.png">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/popup.css">
<script src="${pageContext.request.contextPath}/Js/popup.js"></script>
</head>
<body>

	<c:if test="${not empty requestScope.valError}">
		<div id="errorMsg" class="errorMessage">
			<c:out value="${requestScope.valError}" />
		</div>
	</c:if>
	<!-- nav ends -->
	<div class="body-container">
		<div class="login-box">
			<h1>Reset Password</h1>
			<form action="${pageContext.request.contextPath}/Pages/User/login.jsp" method="post">
				<div class="input-box">
					<input type="text" name="login_username" id="username"
						placeholder="Username"> <i class='bx bxs-user'></i>
				</div>
				<div class="input-box">
					<input type="password" name="newpass" id="password"
						placeholder="New Password"> <i class='bx bxs-lock-alt'></i>
				</div>
				<div class="input-box">
					<input type="password" name="confpass" id="password"
						placeholder="Condirm Password"> <i class='bx bxs-lock-alt'></i>
				</div>
				<button class="lgn-btn" type="submit">Reset Password</button>
			</form>
		</div>

	</div>



	<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>