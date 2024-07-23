<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.banking.dao.AdminDAO,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Edit Customer Page</title>
</head>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (!request.getSession(false).getAttribute("user-type").equals("admin"))
			response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("index.jsp");
	}
	%>
	<%
	AdminDAO admin = new AdminDAO();
	String acc_no = request.getParameter("acc_no");
	ResultSet rs = admin.getUser(acc_no);
	while (rs.next()) {
	%>
	<h1>Banking Application</h1>
	<a href="AdminDashboard.jsp">Home</a>
	<h2>Edit Customer</h2>
	<form action="EditCustomerServlet" method="post">
		<input type="text" name="acc-no"
			placeholder="Enter the Account Number" value="<%=acc_no%>" readonly />
		<input type="text" name="fullname" placeholder="Enter the Full Name"
			value="<%=rs.getString(2)%>"> <input type="text"
			name="address" placeholder="Enter the Address"
			value="<%=rs.getString(3)%>"> <input type="tel"
			pattern="[0-9]{10}" name="mobile-no"
			placeholder="Enter the Mobile Number" value="<%=rs.getString(4)%>"
			required> <input type="email" name="email"
			placeholder="Enter the email" value="<%=rs.getString(5)%>"> <select
			name="acc-type">
			<%
			if (rs.getString(6).equals("Savings")) {
			%>
			<option value="savings" selected>Savings</option>
			<option value="current">Current</option>
			<%
			} else {
			%>
			<option value="savings">Savings</option>
			<option value="current" selected>Current</option>
			<%
			}
			%>
		</select> <input type="date" name="dob" placeholder="Enter the Date Of Birth"
			value="<%=rs.getString(7)%>"> <input type="text"
			name="id-proof" placeholder="Enter the Aadhar Number"
			value="<%=rs.getString(8)%>"> <input type="submit"
			value="Update Customer">
	</form>
	<%
	}
	%>
</body>
</html>