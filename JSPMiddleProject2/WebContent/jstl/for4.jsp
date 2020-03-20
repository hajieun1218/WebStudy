<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] subjects={"자바","오라클","JSP","Spring","Kotlin"};
	//request.setAttribute("subjects", subjects);
%>
<c:set var="subjects" value="<%=subjects %>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Java코딩</h2>
	<ul>
	<%	
		int i=1;
		for(String sub:subjects) {
	%>
			<li><%=i %>.<%=sub %></li>
	<%
			i++;
		}
	%>
	</ul>
	<br>
	
	<h2>JSTL코딩</h2>
	<ul>
		<%-- ${} ==> request.getAttribute() --%>
		<%-- varStatus="s" : 배열첨자(배열의 index번호) --%>
		<c:forEach var="sub" items="${subjects }" varStatus="s">
			<li>${s.index+1}.${sub }</li>
		</c:forEach>
	</ul>
</body>
</html>