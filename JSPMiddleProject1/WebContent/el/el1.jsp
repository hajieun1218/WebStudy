<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		EL(Expression Language) : 화면에 출력하는 언어(표현언어)
				request를 자바로 받아서 다시 출력하는것이 아니라
				request를 바로 출력 -> 자바로 받는 과정을 생략할 수 있다 --> jsp에서 자바를 사용안함, 출력만 담당 (View)
				자바와 jsp를 분리하는 이유 : 재사용하기 위해(자바)=> 유지보수, jsp는 재사용이 불가
				자바에서는 request만 받으면 request,session,cookie 다 사용 가능
				
		-> 사용법
			${출력물} -> {}안에 일반 자바 변수 (X)
			
			<%= 일반 자바변수%>
			${} --> getParameter("id") => ${param.id}
			${} --> request.getAttribute("id") 
					=> ${requestScope.id} ==> ${id} => requestScope는 생략 가능
			${} --> session.getAttribute("name","홍길동")
					=> ${sessionScope.name} => ${name} 
					=> sessionScope 생략 가능하지만 같은 key값이 있다면 우선순위는 request이므로 sessionScope 써줘야함
			예)
				String id="admin";
				${id} (X)
				request.setAttribute("id",id)
				${id} (O) --> request에 들어간 값만 출력, 일반 변수는 출력 X, 변수명이 아니라 키 값으로!!!
			예)
				request.setAttribute("id","admin");
				session.setAttribute("id","hong");
				
				${id} ==> admin
				${sessionScope.id} ==> hong
			
 --%>
<%
	String name="홍길동";
	request.setAttribute("name", name);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${name}
</body>
</html>