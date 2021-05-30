<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	This is success page. <br>
	${accountNumber} : ${balance}
	<br><br>
	계속 거래하시겠습니까?<br>
	<form action = "/atm_service/check/continue" method = "post">
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}"/>
		<input type = "hidden" name = "continue" value = "yes"/>
		<input type = "submit" value = "계속하기" />
	</form>
	<form action = "/atm_service/check/continue" method = "post">
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}"/>
		<input type = "hidden" name = "continue" value = "no"/>
		<input type = "submit" value = "메인으로" />
	</form>
	
</body>
</html>