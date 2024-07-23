<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.banking.dao.AdminDAO,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Admin Dashboard</title>
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (!request.getSession(false).getAttribute("user-type").equals("admin"))
			response.sendRedirect("index.jsp");
	}
	else {
		response.sendRedirect("index.jsp");
	}
	%>
	<h1>Banking Application</h1>
	<a href="createCustomer.jsp">Create Customer</a>
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
	<div>
		<h2>Users</h2>
		<table border="1">
		<tr>
		<th>Account Number</th>
		<th>Full Name</th>
		<th>Address</th>
		<th>Mobile Number</th>
		<th>Email</th>
		<th>Account Type</th>
		<th>Date Of Birth</th>
		<th>Id Proof</th>
		<th>Action</th>
		</tr>
			<%
			AdminDAO admin = new AdminDAO();
			ResultSet rs = admin.getUsers();
			while (rs.next()) {
			%>
			<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(5)%></td>
				<td><%=rs.getString(6)%></td>
				<td><%=rs.getString(7)%></td>
				<td><%=rs.getString(8)%></td>
				<td><a href="editCustomer.jsp?acc_no=<%=rs.getString(1)%>">Edit</a></td>
				<td><a href="DeleteCustomerServlet?acc_no=<%=rs.getString(1) %>">Delete</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>