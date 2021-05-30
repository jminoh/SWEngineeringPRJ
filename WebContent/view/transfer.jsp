<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	This is transfer page.
	<form action = "/atm_service/atm-services/transfer" method = "post"> 
		송금할 계좌와 금액을 넣어주십시오:<br>
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}" /> <%-- 내 계좌 --%>
		송금 계좌: <input type = "text" name = "transferAccountNumber" /> <br> <%-- 송금할 계좌 --%>
		송금 금액: <input type = "text" name = "amount" /> <br> <%-- 송금할 금액 --%>
		<input type = "submit" value = "Transfer" /> <%-- controller 로 request 보내기 --%>
	</form>
</body>
</html>