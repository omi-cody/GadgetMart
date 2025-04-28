<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/shop.css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<section class="category-section">
			<div class="category_container">
				<div class="category-list">
					<div class="category-item">
						<button " ></button>
					</div>
				</div>
		</section>
		<div class="slider-container">
			<div class="wrapper">
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/1.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/2.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/3.png" />
				<img
					src="${pageContext.request.contextPath}/resources/images/system/slider/4.png" />
			</div>
		</div>

		<div class="container">
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>
			<div class="box"></div>


		</div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>