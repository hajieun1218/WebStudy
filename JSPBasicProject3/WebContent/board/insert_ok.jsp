<%@page import="com.sist.dao.BoardDAO"%>
<%@page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ~.ok 파일은 화면출력 안하고 값 받아서 처리 -->

<%
	// 데이터 받기
	try {
		request.setCharacterEncoding("UTF-8"); // 한글변환
	} catch(Exception ex) {}

	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	BoardVO vo=new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	BoardDAO dao=new BoardDAO();
	dao.boardInsert(vo);
	
	// 이동 => 목록(list.jsp)
	// 이동할 때 response
	response.sendRedirect("list.jsp");
%>