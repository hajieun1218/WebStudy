<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*  한글변환 => main에서 처리
	try {
		// request는 main꺼
		request.setCharacterEncoding("UTF-8");
 	} catch(Exception ex) {} */ 

	String ss=request.getParameter("ss");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<h1><%=ss %></h1>
	</div>
</body>
</html>