package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import model.util.DBUtil;

public class CertificationImpl implements Certification{

	@Override
	public int deauthentication(String accountNumber) { //인증 해제
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int count = 0;

		try {
			con = DBUtil.getConnection();
			String sql = "update certification set certification = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, 0);
			pstmt.setString(2, accountNumber);
			count = pstmt.executeUpdate();
		
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
		return count;
	}
	public int setCertNumber(String accountNumber) {
		int certNumber = 0;
		// 8자리 난수 만들기
		Random rand = new Random(); 
		String rst = Integer.toString(rand.nextInt(8) + 1);

		for(int i=0; i < 7; i++){
			rst += Integer.toString(rand.nextInt(9));
		}
		certNumber = Integer.parseInt(rst);

		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int count = 0; // 업데이트 성공 여부
		// 인증번호 업데이트
		try {
			con = DBUtil.getConnection();
			String sql = "update certification set cert_number = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, certNumber);
			pstmt.setString(2, accountNumber);
			
			count = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				DBUtil.dbClose(con, pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int getCertNumber(String accountNumber) {
		Connection con = null;
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int certNumber = 0; 
		
		try{
			con = DBUtil.getConnection();
			
			String sql = "select cert_number from certification where user_id = (select user_id from user where account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("cert_number"));
				certNumber = rs.getInt("cert_number");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // 객체 닫기
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return certNumber;
	}
	
	public int getCertification(String accountNumber) {
		Connection con = null;
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int certification = 0; 
		
		try{
			con = DBUtil.getConnection();
			
			String sql = "select certification from certification where user_id = (select user_id from user where account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("certification"));
				certification = rs.getInt("certification");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // 객체 닫기
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return certification;
	}
}
