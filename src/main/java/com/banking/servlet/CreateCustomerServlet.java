package com.banking.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.dao.AdminDAO;
import com.banking.model.Customer;

@WebServlet("/CreateCustomerServlet")
public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fullname");
		String accNo = request.getParameter("acc-no");
		String pwd = request.getParameter("password");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Long mobileNo = Long.parseLong(request.getParameter("mobile-no"));
		String accType = request.getParameter("acc-type");
		int balance = Integer.parseInt(request.getParameter("bal"));
		String idProof = request.getParameter("id-proof");
		String dob = request.getParameter("dob");
		Customer cust = new Customer();
		cust.setAccountNumber(accNo);
		cust.setFullname(fname);
		cust.setAddress(address);
		cust.setPassword(pwd);
		cust.setEmail(email);
		cust.setMobileNumber(mobileNo);
		cust.setAccountType(accType);
		cust.setBalance(balance);
		cust.setIdProof(idProof);
		cust.setDob(dob);
		AdminDAO admin = new AdminDAO();
		try {
			if (admin.createCustomer(cust)) {
				request.getRequestDispatcher("AdminDashboard.jsp").include(request, response);
			}
			else {
				request.getRequestDispatcher("Error.jsp").include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
