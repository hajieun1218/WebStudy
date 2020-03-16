<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<%
    // insert.jsp에서 main으로 넘어가지 않고 바로 insert_ok로 넘어오기 때문에 request는 main이 아니라 insert_ok꺼
	try {
		request.setCharacterEncoding("UTF-8");
	} catch(Exception ex) {}
%>
<jsp:useBean id="vo" class="com.sist.dao.BoardBean">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
	dao.boardInsert(vo);

	// 이동
	response.sendRedirect("main.jsp?mode=5");
%>