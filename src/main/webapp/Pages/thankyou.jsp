<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='${pageContext.request.contextPath}/Css/thankyou.css'
	rel='stylesheet'>
</head>
<body>
	<!-- Navigation Bar Start -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- Navigation Bar End -->

	<div class="pop-body">
		<div class="pop-main-container">
			<div class="pop-icon-check_circle">
				<svg viewBox="0 0 24 24" width="100" height="100">
                    <path fill="currentColor"
						d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1.41 14.59l-4.24-4.24L7.41 10l3.18 3.18L15.59 7l1.41 1.41-5.18 5.18z"></path>
                </svg>
			</div>
			<h2 class="pop-h2">Thank you!</h2>
			<p class="pop-lead">Your order was successfully completed.</p>
			<p class="pop-lead">We will contact you through phone or SMS
				soon.</p>
			<a href="#"
				class="pop-btn-back">Back to shop</a>
		</div>
	</div>
	<!-- Footer Bar Start -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer Bar End -->
</body>
</html>