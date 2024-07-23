<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Customer Login Page</title>
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (request.getSession(false).getAttribute("user-type").equals("admin"))
			response.sendRedirect("index.jsp");
		if (request.getSession(false).getAttribute("user-type").equals("Customer"))
			response.sendRedirect("index.jsp");
	}
	%>
	<a href="index.jsp">Home</a>
	<h1>CUSTOMER LOGIN</h1>
	<form action="CustomerLoginServlet" method="post">
		<input type="text" name="account-no"
			placeholder="Enter your account number" /> <br /> <input
			type="password" name="password" placeholder="Enter your Password" />
		<br> <input type="submit" value="Login" />
	</form>
</body>
</html>