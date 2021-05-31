<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = null;
	ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
	PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체

	try{
		String jdbcUrl = "jdbc:mysql://218.101.136.155:3306/atm";
		String dbId = "atm_manager";
		String dbPass = "gachon123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		request.setCharacterEncoding("UTF-8"); // 파라미터값 인코딩
		int app_cert = Integer.parseInt(request.getParameter("app_cert"));
		String phone_number = request.getParameter("phone_number"); // 파라미터값 phone_number를 phone_number에 저장
		
		String sql = "update certification set app_cert = ? where certification.user_id in (select user.user_id from user where user.phone_number = ?)";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, app_cert);
		pstmt.setString(2, phone_number);
	
		int count = pstmt.executeUpdate();
		
		if(count > 0) {
			out.println("인증 성공");
		}
		else {
			out.println("인증 에러");
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally { // 객체 닫기
		try {
			pstmt.close();
			conn.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
%>
</body>
</html>