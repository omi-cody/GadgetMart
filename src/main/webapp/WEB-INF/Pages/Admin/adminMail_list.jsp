<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1" />
<title>GadgetMart Mail List</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/admin.css" />
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/popup.css">
<script src="${pageContext.request.contextPath}/Js/popup.js"></script>
</head>
<body>
	<input type="checkbox" id="menu-toggle" />

	<jsp:include page="admin_sidebar.jsp" />

	<div class="main-content">

		<jsp:include page="admin_header.jsp" />
		<%
		String msg = (String) session.getAttribute("errorMessage");
		if (msg != null) {
		%>
		<div id="errorMsg" class="errorMessage"><%=msg%></div>
		<%
		session.removeAttribute("errorMessage");
		}
		%>

		<main>
			<div class="page-header">
				<h1>GadgetMart Admin Panel</h1>
			</div>

			<div class="page-content">
				<div class="records table-responsive">
					<div class="record-header">
						<div>
							<h3>Customer Messages</h3>
						</div>
					</div>

					<div>
						<table width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th><span class="las la-sort"></span> Customer Name</th>
									<th><span class="las la-sort"></span> Message Title</th>
									<th><span class="las la-sort"></span> Created Date</th>
									<th><span class="las la-sort"></span> ACTIONS</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${Inquirylistings}">
									<tr>
										<td>${list.id}</td>
										<td>
											<div class="client">
												<img class="client-img bg-img" src="${list.customerImg}"></img>
												<div class="client-info">
													<h4>${list.customerName}</h4>
													<small>${list.customerEmail}</small>
												</div>
											</div>
										</td>
										<td>${list.customerMessage}</td>

										<td>${list.createdDate}</td>
										<td>
											<div class="actions">
												<a
													href="${pageContext.request.contextPath}/InquiryDetailServlet?InquiryId=${list.inquiryId}"
													class="dev-act"> <span class="las la-eye"></span>
												</a>
												<form
													action="${pageContext.request.contextPath}/DeleteInquiryByAdmin"
													method="post" id="deleteInquiry-${list.inquiryId}">

													<input type="hidden" name="InquiryId"
														value="${list.inquiryId}">

													<button type="submit" class="dev-act"
														onclick="confirmDelete('${list.inquiryId}')">

														<span class="las la-trash-alt"></span>

													</button>

												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
<script>
	function confirmDelete(inquiryId) {

		if (confirm("Are you sure you want to delete this Mail: "
				+ inquiryId

				+ "?")) {

			document.getElementById("deleteForm-" + inquiryId).submit();

		}

	}
</script>
</html>
