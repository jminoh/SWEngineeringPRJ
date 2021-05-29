package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.util.DBUtil;

public class CertificationImpl implements Certification{
	private int userID;
	private boolean certification;
	ATMTrading trading = new ATMTradingImpl();
	Connection con;

	@Override
	public void deauthentication(String accountNumber) { //인증 해제
		Connection con = null;
		PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
		ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
		
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
				DBUtil.dbClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
