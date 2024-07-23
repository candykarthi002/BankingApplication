package com.banking.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDAO {
	static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingApp", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

	public int loginCustomer(String account_no, String pwd) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "SELECT id FROM Customer WHERE account_number=? and password=?;";
		PreparedStatement ps = c.prepareStatement(q);
		ps.setString(1, account_no);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("id");
		} else {
			return -1;
		}
	}

	public boolean checkNewCustomer(int cust_id) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "select c.id from (Select Customer.id as id from Customer JOIN NewCustomer on Customer.id = NewCustomer.id) as c where id=?;";
		PreparedStatement ps = c.prepareStatement(q);
		ps.setInt(1, cust_id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean changePassword(String acc_no, String pwd) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "UPDATE Customer SET password=? WHERE account_number=?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, pwd);
		ps.setString(2, acc_no);
		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean changeNewCustomer(int id) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "DELETE FROM NewCustomer WHERE id=?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, id);
		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean createTransaction(int cust_id, int amount, int balance, String transactionType) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "INSERT INTO Transaction (customer_id, transaction_type, amount, balance, transaction_date) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = c.prepareStatement(q);
		ps.setInt(1, cust_id);
		ps.setString(2, transactionType);
		ps.setInt(3, amount);
		ps.setInt(4, balance);
		ps.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean depositAmount(int cust_id, int amount) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "UPDATE Customer SET balance=balance+? WHERE id=?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, amount);
		ps.setInt(2, cust_id);
		int result = ps.executeUpdate();
		if (result > 0) {
			String q = "SELECT balance FROM Customer WHERE id=?;";
			PreparedStatement ps1 = c.prepareStatement(q);
			ps1.setInt(1, cust_id);
			ResultSet data = ps1.executeQuery();
			while (data.next()) {
				if (this.createTransaction(cust_id, amount, data.getInt("balance"), "Deposit")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	public boolean withdrawAmount(int cust_id, int amount) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String query = "UPDATE Customer SET balance=balance-? WHERE id=?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, amount);
		ps.setInt(2, cust_id);
		int result = ps.executeUpdate();
		if (result > 0) {
			String q = "SELECT balance FROM Customer WHERE id=?;";
			PreparedStatement ps1 = c.prepareStatement(q);
			ps1.setInt(1, cust_id);
			ResultSet data = ps1.executeQuery();
			while (data.next()) {
				if (this.createTransaction(cust_id, amount, data.getInt("balance"), "Withdraw")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	public ResultSet getTransactions(int cust_id) throws SQLException {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String q = "SELECT * FROM Transaction WHERE customer_id=? order by transaction_date desc LIMIT 10;";
		PreparedStatement st = c.prepareStatement(q);
		st.setInt(1, cust_id);
		ResultSet rs = st.executeQuery();
		return rs;
	}
}
