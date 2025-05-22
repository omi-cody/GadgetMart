
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8" />

<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1" />

<title>Order-List</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/admin.css" />

<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
</head>

<body>
	<input type="checkbox" id="menu-toggle" />

	<jsp:include page="admin_sidebar.jsp" />

	<div class="main-content">
		<jsp:include page="admin_header.jsp" />

		<main>
			<div class="page-header">
				<h1>GadgetMart Admin Panel</h1>
			</div>

			<div class="page-content">
				<div class="records table-responsive">
					<div class="record-header">
						<div>
							<h3>Customer Orders</h3>
						</div>
					</div>

					<div>
						<table width="100%">
							<thead>
								<tr>


									<th><span class="las la-sort"></span> Customer Name</th>

									<th><span class="las la-sort"></span> Product Id</th>

									<th><span class="las la-sort"></span> Product Name</th>

									<th><span class="las la-sort"></span> Amount</th>

									<th><span class="las la-sort"></span> Status</th>

									<th><span class="las la-sort"></span> ACTIONS</th>


								</tr>
							</thead>

							<tbody>

								<c:forEach var="list" items="${listings}">
									<tr>


										<td>
											<div class="client">
												<div class="client-img bg-img">
													<img alt="Profile" src="${list.imageLink}">
												</div>

												<div class="client-info">
													<h4>${list.customerName}</h4>

													<small>@${list.id}</small>
												</div>
											</div>
										</td>

										<td>${list.productId}</td>

										<td>${list.productName}</td>

										<td>${list.amount}</td>

										<td>${list.status}</td>
										<td>
											<form action="${pageContext.request.contextPath}/UpdateOrder" class="or-status">
											<select name="status">
											<option value="Pending">Pending</option>
											<option value="Canceled">Canceled</option>
											<option value="Delivered">Delivered</option>
											</select>
											<input type="hidden" value="${list.orderId}" name="oId" >
											<button><span class="las la-edit"></span></button>
											</form>
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
</html>
