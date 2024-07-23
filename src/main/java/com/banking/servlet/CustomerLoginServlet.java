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

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account_no = request.getParameter("account-no");
		String pwd = request.getParameter("password");
		CustomerDAO c = new CustomerDAO();
		try {
			int cust_id = c.loginCustomer(account_no, pwd);
			if ( cust_id != -1) {
				if (c.checkNewCustomer(cust_id)) {
					response.sendRedirect("changePassword.jsp");
				}
				else {
					System.out.println("Login Success");
					HttpSession session = request.getSession();
					session.setAttribute("user-type", "Customer");
					session.setAttribute("user", ""+cust_id);
					request.getRequestDispatcher("CustomerDashboard.jsp").include(request, response);
				}
			}
			else {
				System.out.println("Login Failed");
				response.setContentType("text/html");
				response.getWriter().println("Customer Login Failed Miserably!");
				request.getRequestDispatcher("CustomerLogin.jsp").include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
