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

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acc_no = request.getParameter("acc-no");
		String fname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Long mobileNo = Long.parseLong(request.getParameter("mobile-no"));
		String accType = request.getParameter("acc-type");
		String idProof = request.getParameter("id-proof");
		String dob = request.getParameter("dob");
		Customer cust = new Customer();
		cust.setFullname(fname);
		cust.setAddress(address);
		cust.setEmail(email);
		cust.setMobileNumber(mobileNo);
		cust.setAccountType(accType);
		cust.setIdProof(idProof);
		cust.setDob(dob);
		cust.setAccountNumber(acc_no);

		AdminDAO admin = new AdminDAO();
		try {
			if (admin.editCustomer(cust)) {
				response.setContentType("text/html");
				response.getWriter().println("Customer Updated Successfully");
				request.getRequestDispatcher("AdminDashboard.jsp").include(request, response);
			} else {
				response.setContentType("text/html");
				response.getWriter().println("Customer Updated Failed");
				request.getRequestDispatcher("AdminDashboard.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
