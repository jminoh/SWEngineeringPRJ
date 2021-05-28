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
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
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
	public int checkCard(String account_number) {
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		String sql;

		try {
			con = DBUtil.getConnection();
			sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
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
	public Account getAccount(String account_number) {
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체

		Account account = new Account(); // 입금 계좌
		String sql;
		try {
			con = DBUtil.getConnection();
			sql = "select * from account where account_number = ?"; // 투입한 카드/통장에 대한 레코드를 반환하는 sql문
			pstmt = con.prepareStatement(sql); // pstmt 객체에 sql 구문을 파싱
			pstmt.setString(1, account_number); // sql 구문에 첫번째 ? 에는 account_number 값을 바인딩
			rs = pstmt.executeQuery(); // select 문장에 의해 반환되는 레코드 셋을 rs 객체가 참조
		
			while(rs.next()) {
				account.setAccount_number(rs.getString("account_number")); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
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
			pstmt.setString(2, account.getAccount_number());
			
			int count = pstmt.executeUpdate(); // update 관련 구문에서 반영된 레코드의 건수를 반환
			if(count > 0) {
				balance = account.getBalance() + amount;
				account.setBalance(balance);
			}else {
				account.setBalance(-1);
			}

		}catch(SQLException se) {
			se.printStackTrace();
			account.setBalance(-1);
		}finally { // 객체 닫기
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

	
	public void withdraw() { //출금
		Certification cert = new Certification();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = new Account(); // 출금 계좌
		String check; // 인증 여부 확인을 위한 문자열 변수
		boolean continuation = true; // 거래를 계속할지 여부
		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		
		System.out.println("카드/통장을 투입해주십시오.");
		String account_number = sc.next();
		
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
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
				System.out.println("카드/통장을 다시 투입해주십시오.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		// 본인인증
		System.out.println("본인인증 중입니다.(y/n)");
		check = sc.next();
		
		try {
			switch(check){
			case "y":
				// 추가
				break;
			case "n":
				System.out.println("본인인증을 취소하였습니다.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			default:
				System.out.println("본인인증에 실패하였습니다.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		//
		
		while(continuation) {
		
			System.out.println("출금할 금액을 입력해주십시오.");
			int amount = sc.nextInt();
			
			if(account.getBalance() - amount > 0) { // 계좌 잔액이 출금하려는 액수보다 많으면 실행
				try {
					con = DBUtil.getConnection();
					String sql = "UPDATE account set balance = ? where account_number = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, account.getBalance() - amount);
					pstmt.setString(2, account.getAccount_number());
			
					int count = pstmt.executeUpdate(); // count는 update된 행의 수
					if(count > 0) { // update에 성공했다면
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
			else { // 계좌 잔액이 출금하려는 액수보다 적으면 실행
				System.out.println("출금하려는 금액이 계좌 잔액보다 많습니다.");
			}
			
			System.out.println("계속 거래하시겠습니까?(y/n)");
			check = sc.next();
			
			switch(check){
			case "y": // 계속 거래
				continuation = true;
				break;
			default: // 거래 중지
				try {
					continuation = false;
					cert.deauthentication(account.getAccount_number()); //출금한 계좌의 인증 여부 초기화
					break;
				}catch(Exception e) {
					e.printStackTrace();
				}finally { // 객체 닫기
					try {
						DBUtil.dbClose(con, pstmt, rs);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void transfer() { //송금
		Certification cert = new Certification();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account1 = new Account(); //송금할 계좌
		Account account2 = new Account(); //송금받을 계좌
		String check; // 인증 여부 확인을 위한 문자열 변수
		boolean continuation = true;
		int exist = 0; // 카드/통장이 있는지 확인하기 위한 변수
		
		System.out.println("카드/통장을 투입해주십시오.");
		String account_number = sc.next();
		
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from account where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				exist = rs.getInt("count(*)"); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
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
				System.out.println("카드/통장을 다시 투입해주십시오.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		// 본인인증
		System.out.println("본인인증 중입니다.(y/n)");
		check = sc.next();
		
		try {
			switch(check){
			case "y":
				// 추가
				break;
			case "n":
				System.out.println("본인인증을 취소하였습니다.");
				DBUtil.dbClose(con, pstmt, rs);				
				return;
			default:
				System.out.println("본인인증에 실패하였습니다.");
				DBUtil.dbClose(con, pstmt, rs);
				return;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		//
		
		while(continuation) {
		
			System.out.println("송금받을 계좌를 입력해주십시오.");
			String transaccount = sc.next();
		
			try {
				con = DBUtil.getConnection();
				String sql = "select count(*) from account where account_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, transaccount);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					exist = rs.getInt("count(*)"); // 문자열 타입의 데이터 계좌번호를 가져와 입금계좌의 계좌번호로 설정
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
					System.out.println("없는 계좌번호입니다");
					DBUtil.dbClose(con, pstmt, rs);
					return;
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		
			System.out.println("송금할 금액을 입력해주십시오.");
			int amount = sc.nextInt();
		
			if(account1.getBalance() - amount > 0) { // 계좌 잔액이 송금하려는 액수보다 많으면 실행
				try {
					con = DBUtil.getConnection();
					// 송금하는 계좌의 잔액을 update하는 sql문
					String sql = "UPDATE account set balance = ? where account_number = ?";  
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, (account1.getBalance() - amount));
					pstmt.setString(2, account1.getAccount_number());
					int count1 = pstmt.executeUpdate();
			
					// 송금받는 계좌의 잔액을 update하는 sql문
					sql = "UPDATE account set balance = ? where account_number = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, (account2.getBalance() + amount));
					pstmt.setString(2, account2.getAccount_number());
			
					int count2 = pstmt.executeUpdate();
					if((count1 > 0) && (count2 > 0)) { // 송금에 성공했다면
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
			else { // 계좌 잔액이 출금하려는 액수보다 적으면 실행
				
				System.out.println("송금하려는 금액이 계좌 잔액보다 많습니다.");
				
			}
		
			System.out.println("계속 거래하시겠습니까?(y/n)");
			check = sc.next();
			
			switch(check){
			case "y": // 계속 거래
				continuation = true;
				break;
			default: // 거래 중지
				try {
					continuation = false;
					cert.deauthentication(account1.getAccount_number()); //출금한 계좌의 인증 여부 초기화
					break;
				}catch(Exception e) {
					e.printStackTrace();
				}finally { // 객체 닫기
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