<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	저장장소 : 웹브라우저에 저장 (검색어,방문한 사이트,상세보기 저장 --> 장바구니)
	동작과정 
		1) 생성단계
			Cookie cookie=new Cookie("key","value") -> Map형식(key중복되면 안된다)
													-> 문자열만 저장이 가능
		2) 저장단계 : response.addCookie(cookie)
		3) Cookie 읽기
			Cookie[] cook=request.getCookies() -> ArrayList에 저장 후 출력
		4) 삭제, 저장기간
			삭제 -> setMaxAge(0)
			저장기간 -> setMaxAge(60*60*24) -> 하루
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>