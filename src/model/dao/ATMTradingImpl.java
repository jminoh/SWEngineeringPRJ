package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Account;
import model.util.DBUtil;

public class ATMTradingImpl implements ATMTrading {

	@Override
	public int checkAccount(String accountNumber) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // ��ü �ݱ�
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}

	@Override
	public int checkCard(String accountNumber) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // ��ü �ݱ�
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}

	@Override
	public Account getAccount(String accountNumber) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		Account account = new Account(); // �Ա� ����
		String sql;
		try {
			con = DBUtil.getConnection();
			sql = "select * from account where account_number = ?"; // ������ ī��/���忡 ���� ���ڵ带 ��ȯ�ϴ� sql��
			pstmt = con.prepareStatement(sql); // pstmt ��ü�� sql ������ �Ľ�
			pstmt.setString(1, accountNumber); // sql ������ ù��° ? ���� accountNumber ���� ���ε�
			rs = pstmt.executeQuery(); // select ���忡 ���� ��ȯ�Ǵ� ���ڵ� ���� rs ��ü�� ����
		
			while(rs.next()) {
				account.setAccountNumber(rs.getString("account_number")); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
				account.setBalance(rs.getInt("balance")); // ������ Ÿ���� ������ �ܾ��� ������ �Աݰ����� �ܾ����� ����
				System.out.println("balance : " + account.getBalance());
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // ��ü �ݱ�
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return account;
	}

	@Override
	public Account deposit(Account account, int amount) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü
		int balance = 0; // �ܾ�
		
		try {
			con = DBUtil.getConnection();
			String sql = "UPDATE account set balance = ? where account_number = ?"; // ���¿� �����ִ� �ܾ��� �ٲٱ� ���� sql��
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount + account.getBalance());
			pstmt.setString(2, account.getAccountNumber());
			
			int count = pstmt.executeUpdate(); // update ���� �������� �ݿ��� ���ڵ��� �Ǽ��� ��ȯ
			if(count > 0) {
				balance = account.getBalance() + amount;
				account.setBalance(balance);
				account.setTradingResult(200); // �����ڵ� 200 : ���� ó��
			}else {
				account.setTradingResult(600); // �����ڵ� 600 : ó�� ����
			}

		}catch(SQLException se) {
			se.printStackTrace();
			account.setTradingResult(600);
		}finally { // ��ü �ݱ�
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
				account.setTradingResult(600);
			}
		}
		return account;
	}

	@Override
	public Account withdraw(Account account, int amount) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü
		int balance = 0; // �ܾ�
		
		if(account.getBalance() - amount > 0) { // ���� �ܾ��� ����Ϸ��� �׼����� ������ ����
			try {
				con = DBUtil.getConnection();
				String sql = "UPDATE account set balance = ? where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, account.getBalance() - amount);
				pstmt.setString(2, account.getAccountNumber());
		
				int count = pstmt.executeUpdate(); // count�� update�� ���� ��
				if(count > 0) { // update�� �����ߴٸ�
					System.out.println("withdraw success");
					System.out.println("balance : " + (account.getBalance() - amount));
					balance = account.getBalance() - amount;
					account.setBalance(balance);
					account.setTradingResult(200);
				}
				else {
					System.out.println("withdraw fail");
					account.setTradingResult(600);
				}
			}catch(SQLException se) {
				se.printStackTrace();
				account.setTradingResult(600);

			}finally { // ��ü �ݱ�
				try {
					DBUtil.dbClose(con, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
					account.setTradingResult(600);
				}
			}
		}
		else { // ���� �ܾ��� ����Ϸ��� �׼����� ������ ����
			System.out.println("����Ϸ��� �ݾ��� ���� �ܾ׺��� �����ϴ�.");
			account.setTradingResult(700); // ���� �ڵ� 700 : ���� �ܾ� ����
		}
		
		return account;
	}
}