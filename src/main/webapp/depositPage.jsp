<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application - Deposit Page</title>
</head>
<body>
<form action="CustomerDepositServlet" method="post">
<input type="number" placeholder="Enter the amount" name="amount" />
<input type="submit" value="Deposit" >
</form>
</body>
</html>