<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%--
	기본객체(내장객체)
		*1) request  : 사용자 요청 정보, 사용자의 정보 ==> session생성, cookie생성
			HttpServletRequest
			
		*2) response : 응답 정보, header정보, 이동 정보(페이지 이동)(sendRedirect())
		                      ========   
		                                                  파일 다운로드(파일명, 파일크기를 서버에 먼저 전송 => 파일 전송)         
		   	(데이터 전송 (header,data)
		   	HttpServletResponse
		   	
		3) out 객체 : html을 출력하는 메모리 (출력버퍼)
			JspWriter
			
			<<JSP실행과정>>
			         a.jsp                    자바로 변경(서블릿) a_jsp.java(한번만 수행)
			사용자 요청 =========> 톰캣이 파일을 가지고 온다 =================================> 컴파일 ===> a_jsp.class ===> 실행
											    변경된 자바 파일이 존재? 있으면 업데이트,없으면 생성
												                             ======> 출력버퍼에 HTML을 출력 ===> 사용자에게 전송
		    1. 메모리에 출력 => print(), println(), getBufferSize() => 8kb, getRemaining()(남아있는 버퍼크기)
		    	=> 개발자보다 관리자가 주로 사용
		
		4) application : 서버정보, 버전, 로그파일 관리(log), web.xml을 제어
		                        ====
		                        3.1 
		
		*5) session
		
		6) pageContext : JSP관리 ===> include(), forward()
		-----------------------------------------------------------
		7) config ==> web.xml
		8) page = this
		9) exception ==> try~catch
		
		cookie는 내장 객체가 아니다 => 라이센스 기간, 최근본페이지
		
		
		
		
		
		out : 메모리(출력버퍼) 관리  (버퍼: 임시저장공간)
				= 메모리 출력 (HTML,XML) => out.println(), out.print(), <%= %>
				= 메모리 크기 확인 : getBufferSize() => dafault : 8kb
				= 남아있는 메모리 확인 : getRemaining()
				= 버퍼 지우기 : flush(), clear() ===> JSP (autiFlush())
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	현재 메모리 크기 : <%=out.getBufferSize() %><br>
	남아있는 메모리 크기 : <%=out.getRemaining() %><br>
	현재 사용중인 메모리 크기 : <%=out.getBufferSize()-out.getRemaining() %><br><br>
	
	현재 메모리 크기 : <% out.println(out.getBufferSize()); %><br>
	남아있는 메모리 크기 : <% out.println(out.getRemaining()); %><br>
	현재 사용중인 메모리 크기 : <% out.println(out.getBufferSize()-out.getRemaining()); %><br>
	
</body>
</html>