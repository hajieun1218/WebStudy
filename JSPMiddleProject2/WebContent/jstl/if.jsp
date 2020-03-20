<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	JSTL : JSP => 태그형으로 프로그램을 제작(view) 
			태그로 제어문, 변수선언, 날짜, ...
	JSTL ===> XML로 제작
			  (XML의 형식: 열었으면 반드시 닫아야한다, 지원하는 속성외 다른 속성을 이용하면 에러가 난다,
						 속성값은 반드시 ""를 사용해야한다)
			JSTL은 출력할때 무조건 request,session에서 값을 가져와야한다
							    		 
		1. core
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			
			1) 변수 선언
				<c:set> 
					 <c:set var="id" value="admin"/> ==> request.setAttribute("id","admin")
						===> 출력 ==> ${id}
				<c:out value="test"> => out.println() ======> ${}로 많이 씀
				
			2) 제어문
				<c:if> ==> else가 존재하지 않는다
					<c:if test="조건문">
						실행문장
					</c:if>
					
				<c:forEach>
					for(int i=1;i<=10;i++) {
					}
					=> <c:forEach var="i" begin="1" end="10" step="1">
													=======  ========
													i<=10    step은 1일 경우에는 생략 가능
													*** 단점 : step="-1" ==> 감소는 없다
					   </c:forEach> 
					
					for(MovieVO vo:list) {
					}
					=> <c:forEach var="vo" items="list">
					   </c:forEach>
					   
					   varStatus="s" : 배열첨자(배열의 index번호)
					   <c:forEach var="sub" items="${subjects }" varStatus="s">
						    <li>${s.index+1}.${sub }</li>
					   </c:forEach>
					
				<c:choose> : 다중조건문(if~else), 선택문(switch)
					<c:choose>
						<c:when test="조건문">실행문장</c:when>   ==> if
						<c:when test="조건문">실행문장</c:when>   ==> else if
						<c:when test="조건문">실행문장</c:when>   ==> else if
						<c:when test="조건문">실행문장</c:when>   ==> else if
						<c:otherwise></c:otherwise>          ==> else
					</c:choose>
					
				<c:forTokens var="s" value="red,blue,green" delimt=",">
				====> StringTokenizer()
				
			3) URL
				<c:redirect url=""> : 화면이동 (response.sendRedirect())
			
			
		2. fmt
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
			
		3. fn ==> String,Collection 제어
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions" %>
			${title.substring()} (X) ====> ${fn:title.substring()}
			
		========
		4. sql  =======> DAO
		5. xml  =======> Parser (JAXP,JAXB)
		======== 태그 있지만 사용하지 않는다
 --%>
 
<%
	int sex=1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Java로 코딩을 할 경우</h2>
	<%
		if(sex==1) {
	%>
			남자입니다
	<%
		}
		else {
	%>
			여자입니다
	<%
		}
	%>
	<br>
	
	<c:set var="sex" value="2"/>
	<%--
		var   ==> key
		value ==> 값
		request.setAttribute("sex", 2);
	--%>
	<h2>JSTL을 이용할 경우</h2>
	<%-- request나 session에 들어있는 값!! (일반변수 X) --%>
	<c:if test="${sex==1 }"> 
		남자입니다
	</c:if>
	<c:if test="${sex==2 }">
		여자입니다
	</c:if>
	
</body>
</html>