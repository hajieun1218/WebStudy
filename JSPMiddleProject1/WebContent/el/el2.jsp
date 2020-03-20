<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	 	${연산자}
	 		1. 산술연산자
	 		   ( + , - , * , /(div) , %(mod) )
	 		   				=> ${5/2} , ${5 div 2}
	 		   				
	 			/ -> <%= 10/3 %> ==> 3(정수)
	 			  -> ${10/3} ==> 3.333(실수)
	 			+ : 자바 => 산술연산, 문자열 결합
	 			          "Hello"+10 ==> Hello10
	 			          <%= "100"+"10" %> ==> 10010
	 			          <%=null+10 %> ==> error
	 				EL => 순수하게 산술만 가능  ========>(문자열 결합은 +=)
	 					  "Hello"+10 ==> error
	 					  ${"100"+"10"} ==> 110
	 					  ${"Hello"+=10 }
	 					  ${null+10 } ====> null이 자동으로 0으로 변경
	 					  
	 						var a="Hello"+'Hello'  ==> "",'' 둘 다 가능
	 						${sessionScope.id}  로그인 안했으면 null (예매,댓글)
	 						
	 		2. 비교연산자  ==> true/false
	 			==(eq) (문자열,숫자) ==> ${requestScope.id == 'admin'} (.equals(X))
	 			!=(ne) (문자열,숫자) ==> ${requestScope.id ne 'admin'}
	 			<(lt)  ${10<5}
	 			>(gt)  ${10>5}
	 			<=(le) ${10<=5}
	 			>=(ge) ${10>=5}
	 			
	 		3. 논리연산자 ==> true/false
	 			&&(and)
	 			||(or)
	 			!(not)
	 		
	 		4. empty연산자
	 			${empty list} => list에 값이 있냐 없냐 
	 						  => null이거나 빈공백("")이면 true를 리턴
	 			${list.size()>1} (X)
	 			
	 			String id;     => null
	 			String id="";  => ""
	 	
	 		5. 삼항연산자
	 			${조건?값1:값2}
	 		
	 		6. 문자열 결합: +=
	 		
	 		
	 		=============> JSP안에서는 <% %> 사용X
	 						<%@ %> 선언문은 존재
	 		
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= "Hello" %><br>
	${"Hello" }<br><br>
	
	<%=10/3 %><br>
	${10/3 }<br><br>
	
	<%="Hello"+10 %><br>
	<%-- ${"Hello"+10 }<br><br> --%>
	${"Hello"+=10 }<br><br> <!-- 문자열 결합은 += -->
	
	<%="100"+"10" %><br>
	${"100"+"10" }<br><br>
	
	<%-- <%=null+10 %><br> --%>
	${null+10 }<br><br>
</body>
</html>