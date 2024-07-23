package com.banking.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.dao.AdminDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password"); 
		AdminDAO admin = new AdminDAO();
		try {
			if (admin.verifyAdminUser(name, pwd)) {
				System.out.println("Login Success");
				HttpSession session = request.getSession();
				session.setAttribute("user-type", "admin");
				session.setAttribute("user", name);
				request.getRequestDispatcher("AdminDashboard.jsp").include(request, response);
			}
			else {
				System.out.println("Login Failed");
				response.setContentType("text/html");
				response.getWriter().println("Login Failed Miserably!");
				request.getRequestDispatcher("AdminLogin.jsp").include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
