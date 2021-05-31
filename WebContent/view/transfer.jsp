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
	.a4{
		padding-top: 30%;
		padding-bottom: 30%;
		font-size: 14px;
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
		width: 30px;
		height: 30px;
	}
	.btn2{
		width: 50px;
		height: 30px;
		padding-left: 3px;
	}
	

</style>
<script type="text/javascript">
function setText(number) {
	var text = document.getElementById("inputText").value;
	if (number == "-100") {
		document.getElementById("inputText").value = text.slice(0, text.length -1);
	} else if (number == "-200") {
		document.getElementById("inputText").value = '';
	} else {
		document.getElementById("inputText").value += number;
	}
}
</script>
</head>
<body>
	<h1></h1>
	<div id="grid">
 
	   <div id="left" >
		 <div class="a1">
 
			 <p class="a4">송금할 계좌와 금액을 입력하십시오</p>
 
		 </div>
		 <form action = "/atm_service/atm-services/transfer" method = "post"> 
			<input type = "hidden" name = "action" value = "${action}" />
			<input type = "hidden" name = "accountNumber" value = "${accountNumber}" /> <%-- 내 계좌 --%>
			계좌: <input type = "text" name = "transferAccountNumber" /> <br> <%-- 송금할 계좌 --%>
			금액: <input type = "text" name = "amount" id ="inputText"/> <%-- 송금할 금액 --%>
			<button type = "submit" >송금</button><%-- controller 로 request 보내기 --%>
		</form>    
	   </div>
 
	   <div id="right">
		   <div class="a3">
			<div>
			<button class="btn1" onclick="setText(1)">1</button>
			<button class="btn1" onclick="setText(2)">2</button>
			<button class="btn1" onclick="setText(3)">3</button>
			</div>
			<div>
			<button class="btn1" onclick="setText(4)">4</button>
			<button class="btn1" onclick="setText(5)">5</button>
			<button class="btn1" onclick="setText(6)">6</button>
			</div>
			<div>
			<button class="btn1" onclick="setText(7)">7</button>
			<button class="btn1" onclick="setText(8)">8</button>
			<button class="btn1" onclick="setText(9)">9</button>
			</div>
			<div>
			<button class="btn1" onclick="setText(0)">0 </button>
			<button class="btn1" onclick="setText('000')">천 </button>
			<button class="btn1" onclick="setText('0000')">만 </button>
			</div>
			<div>
			<button class="btn2" onclick="setText(-100)">정정</button>
			<button class="btn2" onclick="setText(-200)">취소</button>
			</div>		
 
 
		   </div>​
		 
	  </div>
  </div>
 
 
</body>
</html>