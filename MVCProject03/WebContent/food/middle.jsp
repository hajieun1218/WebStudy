<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<c:forEach var="vo" items="${list }">
					<table class="table table-hover">
						<tr>
							<td width="20%" rowspan="3"><a href="detail.do?no=${vo.no }"><img src="${vo.image }" width="250" height="200"></a></td>
							<td width="80%">
								<h2>
									<a href="detail.do?no=${vo.no }">${vo.title }</a>&nbsp;
									<font color="#FC6">${vo.score }</font>
								</h2>
							</td>
						</tr>
						<tr>
							<td width="80%">${vo.address }</td>
						</tr>
						<tr>
							<td width="80%">${vo.tel }</td>
						</tr>
					</table>
				</c:forEach>
			</table>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-right">
						<a href="category.do" class="btn btn-md btn-success">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>