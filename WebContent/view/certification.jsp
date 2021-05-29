<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	Connection conn = null;
	ResultSet rs = null; // select 문장에 의해 반환되는 레코드셋을 참조하기 위한 객체
	PreparedStatement pstmt = null; // 동적인 sql 문장을 실행하기 위한 객체
	String checkNumber = null; 
	
	try{
		String jdbcUrl = "jdbc:mysql://218.101.136.155:3306/atm";
		String dbId = "atm_manager";
		String dbPass = "gachon123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		request.setCharacterEncoding("UTF-8"); // 파라미터값 인코딩
		String phone_number = request.getParameter("phone_number"); // 파라미터값 phone_number를 phone_number에 저장
		
		String sql = "select cert_number from certification where certification.user_id in (select user.user_id from user where user.phone_number = ?)";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, phone_number);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			out.println(rs.getInt("cert_number"));
			checkNumber = Integer.toString(rs.getInt("cert_number"));
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
</head>
<body>
	This is certification page.
	<form action = "/atm_service/certification" method = "post"> 
		본인인증을 진행합니다.: <%-- Account 객체 가져와 포함 시켜주어야 합니다. --%>
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}" />
		<input type = "hidden" name = "checkNumber" value = "<%= checkNumber %>" /> <%-- [TODO] 문법 확인 필요 --%>
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "submit" value = "OK" /> <%-- controller 로 request 보내기 --%>
	</form>
</body>
</html>