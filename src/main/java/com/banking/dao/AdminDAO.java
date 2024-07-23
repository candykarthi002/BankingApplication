package com.banking.dao;

import java.sql.*;
import com.banking.model.Customer;

public class AdminDAO {
	Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingapp", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

	public boolean verifyAdminUser(String username, String pwd) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "SELECT * FROM Admin WHERE username=? and password=?;";
		PreparedStatement ps = c.prepareStatement(q);
		ps.setString(1, username);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean createCustomer(Customer c) throws SQLException {
		Connection c1 = null;
		try {
			c1 = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String insertQuery = "INSERT INTO Customer (account_number, password, fullname, address, mobile_number, email, account_type, balance, date_of_birth, id_proof) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = c1.prepareStatement(insertQuery);
		ps.setString(1, c.getAccountNumber());
		ps.setString(2, c.getPassword());
		ps.setString(3, c.getFullname());
		ps.setString(4, c.getAddress());
		ps.setLong(5, c.getMobileNumber());
		ps.setString(6, c.getEmail());
		ps.setString(7, c.getAccountType());
		ps.setInt(8, c.getBalance());
		ps.setString(9, c.getDob());
		ps.setString(10, c.getIdProof());
		int result = ps.executeUpdate();
		if (result > 0) {
			String idQuery = "SELECT id FROM Customer WHERE account_number=?";
			PreparedStatement s = c1.prepareStatement(idQuery);
			s.setString(1, c.getAccountNumber());
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				insertQuery = "INSERT INTO NewCustomer VALUES (?);";
				s = c1.prepareStatement(insertQuery);
				s.setInt(1, rs.getInt("id"));
				result = s.executeUpdate();
			}
			return (result > 0) ? true : false;
		} else {
			return false;
		}
	}
	public ResultSet getUsers() throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "SELECT account_number, fullname, address, mobile_number, email, account_type, date_of_birth, id_proof FROM Customer";
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(q);
		return rs;
	}
	public ResultSet getUser(String acc_no) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "SELECT account_number, fullname, address, mobile_number, email, account_type, date_of_birth, id_proof FROM Customer where account_number=?";
		PreparedStatement st = c.prepareStatement(q);
		st.setString(1, acc_no);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public boolean deleteUser(String acc_no) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "DELETE FROM Customer WHERE account_number=?";
		PreparedStatement st = c.prepareStatement(q);
		st.setString(1, acc_no);
		int result = st.executeUpdate();
		return (result > 0) ? true : false;
	}
	public boolean editCustomer(Customer c) throws SQLException {
		Connection c1 = null;
		try {
			c1 = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String insertQuery = "UPDATE Customer SET fullname=?, address=?, mobile_number=?, email=?, account_type=?, date_of_birth=?, id_proof=? where account_number=?;";
		PreparedStatement ps = c1.prepareStatement(insertQuery);
		ps.setString(1, c.getFullname());
		ps.setString(2, c.getAddress());
		ps.setLong(3, c.getMobileNumber());
		ps.setString(4, c.getEmail());
		ps.setString(5, c.getAccountType());
		ps.setString(6, c.getDob());
		ps.setString(7, c.getIdProof());
		ps.setString(8, c.getAccountNumber());
		int result = ps.executeUpdate();
		return (result > 0) ? true : false;
	}
}
