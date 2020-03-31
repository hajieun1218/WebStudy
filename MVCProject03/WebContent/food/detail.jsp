<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<c:forTokens items="${vo.image }" delims="^" var="img">
					<td>
						<img src="${img }" width="100%">
					</td>
				</c:forTokens>
			</table>
			<table class="table">
				<tr>
					<td colspan="2">
						<h2>
							${vo.title }&nbsp;
							<font color="#FC6">${vo.score }</font>
						</h2>
					</td>
				</tr>
				<tr>
					<th width="20%">주소</th>
					<td width="80%">${vo.address }</td>
				</tr>
				<tr>
					<th width="20%">전화번호</th>
					<td width="80%">${vo.address }</td>
				</tr>
				<tr>
					<th width="20%">음식종류</th>
					<td width="80%">${vo.type }</td>
				</tr>
				<tr>
					<th width="20%">가격대</th>
					<td width="80%">${vo.price }</td>
				</tr>
				<tr>
					<th width="20%">평가</th>
					<td width="80%">맛있다(${vo.good }) | 괜찮다(${vo.soso }) | 별로</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<th class="text-right">
						<a href="middle.do?cno=${vo.cno }" class="btn btn-md btn-success">목록</a>
					</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>