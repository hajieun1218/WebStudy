<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		String name=request.getParameter("name");
		String name=pageContext.getRequest.getParameter("name");
		
		pageContext ==> 화면 조립, 화면 이동(request를 유지)
						======  ======
						inclued  forward
						
		화면 이동
		1. html ==> <a> <form action="">
		2. javascript ==> location.href ===> (ajax)
		3. java ==> sendRedirect(), forward()
		  request유지여부           x           o
		  URL주소가 바뀌면 sendRedirect, 바뀌지 않으면  forward
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.container {
	border: 1px solid black;
	width: 960px;
	height: 760px;
}
.header {
	border: 1px solid red;
	width: 960px;
	height: 120px;
}
.footer {
	border: 1px solid blue;
	width: 960px;
	height: 120px;
}
.content {
	border: 1px solid green;
	width: 960px;
	height: 520px;
}
.col-sm-3 {
	border: 1px solid yellow;
}
.col-sm-9 {
	border: 1px solid magenta;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row header">
			<%
				pageContext.include("header.jsp");
				// <jsp:include page="header.jsp"> ==> request를 공유
			%>
		</div>
		<div class="row content">
			<div class="col-sm-3">
				<%
					pageContext.include("side.jsp");	
				%>
			</div>
			<div class="col-sm-9">
				<%
					pageContext.include("content.jsp");
				%>
			</div>
		</div>
		<div class="row footer">
			<%
				pageContext.include("footer.jsp");
			%>
		</div>
	</div>
</body>
</html>