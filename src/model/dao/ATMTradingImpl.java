package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Account;
import model.util.DBUtil;

public class ATMTradingImpl implements ATMTrading {

	@Override
	public int checkAccount(String account_number) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
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
	public int checkCard(String account_number) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
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
	public Account getAccount(String account_number) {
		Connection con = null;
		PreparedStatement pstmt = null; // ������ sql ������ �����ϱ� ���� ��ü
		ResultSet rs = null; // select ���忡 ���� ��ȯ�Ǵ� ���ڵ���� �����ϱ� ���� ��ü

		Account account = new Account(); // �Ա� ����
		String sql;
		try {
			con = DBUtil.getConnection();
			sql = "select * from account where account_number = ?"; // ������ ī��/���忡 ���� ���ڵ带 ��ȯ�ϴ� sql��
			pstmt = con.prepareStatement(sql); // pstmt ��ü�� sql ������ �Ľ�
			pstmt.setString(1, account_number); // sql ������ ù��° ? ���� account_number ���� ���ε�
			rs = pstmt.executeQuery(); // select ���忡 ���� ��ȯ�Ǵ� ���ڵ� ���� rs ��ü�� ����
		
			while(rs.next()) {
				account.setAccount_number(rs.getString("account_number")); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
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
			pstmt.setString(2, account.getAccount_number());
			
			int count = pstmt.executeUpdate(); // update ���� �������� �ݿ��� ���ڵ��� �Ǽ��� ��ȯ
			if(count > 0) {
				balance = account.getBalance() + amount;
				account.setBalance(balance);
			}else {
				account.setBalance(-1);
			}

		}catch(SQLException se) {
			se.printStackTrace();
			account.setBalance(-1);
		}finally { // ��ü �ݱ�
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
				account.setBalance(-1);
			}
		}
		return account;
	}

