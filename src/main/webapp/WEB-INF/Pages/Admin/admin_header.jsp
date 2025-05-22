<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Css/admin.css" />
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
</head>
<body>
	<header>
		<div class="header-content">
			<label for="menu-toggle"> <span class="las la-bars"></span>
			</label>
			<div class="header-menu">
				<div class="user">
					<form action="${pageContext.request.contextPath}/LogoutServlet"
						method="post">
						<button type="submit" class="logout-btnm">Logout</button>
					</form>
				</div>
			</div>
		</div>
	</header>
</body>
</html>