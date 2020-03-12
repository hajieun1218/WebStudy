<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		application 객체 ==> ServletContext
		=> Servlet(Server+let): 서버에서 사용되는 가벼운 프로그램(let:가벼운 프로그램)을 web에서 사용할 수 있게
		
		application
			서버와 관련
				1) 서버 정보
					= getServerInfo()  ===> 3.1 (3:MajorVersion, 1:MinorVersion)
					= getMajorVersion(), getMinorVersion() :버전 정보
				2) 자원 정보
					= getRealPath() 
				3) web.xml을 읽어서 처리 : log파일
					= getInitParameter()
		
		
		
		
			Java SE : Application
			Java EE : 기업용 환경(WEB)
			Java ME : 모바일
		   
		   Servlet  VS  JSP
		           연결                  화면 UI
		   
		   브라우저 ==> 사용자가 데이터 전송 ==> 받을 수 있는 파일(Servlet,JSP)
		                                                                일반 자바파일에서는 받지 못한다 -> Servlet,JSP에서 받아서 매개변수로 넘겨줌
		                                                                
		   데이터 -> Servlet -> Java(처리과정) -> JSP(출력)   =======> MVC구조
		   		(Controller)  (Model)       (View)
		   
		   MVC 사용하는 이유 : 
		   	1) 재사용
		   	2) 확장성 
		   	====> 유지보수를 위해
		   	
		   	
		   	jsp 2.3
		   	oracle 11g
		   	
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%=application.getRealPath("/jsp3/application.jsp") %> --%>
	
	서버이름 : <%=application.getServerInfo() %><br>
	버전 : <%=application.getMajorVersion() %><br>
		 <%=application.getMinorVersion() %>
		 <%
		 	String driver=application.getInitParameter("driver");
		 	String url=application.getInitParameter("url");
		 	String username=application.getInitParameter("username");
		 	String pwd=application.getInitParameter("password");
		 	
		 	application.log("Driver:"+driver);
		 	application.log("URL:"+url);
		 	application.log("Username:"+username);
		 	application.log("Password:"+pwd);
		 	// 로그파일은 콘솔창에 출력
		 %>
</body>
</html>