	@Override
	public Account withdraw(Account account, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account transfer(Account userAccount, Account transferAccount, int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	
/*

	
	public void withdraw() { //���
		Certification cert = new Certification();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = new Account(); // ��� ����
		String check; // ���� ���� Ȯ���� ���� ���ڿ� ����
		boolean continuation = true; // �ŷ��� ������� ����
		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		
		System.out.println("ī��/������ �������ֽʽÿ�.");
		String account_number = sc.next();
		
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
			}
			
			if(exist > 0) {
				sql = "select * from account where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, account_number);
				rs = pstmt.executeQuery();
			
			
				while(rs.next()) {
					account.setAccount_number(rs.getString("account_number"));
					account.setBalance(rs.getInt("balance"));
				}
			}
			else {
				System.out.println("ī��/������ �ٽ� �������ֽʽÿ�.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		// ��������
		System.out.println("�������� ���Դϴ�.(y/n)");
		check = sc.next();
		
		try {
			switch(check){
			case "y":
				// �߰�
				break;
			case "n":
				System.out.println("���������� ����Ͽ����ϴ�.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			default:
				System.out.println("���������� �����Ͽ����ϴ�.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		//
		
		while(continuation) {
		
			System.out.println("����� �ݾ��� �Է����ֽʽÿ�.");
			int amount = sc.nextInt();
			
			if(account.getBalance() - amount > 0) { // ���� �ܾ��� ����Ϸ��� �׼����� ������ ����
				try {
					con = DBUtil.getConnection();
					String sql = "UPDATE account set balance = ? where account_number = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, account.getBalance() - amount);
					pstmt.setString(2, account.getAccount_number());
			
					int count = pstmt.executeUpdate(); // count�� update�� ���� ��
					if(count > 0) { // update�� �����ߴٸ�
						System.out.println("withdraw success");
						System.out.println("balance : " + (account.getBalance() - amount));
						account.setBalance(account.getBalance() - amount);
					}
					else {
						System.out.println("withdraw fail");
					}
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			else { // ���� �ܾ��� ����Ϸ��� �׼����� ������ ����
				System.out.println("����Ϸ��� �ݾ��� ���� �ܾ׺��� �����ϴ�.");
			}
			
			System.out.println("��� �ŷ��Ͻðڽ��ϱ�?(y/n)");
			check = sc.next();
			
			switch(check){
			case "y": // ��� �ŷ�
				continuation = true;
				break;
			default: // �ŷ� ����
				try {
					continuation = false;
					cert.deauthentication(account.getAccount_number()); //����� ������ ���� ���� �ʱ�ȭ
					break;
				}catch(Exception e) {
					e.printStackTrace();
				}finally { // ��ü �ݱ�
					try {
						DBUtil.dbClose(con, pstmt, rs);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void transfer() { //�۱�
		Certification cert = new Certification();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account1 = new Account(); //�۱��� ����
		Account account2 = new Account(); //�۱ݹ��� ����
		String check; // ���� ���� Ȯ���� ���� ���ڿ� ����
		boolean continuation = true;
		int exist = 0; // ī��/������ �ִ��� Ȯ���ϱ� ���� ����
		
		System.out.println("ī��/������ �������ֽʽÿ�.");
		String account_number = sc.next();
		
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
			}
			
			if(exist > 0) {
				sql = "select * from account where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, account_number);
				rs = pstmt.executeQuery();
			
			
				while(rs.next()) {
					account1.setAccount_number(rs.getString("account_number"));
					account1.setBalance(rs.getInt("balance"));
				}
			}
			else {
				System.out.println("ī��/������ �ٽ� �������ֽʽÿ�.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		// ��������
		System.out.println("�������� ���Դϴ�.(y/n)");
		check = sc.next();
		
		try {
			switch(check){
			case "y":
				// �߰�
				break;
			case "n":
				System.out.println("���������� ����Ͽ����ϴ�.");
				DBUtil.dbClose(con, pstmt, rs);				
				return;
			default:
				System.out.println("���������� �����Ͽ����ϴ�.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		//
		
		while(continuation) {
		
			System.out.println("�۱ݹ��� ���¸� �Է����ֽʽÿ�.");
			String transaccount = sc.next();
		
			try {
				con = DBUtil.getConnection();
				String sql = "select count(*) from account where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, transaccount);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					exist = rs.getInt("count(*)"); // ���ڿ� Ÿ���� ������ ���¹�ȣ�� ������ �Աݰ����� ���¹�ȣ�� ����
				}
				
				if(exist > 0) {
					sql = "select * from account where account_number = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, transaccount);
					rs = pstmt.executeQuery();
			
					while(rs.next()) {
						account2.setAccount_number(rs.getString("account_number"));
						account2.setBalance(rs.getInt("balance"));
					}
				}
				else {
					System.out.println("���� ���¹�ȣ�Դϴ�");
					DBUtil.dbClose(con, pstmt, rs);
					return;
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		
			System.out.println("�۱��� �ݾ��� �Է����ֽʽÿ�.");
			int amount = sc.nextInt();
		
			if(account1.getBalance() - amount > 0) { // ���� �ܾ��� �۱��Ϸ��� �׼����� ������ ����
				try {
					con = DBUtil.getConnection();
					// �۱��ϴ� ������ �ܾ��� update�ϴ� sql��
					String sql = "UPDATE account set balance = ? where account_number = ?";  
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, (account1.getBalance() - amount));
					pstmt.setString(2, account1.getAccount_number());
					int count1 = pstmt.executeUpdate();
			
					// �۱ݹ޴� ������ �ܾ��� update�ϴ� sql��
					sql = "UPDATE account set balance = ? where account_number = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, (account2.getBalance() + amount));
					pstmt.setString(2, account2.getAccount_number());
			
					int count2 = pstmt.executeUpdate();
					if((count1 > 0) && (count2 > 0)) { // �۱ݿ� �����ߴٸ�
						System.out.println("transfer success");
						System.out.println("balance : " + (account1.getBalance() - amount));
						account1.setBalance(account1.getBalance() - amount);
					}
					else {
						System.out.println("transfer fail");
					}
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			else { // ���� �ܾ��� ����Ϸ��� �׼����� ������ ����
				
				System.out.println("�۱��Ϸ��� �ݾ��� ���� �ܾ׺��� �����ϴ�.");
				
			}
		
			System.out.println("��� �ŷ��Ͻðڽ��ϱ�?(y/n)");
			check = sc.next();
			
			switch(check){
			case "y": // ��� �ŷ�
				continuation = true;
				break;
			default: // �ŷ� ����
				try {
					continuation = false;
					cert.deauthentication(account1.getAccount_number()); //����� ������ ���� ���� �ʱ�ȭ
					break;
				}catch(Exception e) {
					e.printStackTrace();
				}finally { // ��ü �ݱ�
					try {
						DBUtil.dbClose(con, pstmt, rs);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
	}

 */
}