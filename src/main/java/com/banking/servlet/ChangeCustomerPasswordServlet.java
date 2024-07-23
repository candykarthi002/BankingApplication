package com.banking.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.dao.CustomerDAO;

@WebServlet("/ChangeCustomerPasswordServlet")
public class ChangeCustomerPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acc_no = request.getParameter("acc-no");
		String pwd = request.getParameter("pwd");
		CustomerDAO cust = new CustomerDAO();
		try {
			if (cust.changePassword(acc_no, pwd)) {
				int cust_id = cust.loginCustomer(acc_no, pwd);
				if (cust.changeNewCustomer(cust_id)) {
					HttpSession session = request.getSession();
					session.setAttribute("user-type", "customer");
					session.setAttribute("user", "" + cust_id);
					request.getRequestDispatcher("CustomerDashboard.jsp").include(request, response);
				}
				else {
					request.getRequestDispatcher("Error.jsp").include(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
