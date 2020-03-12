<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, java.net.*"%>

<%
	// get방식은 톰캣에서 한글 인코딩 처리를 한다, post방식만 인코딩 처리!!
	// Server -> server.xml 63번쨰 줄 URIEncoding="UTF-8"
	
	String fn=request.getParameter("fn");
	// Content-Disposition : 밑에 다운로드 창 , 다운로드할까요?만 보내주고 데이터는 안보내줌
	response.setHeader("Content-Disposition", "attachment;filename="  // Header: 데이터 보내기 전에 먼저 물어보는거
			+URLEncoder.encode(fn, "UTF-8"));
	
	File file=new File("c:\\upload\\"+fn);
	response.setContentLength((int)file.length());
	
	try {
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); // 파일을 읽어오겠다
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); // 값을 채워주겠다
		int i=0;
		byte[] buffer=new byte[1024]; // 데이터를 1024씩 쪼개서 보낸다
		while((i=bis.read(buffer, 0, 1024))!=-1) { // i: 읽은 바이트 개수
			bos.write(buffer, 0, i);
		}
		
		// out: html을 메모리에 출력하는 용도 => 다운로드할때 out객체를 잠시 빌림 => 끝나면 기능 다 지우고  원래상태로 복귀(초기화) pushBody() 
		out.clear();
		out=pageContext.pushBody();
		
		bis.close();
		bos.close();
		
	} catch(Exception ex) {}
%>