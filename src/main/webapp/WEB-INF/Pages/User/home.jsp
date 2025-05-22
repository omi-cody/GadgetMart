<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GadgetMart</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/home.css" />
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<!-- Hero Start -->
		<section class="hero">
			<div class="row_container">
				<div class="column">
					<h2>GadgetMart: Maximize Your Potential with Our Premium
						Mobile Phones and Tablets</h2>
					<p>"Welcome to GadgetMart: Your Mobile Growth Hub! Explore our
						curated free tools and extensions to boost your business. From
						productivity to marketing, GadgetMart has you covered. Elevate
						your mobile business today!"</p>
					<div class="buttons">
						<a href="${pageContext.request.contextPath}/ShopServlet"
							class="btn" id="shopbtn"> Shop Now </a> <a
							href="${pageContext.request.contextPath}/Pages/about.jsp"><button
								class="btn">Contact Us</button></a>
					</div>
				</div>
				<div class="column">
					<img
						src="${pageContext.request.contextPath}/resources/images/system/home/selfie1.png"
						alt="heroImg" class="hero_img" />
				</div>
			</div>
		</section>
		<!-- Hero End-->
		<!-- Top Product Start-->
		<section class="trend-product-section">
			<div class="trend-title">
				<h2>Top Products</h2>
			</div>
			<div class="card-list" style="margin-top: 50px;">
			<c:forEach var="product" items="${productList}" >
					<div class="card-item">
						<section class="image">
							<img alt="" src="${product.imageUrl}"><br>
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

	
		</section>
		<!-- Trending Product End-->

		<!-- Testimonies Start-->
		<section class="testi-section">
			<div class="testi-title">
				<h2>Testimonies</h2>
			</div>
			<div class="testi-list">
				<div class="testi-item">
					<div class="testi-profile">
						<img alt="Testimonis Profile"
							src="${pageContext.request.contextPath}/resources/images/system/home/hariom.jpg">
					</div>
					<div class="testi-detail">
						<div class="name">
							<h2>Hariom Sah</h2>
						</div>
						<div class="message">
							<p><i>"I ordered the Samsung Galaxy S23 from this site and was genuinely surprised by the quick delivery and excellent packaging. 
							The phone is 100% genuine and came with a valid warranty. I’ve been using it for a month now, and it works perfectly. 
							Highly recommended for anyone looking to buy original smartphones online!"</i></p>
						</div>
					</div>



				</div>
				<div class="testi-item">
					<div class="testi-profile">
						<img alt="Testimonis Profile"
							src="${pageContext.request.contextPath}/resources/images/system/home/gyan.jpg">
					</div>
					<div class="testi-detail">
						<div class="name">
							<h2>Gyan Prakash Patel</h2>
						</div>
						<div class="message">
							<p><i>“Great experience! I purchased an iPad for my online classes, and it arrived in just 3 days. 
							The product was sealed, brand new, and came with all accessories. Customer support also helped me track the order easily.
							 I just wish there was an option for free delivery, but overall I’m really happy with the purchase.”"</i>.</p>
						</div>
					</div>



				</div>
			</div>
		</section>
		<!-- Testimonies End-->

		<!-- Services Start-->
		<section class="service">
			<div class="service-title">
				<h2>Our Services</h2>
			</div>
			<div class="service-list">
				<div class="service-item">
					<section
						style="display: flex; align-items: center; justify-content: center">
						<img
							src="${pageContext.request.contextPath}/resources/images/system/home/delivary-van.png"
							alt="del-logo" width="33%" />
						<h3>SHIPPING SERVICES</h3>
					</section>
					<p>we offer efficient shipping services that prioritize speed,
						reliability, and cost-effectiveness. Our deliveries are fast and
						always on time, with free shipping provided for all orders. We
						operate around the clock to ensure convenience and meet the
						diverse needs of our customers.</p>
				</div>

				<div class="service-item">
					<section
						style="display: flex; align-items: center; justify-content: center">
						<img
							src="${pageContext.request.contextPath}/resources/images/system/home/return.png"
							alt="del-logo" width="33%" />
							<h3>RETURN POLICY</h3>
					</section>
					<p>Returning stuff is easy with us. If you need to send
						something back, just do it within the time limit and make sure
						it's still in good shape. You can get your money back, exchange
						it, or get store credit. And if you have any questions, our
						customer service team is ready to help.</p>
				</div>

				<div class="service-item ">
					<section
						style="display: flex; align-items: center; justify-content: center">
						<img
							src="${pageContext.request.contextPath}/resources/images/system/home/customer.png"
							alt="del-logo" width="30%" />
						<h3>CUSTOMER SUPPORT</h3>
					</section>
					<p>For customer support, you can easily reach out to us by
						leaving a message on our social media platforms or by using the
						contact form on our website's "Contact Us" page. We'll get back to
						you promptly to assist with any questions or concerns you may
						have.</p>
				</div>
			</div>
		</section>
		<!-- Services Start-->


	</main>


	<jsp:include page="footer.jsp"></jsp:include>

	<p></p>
</body>
</html>