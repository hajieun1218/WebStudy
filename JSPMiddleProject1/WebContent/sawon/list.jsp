<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl => 제어문사용할때   -->
<%
	SawonModel sm=new SawonModel();
	sm.sawonListData(request);  
	// 같은request ==>  call by reference 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>사원 목록</h1>
		<c:forEach var="name" items="${list }">
			${name }<br>
		</c:forEach>
	</center>
</body>
</html>