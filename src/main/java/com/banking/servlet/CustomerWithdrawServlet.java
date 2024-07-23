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

/**
 * Servlet implementation class CustomerWithdrawServlet
 */
@WebServlet("/CustomerWithdrawServlet")
public class CustomerWithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		HttpSession session = request.getSession(false);
		int cust_id = Integer.parseInt((String) session.getAttribute("user"));
		CustomerDAO cust = new CustomerDAO();
		try {
			if (cust.withdrawAmount(cust_id, amount)) {
				System.out.println("Amount Withddrawn");
				response.setContentType("text/html");
				response.getWriter().println("Amount Withdrawn Successfully");
				request.getRequestDispatcher("CustomerDashboard.jsp").include(request, response);
			} else {
				System.out.println("Amount Not Withddrawn");
				response.setContentType("text/html");
				response.getWriter().println("Amount Withddrawl Failed");
				request.getRequestDispatcher("CustomerDashboard.jsp").include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
