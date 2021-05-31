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
		padding-left: 20px;
	}


   .btn1{
	margin-top:-10px;
	margin-left:-20px;	
   }
	</style>
</head>
<body>
	<h1></h1>
	<div id="grid">
 
	   <div id="left" >
		 <div class="a1">
 
			<p class="a2">본인인증을 진행합니다.<br>
				본인인증 어플에서 진행 후<br>화면의 OK 버튼을 눌러주세요.</p>
 
		 </div>
	     
	   </div>
 
	   <div id="right">
		   <div class="a3">
				<form action = "/atm_service/certification" method = "post" class="btn1"> 
				<input type = "hidden" name = "accountNumber" value = "${accountNumber}" />
				<input type = "hidden" name = "action" value = "${action}" />
				<input type = "submit" value = "OK" /> <%-- controller 로 request 보내기 --%>
			</form>
			</div>​
​
		 
	  </div>
  </div>
 


</body>
</html>