<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (request.getSession(false).getAttribute("user-type").equals("admin")) {
			response.sendRedirect("AdminDashboard.jsp");
		}
		if (request.getSession(false).getAttribute("user-type").equals("Customer")) {
			response.sendRedirect("CustomerDashboard.jsp");
		}
	}
	%>
	<div class="main-container">
		<h1>Banking Application</h1>
		<div class="inner">
			<div class="left">
				<img alt="cover" src="assets/cover-img.png">
			</div>
			<div class="right">
				<p>Welcome to Online Banking App! Enjoy secure, easy access to
					your accounts anytime, anywhere. Manage your finances efficiently
					with seamless transactions, personalized support, and a
					user-friendly interface. Admins can log in to oversee operations,
					while customers can access their personal banking dashboard. Thank
					you for choosing us - your financial success starts here!</p>
				<div class="btns">
					<a href="AdminLogin.jsp">Admin Login</a> <a
						href="CustomerLogin.jsp">Customer Login</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>