<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/shop.css" />
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
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
	<main>
		<form action="${pageContext.request.contextPath}/CategoryServlet"
			method="get" class="category-container">
			<div class="category">
				<button type="submit" name="brand" value="S">
					<img alt="samsung"
						src="${pageContext.request.contextPath}/resources/images/system/shop/Samsung.png">
				</button>
			</div>
			<div class="category">
				<button type="submit" name="brand" value="A">
					<img alt="Apple"
						src="${pageContext.request.contextPath}/resources/images/system/shop/Iphone.png">
				</button>
			</div>
			<div class="category">
				<button type="submit" name="brand" Value="N">
					<img alt="Nothing"
						src="${pageContext.request.contextPath}/resources/images/system/shop/Nothing.png">
				</button>
			</div>
			<div class="category">
				<button type="submit" name="brand" value="T">
					<img alt="Tablet"
						src="${pageContext.request.contextPath}/resources/images/system/shop/Tablet.png">
				</button>
			</div>

		</form>
		<div class="slider-container">
			<div class="wrapper">
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/SamAd.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/AppleAd.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/NthgAd.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/TabAd.png" />

			</div>
		</div>
		
		<div class="card-list">
			<c:set var="productCount" value="10" />
			<c:forEach var="product" items="${productList}" varStatus="status">
					<div class="card-item">
						<section class="image">
							<img alt="${product.productName}" src="${product.imageUrl}"><br>
						</section>
						<span class="dicount">${product.discount} % OFF</span>
						<h3>${product.productName}</h3>
						<section class="price-wrap">
							<form action="${pageContext.request.contextPath}/ProductDetailServlet">
								<input type="hidden" name="product_" value="${product.productID }">
								<button type="submit" class="arrow">Buy Now</button>
							</form>
							<h4
								style="margin-top: 50px; color: black; padding-left: 100px; font-size: 23px;">RS.${product.price}</h4>
						</section>
					</div>
			</c:forEach>
		</div>

	</main>
	<jsp:include page="footer.jsp"></jsp:include>


</body>

</html>