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

	.btn{
	margin-top:50px;	
   }
   .btn1{
	margin-top:-10px;	
   }
 
 

	</style>
</head>
<body>
	<h1></h1>
	<div id="grid">
 
	   <div id="left" >
		 <div class="a1">
			 <p class="a2">Error : ${errorMsg} </p> 
		 </div>   
	   </div>
 
	   <div id="right">
		   <div class="a3">
				<button type="button" class="btn1"  onclick='location.href ="/atm_service/home?action=index"'>메인</button>
			</div>​ 
	  </div>
  </div>
 

</body>
</html>