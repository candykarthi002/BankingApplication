<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Change Password</title>
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (request.getSession().getAttribute("user-type").equals("Customer"))
			response.sendRedirect("CustomerDashboard.jsp");
		if (request.getSession().getAttribute("user-type").equals("admin"))
			response.sendRedirect("AdminDashboard.jsp");
	}
	else {
		response.sendRedirect("index.jsp");
	}
	%>
	<form action="ChangeCustomerPasswordServlet" method="post">
		<input type="text" name="acc-no"
			placeholder="Enter your Account Number"> <input
			type="password" name="pwd" placeholder="Enter new password">
		<input type="submit" value="change password">
	</form>
</body>
</html>