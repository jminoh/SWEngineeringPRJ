package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import model.util.DBUtil;

public class CertificationImpl implements Certification{
	private int certNumber;
	Connection con;

	@Override
	public void deauthentication(String accountNumber) { //인증 해제
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		
		try {
			con = DBUtil.getConnection();
			String sql = "update certification set certification = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, 0);
			pstmt.setString(2, accountNumber);
			int count = pstmt.executeUpdate();
		
			if(count > 0) {
				System.out.println("certification reset success");
			}
			else {
				System.out.println("certification reset fail");
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				DBUtil.dbClose(con, pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setCertNumber(String accountNumber) {
		// 8자리 난수 만들기
		Random rand = new Random(); 
		String rst = Integer.toString(rand.nextInt(8) + 1);

		for(int i=0; i < 7; i++){
			rst += Integer.toString(rand.nextInt(9));
		}
		this.certNumber = Integer.parseInt(rst);

		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		
		// 인증번호 업데이트
		try {
			con = DBUtil.getConnection();
			String sql = "update certification set cert_number = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, certNumber);
			pstmt.setString(2, accountNumber);
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				DBUtil.dbClose(con, pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getCertNumber() {
		return certNumber;
	}
}
