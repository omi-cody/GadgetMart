<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Message View</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/admin.css" />
</head>
<body>
<c:forEach var="inquiry" items="${MatchingInquiry}">
	<section class="assmi-bodyiie">
		<div class="assmi-message-container">
			<div class="assmi-profile">
				<img src="${inquiry.customerImg }" alt="Profile Picture">
			</div>
			<div class="assmi-user-details">
				<h2 class="assmi-username">${inquiry.customerName }</h2>
				<ul class="assmi-user-info">
					<li><strong>Email: </strong> ${inquiry.customerEmail }</li>
					<li><strong>Contact: </strong>${inquiry.customerPhoneNumber }</li>
					<li><strong>Address: </strong> ${inquiry.customerAddress}</li>
					<li><strong>Created Date: </strong>${inquiry.createdDate}</li>
				</ul>
			</div>
			<div class="assmi-message-details">
				<h3 class="assmi-message-title">
					<span>Subject: </span>${inquiry.customerSubject}
				</h3>
				<hr>
				<p class="assmi-message-content">${inquiry.customerMessage}</p>
			</div>
			<a href="${pageContext.request.contextPath}/InquiryListServlet" class="assmi-back-link"><button
					class="assmi-back-button">Back</button></a>
		</div>
	</section>
	</c:forEach>

</body>
</html>
