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
	var text = document.getElementById("inputText").value; // 현재 입금금액 입력창에 있는 값 읽어오기
	if (number == "-100") {
		document.getElementById("inputText").value = text.slice(0, text.length -1); // 현재 입금금액 입력창의 맨 마지막 숫자 삭제
	} else if (number == "-200") { 
		document.getElementById("inputText").value = ''; // 현재 입금금액 입력창의 금액 삭제
	} else {
		if (number == "0" | number == "000" | number == "0000") {
			if (document.getElementById("inputText").value != '') { // 0을 입력 시 시작 숫자가 아니면 입력
				document.getElementById("inputText").value += number;
			}
		} else {
			document.getElementById("inputText").value += number; // 그 외 숫자 입력
		}
	}
}
</script>
</head>
<body>
	<h1></h1>
	<div id="grid">
 
	   <div id="left" >
		 <div class="a1">
 
			 <p class="a2">입금할 금액을 넣어주십시오.</p>
 
		 </div>     
			<form action = "/atm_service/atm-services/deposit" method = "post">  <%-- Account 객체 가져와 포함 시켜주어야 합니다. --%>
				<input type = "hidden" name = "action" value = "${action}" />
				<input type = "hidden" name = "accountNumber" value = "${accountNumber}" />
				<input type = "text" name = "amount"  id ="inputText" /> <%-- 입금할 금액 --%>
				<button type = "submit">입금</button> <%-- controller 로 request 보내기 --%>
			</form>
	   </div>
	   <div id="right">
		   <div class="a3">
			<%-- setText(입금금액) 함수를 이용하여 입금금액 입력창에 금액 입력 --%>
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