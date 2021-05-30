<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	This is certification page.
	<%-- 웹 상에서의 본인인증 진행 --%>
	<form action = "/atm_service/certification" method = "post"> 
		본인인증을 진행합니다. <br>
		본인인증 어플에서 진행 후 화면의 OK 버튼을 눌러주세요.<br>
		<input type = "hidden" name = "accountNumber" value = "${accountNumber}" />
		<input type = "hidden" name = "action" value = "${action}" />
		<input type = "submit" value = "OK" /> <%-- controller 로 request 보내기 --%>
	</form>
</body>
</html>