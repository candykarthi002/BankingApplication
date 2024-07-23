<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Customer Creation Page</title>
</head>
<style>
form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-content: center;
	width: 300px;
	gap: 1rem;
}
</style>
<body>
	<%
	if (request.getSession(false).getAttribute("user-type") != null) {
		if (!request.getSession().getAttribute("user-type").equals("admin"))
			response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("index.jsp");
	}
	%>
	<h1>Banking Application</h1>
	<a href="AdminDashboard.jsp">Home</a>
	<h2>Customer Creation</h2>
	<form action="CreateCustomerServlet" method="post">
		<input type="text" name="acc-no"
			placeholder="Enter the Account Number"> <input type="text"
			name="fullname" placeholder="Enter the Full Name"> <input
			type="password" name="password" placeholder="Enter the Password">
		<input type="text" name="address" placeholder="Enter the Address">
		<input type="tel" pattern="[0-9]{10}" name="mobile-no"
			placeholder="Enter the Mobile Number" required> <input
			type="email" name="email" placeholder="Enter the email"> <select
			name="acc-type">
			<option value="savings">Savings</option>
			<option value="current">Current</option>
		</select> <input type="date" name="dob" placeholder="Enter the Date Of Birth">
		<input type="number" name="bal" min=1000 value=1000
			placeholder="Enter the Initial Balance"> <input type="text"
			name="id-proof" placeholder="Enter the Aadhar Number"> <input
			type="submit" value="Create Customer">
	</form>
</body>
</html>