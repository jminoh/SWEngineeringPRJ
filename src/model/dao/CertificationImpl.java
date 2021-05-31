package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.text.SimpleDateFormat;
import model.util.DBUtil;

public class CertificationImpl implements Certification{

	@Override
	public int deauthentication(String accountNumber) { //인증 해제(초기화)
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int count = 0;
		int web_cert;
		int app_cert;
		
		
		// 8자리 난수 만들기
		Random rand1 = new Random(); 
		String rst1 = Integer.toString(rand1.nextInt(8) + 1);

		for(int i=0; i < 7; i++){
			rst1 += Integer.toString(rand1.nextInt(9));
		}
		web_cert = Integer.parseInt(rst1);
		
		Random rand2 = new Random(); 
		String rst2 = Integer.toString(rand2.nextInt(8) + 1);

		for(int i=0; i < 7; i++){
			rst2 += Integer.toString(rand2.nextInt(9));
		}
		app_cert = Integer.parseInt(rst2);
		
		
		try {
			con = DBUtil.getConnection();
			String sql = "update certification set web_cert = ?, app_cert = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, web_cert);
			pstmt.setInt(2,  app_cert);
			pstmt.setString(3, accountNumber);
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
		Connection con = null;
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int count = 0; // 업데이트 성공 여부
		String phoneNumber = null;
		
		//현재 날짜 구하기
		SimpleDateFormat md = new SimpleDateFormat("MMdd", Locale.KOREA);
		Date date = new Date();
		String mtime = md.format(date);
		
		// 인증번호 업데이트
		try {
			con = DBUtil.getConnection();
			
			// 폰번호 구하기
			String sql = "select * from user where account_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				phoneNumber = rs.getString("phone_number");
			}
			
			// 폰번호 끝 4자리에 현재 날짜 추가해서 문자열 생성
			String phone4 = phoneNumber.substring(phoneNumber.length()-4, phoneNumber.length());
			int result = Integer.parseInt(phone4.concat(mtime));
			
			sql = "update certification set web_cert = ? where certification.user_id in (select user.user_id from user where user.account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, result);
			pstmt.setString(2, accountNumber);
			
			count = pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int getWebCert(String accountNumber) {
		Connection con = null;
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int certNumber = 0; 
		
		try{
			con = DBUtil.getConnection();
			
			String sql = "select web_cert from certification where user_id = (select user_id from user where account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("web_cert"));
				certNumber = rs.getInt("web_cert");
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
	
	public int getAppCert(String accountNumber) {
		Connection con = null;
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		int certNumber = 0; 
		
		try{
			con = DBUtil.getConnection();
			
			String sql = "select app_cert from certification where user_id = (select user_id from user where account_number = ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("app_cert"));
				certNumber = rs.getInt("app_cert");
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
}
