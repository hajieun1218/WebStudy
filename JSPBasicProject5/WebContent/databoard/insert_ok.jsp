<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, com.oreilly.servlet.multipart.*, com.oreilly.servlet.*"%>
<%@ page import="java.io.*" %>
<%
	try {
		request.setCharacterEncoding("UTF-8");
	} catch(Exception ex) {}

	String path="c:\\upload";
	int maxSize=100*1024*1024;  // 100메가
	String enctype="UTF-8";  // 한글이름 파일을 받기 위해
	// 업로드 -> 한줄에 끝남
	MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,
			new DefaultFileRenamePolicy()); // 같은 파일명이면 파일명 뒤에 (1),(2),(3),.. 붙이면서 이름을 바꿔줌
	
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	String filename=mr.getOriginalFileName("upload"); // 사용자가 보낸 파일명 (원래파일)
	String fn=mr.getFilesystemName("upload"); // 변경된 파일명 -> 동일한 이름의 파일을 저장하면 뒤에 1,2,..가 붙는다
	
	FileBoardVO vo=new FileBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	if(filename==null) {
		vo.setFilename("");
		vo.setFilesize(0);
	}
	else {
		File file=new File(path+"\\"+fn);
		vo.setFilename(fn);
		vo.setFilesize((int)file.length());
	}
	
	FileBoardDAO dao=new FileBoardDAO();
	// insert
	dao.boardInsert(vo);
	// 이동
	response.sendRedirect("list.jsp"); // 맨 첫줄에 올라오니까 page 안줘도 된다
%>