<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session.invalidate(); 전체 삭제
	session.removeAttribute("name"); // 일부 삭제(장바구니, 찜)
	response.sendRedirect("diary.jsp");
	
	/*
		Session: 서버에 일부 데이터 저장 -> 모든 JSP에서 공유할 목적으로 사용
		주요 기능
			1. setAttribute("key", Object) : 세션 공간에 저장
			2. getAttribute("key") : 저장된 값을 읽어올 때
			3. invalidate() : 저장된 모든 데이터를 삭제
			4. removeAttribute("key") : 한개씩 삭제
			5. setMaxactiveInterval : 저장 기간 설정
	*/
%>