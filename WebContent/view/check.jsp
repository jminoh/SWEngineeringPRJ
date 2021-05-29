<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	This is check card/account page.
	<%-- [TODO] 카드 통장 체크 다른 페이지로 이동하기 --%>
	<form action = "/atm_service/check/card" method = "post">
		카드 통장을 투입해 주십시오:
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "text" name = "accountNumber" /> <br>
		<input type = "submit" value = "Card" />
	</form>
</body>
</html>