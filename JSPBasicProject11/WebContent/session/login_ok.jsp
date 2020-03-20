<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
	// 데이터가 적으면 getParameter로 받는다
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	// DAO 연결
	String res=dao.isLogin(id, pwd);
	
	
	if(res.equals("NOID")) {
%>
		<script>
		alert("아이디가 존재하지 않습니다");
		history.back();
		</script>
<%
	}
	else if(res.equals("NOPWD")) {
%>
		<script>
		alert("비밀번호가 틀립니다\n다시 입력하세요");
		history.back();
		</script>
<%
	}
	else {
		// 세션 저장
		session.setAttribute("id", id);
		session.setAttribute("name", res);
		// 이동
		response.sendRedirect("diary.jsp");
	}	
%>
