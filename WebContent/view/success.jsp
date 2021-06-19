<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	form{
		padding-left: 10px;
	}

	body{
	margin:0px;
    padding:0px;
    background-color: rgb(152, 178, 233);
	}
	#grid{
	 display: grid;
	 grid-template-columns: 250px 1fr;	
	}
	.a1{
		width: 120;
		height: 300;
		border-radius: 10px;
		padding-left: 5px;
		background-color: rgb(212, 224, 238);
		text-align: center;
	}
	.a2{
		padding-top: 30%;
		padding-bottom: 30%;
	}
	.a3{
		width: 150;
		height: 300;
		border-radius: 10px;
		background-color: rgb(152, 178, 233);
		padding-top: 30px;
		padding-left: 10px;
	}

	.btn3{
	margin-top:80px;	
   }
   .btn4{
	margin-top:-10px;	
   }
 
 

	</style>
</head>
<body>
	<h1></h1>
	<div id="grid">
 
	   <div id="left" >
		 <div class="a1">
 			<%-- 명세표 출력을 대신하여 거래정보 출력 --%>
			 <p class="a2">	계좌 : ${accountNumber}<br> 거래금액 : ${amount}<br> 잔액 : ${balance}<br>
				계속 거래하시겠습니까?<br></p> 
 
		 </div>
	     
	   </div>
 
	   <div id="right">
		   <div class="a3">
			<form action = "/atm_service/check/continue" method = "post" class="btn4">
				<input type = "hidden" name = "action" value = "${action}" />
				<input type = "hidden" name = "accountNumber" value = "${accountNumber}"/>
				<input type = "hidden" name = "continue" value = "yes"/>
				<input type = "submit" value = "계속" />
			</form>
			</div>​
			<div class="a3">
				<form action = "/atm_service/check/continue" method = "post" class="btn3">
					<input type = "hidden" name = "action" value = "${action}" />
					<input type = "hidden" name = "accountNumber" value = "${accountNumber}"/>
					<input type = "hidden" name = "continue" value = "no"/>
					<input type = "submit" value = "메인" />
				</form>
			</div>​
		 
	  </div>
  </div>	
</body>
</html>