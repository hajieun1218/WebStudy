<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() { 
	// 가상선택자 
	
/* 	$('h1').css('color',"orange")
	$('h1:even').css("color","pink")  // 0부터 시작
	$('h1:odd').css("color",'magenta') */
	//$('h1').eq(4).css('background-color',"yellow")
	$('h1').first().css('background-color','green')
	$('h1').last().css('background-color','green')
});
/*  
 *  -- 자바 스크립트 시작
 * 	window.onload=function(){
	
	}
	window.onload=()=>{
	
	}
	$(document).ready(function(){   // (document).ready 생략
		
	})
 */
</script>
</head>
<body>
	<h1>Java</h1>
	<h1>Oracle</h1>
	<h1>JSP</h1>
	<h1>Spring</h1>
	<h1>Kotlin</h1>
</body>
</html>