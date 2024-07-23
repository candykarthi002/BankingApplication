<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application</title>
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
	<h1>Banking Application</h1>
	<a href="AdminLogin.jsp">Admin Login</a>
	<a href="CustomerLogin.jsp">Customer Login</a>
</body>
</html>