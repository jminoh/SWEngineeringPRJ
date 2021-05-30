<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	This is withdraw page.
	<form action = "/atm_service/atm-services/withdraw" method = "post"> 
		출금할 금액을 넣어주십시오: <%-- Account 객체 가져와 포함 시켜주어야 합니다. --%>
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}" />
		<input type = "text" name = "amount" /> <br> <%-- 입금할 금액 --%>
		<input type = "submit" value = "Withdraw" /> <%-- controller 로 request 보내기 --%>
	</form>
</body>
</html>