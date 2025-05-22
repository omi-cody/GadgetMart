<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/productDetail.css" />
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>

<body>
	<!-- nav start -->
	<jsp:include page="header.jsp" />
	<!-- nav end -->

	<!-- product card start -->
	<c:forEach var="product" items="${MatchingProducts}">
		<section class="outbind">
			<div class="rish-product-details">
				<div class="rish-grid-container">
					<div class="rish-product-image">
						<img alt="Product Image" src="${product.imageUrl}">
					</div>
					<div class="rish-product-info">
						<h1>${product.productName}</h1>
						<p class="rish-price"><ins style="color: Green; text-decoration: none">Rs.${product.discountAmount}</ins><br><del>RS.${product.price}</del></p>
						<div class="rish-features">
							<ul>
								<li><strong>Screensize: </strong> ${product.screenSize}</li>
								<li><strong>RAM: </strong> ${product.ram}</li>
								<li><strong>Storage: </strong> ${product.storage}</li>
								<li><strong>Processor: </strong> ${product.processor}</li>
								<li><strong>CAMERA: </strong> ${product.camera}</li>
								<li><strong>Discount: </strong> ${product.discount}%</li>
								<li><strong>Warranty: </strong> ${product.warranty}</li>
							</ul>

						</div>
						<p class="rish-feat-desc">
							<strong>Features:<br></strong> ${product.features}
						</p>

						<form action="${pageContext.request.contextPath}/CartServlet"
							method="post">
							<input type="hidden" name="product_id_cart"
								value="${product.productID}">
							<div class="tha-quantity-control">
								<button class="tha-button tha-button-secondary minus"
									type="button">-</button>
								<input class="tha-inputt" type="text" value="1" min='1' name="quant">
								<button class="tha-button tha-button-secondary plus"
									type="button">+</button>
							</div>
							<button type="submit" class="rish-add-to-cart" id="counter_id">Add
								to Cart</button>
						</form>

					</div>
				</div>
			</div>
		</section>


	</c:forEach>


	<!-- product card end -->



	<!-- footer start -->
	<jsp:include page="footer.jsp" />
	<!-- footer ends -->

	<script src="${pageContext.request.contextPath}/js/home.js"></script>




	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const quantityControls = document
					.querySelectorAll('.tha-quantity-control');
			quantityControls.forEach(function(control) {
				const input = control.querySelector('.tha-inputt');
				const plusButton = control.querySelector('.plus');
				const minusButton = control.querySelector('.minus');
				plusButton.addEventListener('click', function() {
					input.value = parseInt(input.value) + 1;
				});
				minusButton.addEventListener('click', function() {
					let value = parseInt(input.value);
					if (value > 1) {
						input.value = value - 1;
					}
				});
			});
		});
	</script>
</body>
</html>