<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Service</title>
<link href="/atm_service/view/gui.css" rel="stylesheet" type="text/css">



</head>
<body>
	<div>
	<p>ATM SERVICE</p>
	</div>
	<div>
	<button type="button" class="btn buttons-left btn-text" onclick='location.href = "/atm_service/home?action=deposit"'> 입금 </button>
	<button type="button" class="btn buttons-left btn-text" onclick='location.href = "/atm_service/home?action=withdraw"'> 출금 </button>
	</div>
	<div>
	<button type="button" class="btn buttons-left btn-text" onclick='location.href = "/atm_service/home?action=transfer"'> 송금 </button>
    <button type="button" class="btn buttons-left btn-text">조회 </button>
	</div>
	<div>
	<button type="button" class="btn buttons-left btn-text">기타 </button>
	</div>
</body>
</html>