<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/about.css" />
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<!-- section for portfolio of members -->
		<section class="about-container">
			<p style="font-size: 40px; font-weight: bold; margin-bottom: 10px;">
				Meet The Team Members</p>
			<c:forEach var="member" items="${membersList}">
				<div class="about" id="about">
					<!-- image of individual member -->
					<div class="about_image">
						<img
							src="${member.memberImage}"
							alt="">
					</div>
					<!-- detail of individual member -->
					<div class="about-content">
						<h1 class="fname">
							${member.memberFName}<span id="backname">${member.memberLName}</span>
						</h1>
						<h3>${member.memberType}</h3>
						<p style="font-size: 27px;">${member.memberIntro}
						<p align="center"
							style="text-align: justify; line-height: 1.5; padding: 5px;">
							${member.memberInfo}
							</p>
						<!-- social media link -->
						<div class="social-media">
							<a href="https://www.facebook.com/" target="_blank"><img
								src="${pageContext.request.contextPath}/resources/images/system/icons/facebook.png"></a>
							<a href="https://www.instagram.com/" target="_blank"><img
								src="${pageContext.request.contextPath}/resources/images/system/icons/instagram.png"></a>
							<a href="https://linkedin/" target="_blank"><img
								src="${pageContext.request.contextPath}/resources/images/system/icons/linkedin.png"></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</section>
		<!-- section for Inquiry form -->
		<div class="body-container">
			<div class="inquiry-box">
				<h1>Feedback Form</h1>
				<form action="${pageContext.request.contextPath}/InquiryServlet"
					method="post">
					<div class="input-box">
						<div class="input-field">
							<input type="text" name="subject" id="subject"
								placeholder="Subject">
						</div>
						<div class="input-field">
							<textarea name="message" id="message" rows="10"
								placeholder="Enter Your Message"></textarea>
						</div>
					</div>

					<button type="submit" class="inq-btn">Submit</button>

				</form>
			</div>
		</div>

	</main>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>