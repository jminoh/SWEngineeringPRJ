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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // 객체 닫기
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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // 객체 닫기
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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		Account account = new Account(); // 입금 계좌
		String sql;
		try {
			con = DBUtil.getConnection();
			sql = "select * from account where account_number = ?"; // 투입한 카드/통장에 대한 레코드를 반환하는 sql문
			pstmt = con.prepareStatement(sql); // pstmt 객체에 sql 구문을 파싱
			pstmt.setString(1, accountNumber); // sql 구문에 첫번째 ? 에는 accountNumber 값을 바인딩
			rs = pstmt.executeQuery(); // select 문장에 의해 반환되는 레코드 셋을 rs 객체가 참조
		
			while(rs.next()) {
				account.setAccountNumber(rs.getString("account_number")); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
				account.setBalance(rs.getInt("balance")); // 정수형 타입의 데이터 잔액을 가져와 입금계좌의 잔액으로 설정
				System.out.println("balance : " + account.getBalance());
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally { // 객체 닫기
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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		int balance = 0; // 잔액
		
		try {
			con = DBUtil.getConnection();
			String sql = "UPDATE account set balance = ? where account_number = ?"; // 계좌에 남아있는 잔액을 바꾸기 위한 sql문
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount + account.getBalance());
			pstmt.setString(2, account.getAccountNumber());
			
			int count = pstmt.executeUpdate(); // update 관련 구문에서 반영된 레코드의 건수를 반환
			if(count > 0) {
				balance = account.getBalance() + amount;
				account.setBalance(balance);
				account.setTradingResult(200); // 정상코드 200 : 정상 처리
			}else {
				account.setTradingResult(600); // 오류코드 600 : 처리 오류
			}

		}catch(SQLException se) {
			se.printStackTrace();
			account.setTradingResult(600);
		}finally { // 객체 닫기
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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		int balance = 0; // 잔액
		
		if(account.getBalance() - amount > 0) { // 계좌 잔액이 출금하려는 액수보다 많으면 실행
			try {
				con = DBUtil.getConnection();
				String sql = "UPDATE account set balance = ? where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, account.getBalance() - amount);
				pstmt.setString(2, account.getAccountNumber());
		
				int count = pstmt.executeUpdate(); // count는 update된 행의 수
				if(count > 0) { // update에 성공했다면
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

			}finally { // 객체 닫기
				try {
					DBUtil.dbClose(con, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
					account.setTradingResult(600);
				}
			}
		}
		else { // 계좌 잔액이 출금하려는 액수보다 적으면 실행
			System.out.println("출금하려는 금액이 계좌 잔액보다 많습니다.");
			account.setTradingResult(700); // 오류 코드 700 : 계좌 잔액 부족
		}
		
		return account;
	}
}