<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Order</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/cart-list.css" />
</head>
<body>
	<!-- Include header -->
	<jsp:include page="header.jsp" />
	<section class="tha-cover-list">

		<div class="tha-container">

			<header class="tha-headerr">

				<h1 class="tha-text-xl">Your Orders</h1>

			</header>

			<div class="tha-main-content">

				<div class="tha-table-container">

					<table class="tha-tablee">

						<thead>

							<tr>
								<th>Order Id</th>

								<th>Image</th>

								<th>Product</th>

								<th>Price</th>

								<th>Discounted Price</th>

								<th>Amount Paid</th>
								
								<th>Status</th>


							</tr>

						</thead>

						<tbody>


							<c:forEach var="order" items="${Orderlistings}">

								<tr>
									<td>${order.orderId}</td>

									<td><img src="${order.productImg}" alt="Product Image"
										style="max-width: 100px;"></td>

									<td>${order.productName}</td>

									<td>${order.productPrice}</td>

									<td>${order.productDiscount}</td>

									<td>${order.productDiscount}</td>
									
									<td>${order.status}</td>


								</tr>



							</c:forEach>



						</tbody>



					</table>

				</div>

				<div class="tha-btoon_cart">

					<a href="${pageContext.request.contextPath}/ShopServlet"><button
							class="tha-button tha-button-primary">Continue Shopping</button></a>

				</div>

			</div>

		</div>

	</section>


	<!-- Include footer -->

	<jsp:include page="footer.jsp" />

	<!-- Footer ends -->

</body>
</html>