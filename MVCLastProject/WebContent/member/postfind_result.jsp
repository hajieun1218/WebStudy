<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {
	font-size: 8pt;
}
</style>

<!-- Jquery -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.post_click').hover(function(){
		$(this).css("cursor","pointer").css("background-color","#FC6"); // 커서가 올라가면 pointer (if)
	},function(){
		$(this).css("cursor","none").css("background-color","white");;  // (else)
	});
	
	$('.post_click').click(function(){
		var zip=$(this).attr('zip');   // this => 클릭한 tr태그 위치 가져옴
		var addr=$(this).attr('addr');
		parent.frm.post1.value=zip.substring(0,3); // parent : join.jsp
		parent.frm.post2.value=zip.substring(4,7);
		parent.frm.addr1.value=addr;
		parent.Shadowbox.close();
	})
});
</script>

<!-- 자바스크립트 -->
<!-- <script type="text/javascript">
function ok(zip,addr) {
	parent.frm.post1.value=zip.substring(0,3); // parent : join.jsp
	parent.frm.post2.value=zip.substring(4,7);
	parent.frm.addr1.value=addr;
	parent.Shadowbox.close();
} -->
</script>
</head>
<body>
	<c:if test="${count==0 }">
		<table class="table">
			<tr>
				<td class="text-center">
					<b>검색한 결과가 없습니다.</b>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count!=0 }">
		<table class="table">
			<tr class="success">
				<th class="text-center">우편번호</th>
				<th class="text-center">주소</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr class="post_click" zip="${vo.zipcode }" addr="${vo.address }"> <!-- 속성명은 내 마음대로 할 수 있다 -->
					<td class="text-center">${vo.zipcode }</td>
					<!-- 자바스크립트 -->
					<%-- <td><a href="javascript:ok('${vo.zipcode }','${vo.address }')">${vo.address }</a></td> --%>
					<!-- Jquery -->
					<td>${vo.address }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>