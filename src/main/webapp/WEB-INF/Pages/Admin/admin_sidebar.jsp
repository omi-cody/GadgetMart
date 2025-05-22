<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.awt.desktop.UserSessionEvent"%>
<%@page import="com.gadgetmart.util.StringUtil"%>
<%
//get the session and request object
HttpSession adminSession = request.getSession();
String currentUser = (String) adminSession.getAttribute(StringUtil.SESSION_DATA);
String imgUrl = (String) adminSession.getAttribute(StringUtil.SESSION_IMG);
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/admin.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/all.min.css" />
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
	<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
</head>
<body>
	<div class="sidebar">
		<div class="side-header">
			<img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/system/GadgetMartLogo.png"
				class="logo">
		</div>


		<div class="side-content">
			<div class="profile">
				<div class="profile-img">
					<img alt="Admin Profile"
						src="<%=imgUrl%>"onerror="this.src='${pageContext.request.contextPath}/resources/images/system/icons/admin.png';">
				</div>
				<h4>Om Shankar Sah</h4>
			</div>
			<div class="side-menu">
				<ul>
				<li><a
						href="${pageContext.request.contextPath}/DashboardServlet" >
							<span class="bx bxs-dashboard"></span> <small>Dashboard</small>
					</a></li>

					<li><a
						href="${pageContext.request.contextPath}/FetchProductForAdmin">
							<span class="las la-mobile"></span> <small> Products</small>
					</a></li>

					<li><a
						href="${pageContext.request.contextPath}/OrderlistServlet">
							<span class="las la-shopping-cart"></span> <small>Orders</small>
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/InquiryListServlet">
							<span class="lar la-envelope"></span> <small>Mails</small>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>