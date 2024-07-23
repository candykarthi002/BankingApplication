<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Admin Login Page</title>
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (request.getSession().getAttribute("user-type").equals("admin"))
			response.sendRedirect("AdminDashboard.jsp");
		if (request.getSession().getAttribute("user-type").equals("Customer"))
			response.sendRedirect("index.jsp");
	}
	%>
	<a href="index.jsp">Home</a>
	<h1>ADMIN LOGIN</h1>
	<form action="AdminLoginServlet" method="post">
		<input type="text" name="username" placeholder="Enter your Username" />
		<br /> <input type="password" name="password"
			placeholder="Enter your Password" /> <br> <input type="submit"
			value="Login" />
	</form>
</body>
</html>