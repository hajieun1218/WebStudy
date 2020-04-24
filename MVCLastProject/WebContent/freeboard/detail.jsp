<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
th{
	color: black;
}
td{
	background-color: white;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var u=0;
var i=0;
$(function() {
	$('.upBtn').click(function(){
		$('.reply_update').hide();
		$('.reply_insert').hide();
		var no=$(this).attr("data-no");
		if(u==0) {
			$('#m'+no).show();
			u=1;
		}
		else {
			$('#m'+no).hide();
			u=0;
		}
	})
	
	$('.inBtn').click(function(){
		$('.reply_update').hide();
		$('.reply_insert').hide();
		var no=$(this).attr("data-no");
		if(i==0) {
			$('#i'+no).show();
			i=1;
		}
		else {
			$('#i'+no).hide();
			i=0;
		}
	})
});
</script>
</head>
<body>
<div class="wrapper row2">
  	<div id="services" class="clear"> 
  		<div class="row text-center">
  			<img src="../freeboard/jsp_board2.png" style="width: 900px; height: 200px;">
  		</div>
  		<div class="row">
  			<table class="table">
				<tr>
					<th class="text-center success" width="20%">번호</th>
					<td class="text-center" width="30%">${vo.no }</td>
					<th class="text-center success" width="20%">작성일</th>
					<td class="text-center" width="30%">
						<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<th class="text-center success" width="20%">이름</th>
					<td class="text-center" width="30%">${vo.name }</td>
					<th class="text-center success" width="20%">조회수</th>
					<td class="text-center" width="30%">${vo.hit }</td>
				</tr>
				<tr>
					<th class="text-center success" width="20%">제목</th>
					<td class="text-center" colspan="3">${vo.subject }</td>
				</tr>
				<tr>
					<td class="text-left" colspan="4" valign="top" height="200">${vo.content }</td>
				</tr>
				<tr>
					<td class="text-right" colspan="4">
						<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
						<a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
						<a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
					</td>
				</tr>
			</table>
			<div style="height: 20px;"></div>
			<table class="table">
				<c:forEach var="rvo" items="${list }">
					<tr>
						<td class="text-left">
							<c:if test="${rvo.group_tab>0 }">
								<c:forEach var="i" begin="1" end="${rvo.group_tab }">
									&nbsp;&nbsp;
								</c:forEach>
								<img src="icon_reply.gif"/>
							</c:if>
							${rvo.name }&nbsp;<span style="color: #999">(${rvo.dbday })</span>
						</td>
						<td class="text-right">
							<c:if test="${rvo.msg!='관리자가 삭제한 댓글입니다.' }">
								<c:if test="${sessionScope.id==rvo.id }">
									<span class="btn btn-xs btn-primary upBtn" data-no="${rvo.no }">수정</span>
									<a href="../freeboard/reply_delete.do?no=${rvo.no }&bno=${vo.no}" class="btn btn-xs btn-danger">삭제</a>
										<!-- detail로 다시 가야하니까 bno 보내줘야함 (rvo.no : 댓글번호, vo.no : 게시물번호) -->
								</c:if>
								<span class="btn btn-xs btn-success inBtn" data-no="${rvo.no }">댓글</span>
							</c:if>
						</td>
					</tr>	
					<tr>
						<td colspan="2" class="text-left" valign="top">
							<pre style="white-space: pre-wrap;">${rvo.msg }</pre> <!-- 칸이 넘어가면 자동줄바꿈 -->
						</td>
					</tr>
					<tr id="m${rvo.no }" style="display: none;" class="reply_update">
						<td colspan="2">
							<form method="post" action="../freeboard/reply_update.do">
								<input type="hidden" name="bno" value="${vo.no }"> <!-- 게시물번호 -->
								<input type="hidden" name="no" value="${rvo.no }"> <!-- 댓글번호 -->
								<textarea rows="5" cols="125" name="msg" style="float: left;">${rvo.msg }</textarea>
								<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float: left;" value="수정하기">
							</form>
						</td>	
					</tr>	
					<tr id="i${rvo.no }" style="display: none;" class="reply_insert">
						<td colspan="2">
							<form method="post" action="../freeboard/reply_reply_insert.do">
								<input type="hidden" name="bno" value="${vo.no }"> <!-- 게시물번호 -->
								<input type="hidden" name="pno" value="${rvo.no }"> <!-- 상위 댓글번호 -->
								<textarea rows="5" cols="125" name="msg" style="float: left;"></textarea>
								<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float: left;" value="댓글쓰기">
							</form>
						</td>	
					</tr>	
				</c:forEach>
				<tr>
					<td class="text-center" colspan="2">
						<a href="#" class="btn btn-xs btn-danger">이전</a>
						${curpage } page / ${totalpage } pages
						<a href="#" class="btn btn-xs btn-danger">다음</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td>
						<form method="post" action="../freeboard/reply_insert.do">
							<input type="hidden" name="bno" value="${vo.no }">
							<textarea rows="5" cols="125" name="msg" style="float: left;"></textarea>
							<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float: left;" value="댓글쓰기">
						</form>
					</td>	
				</tr>
			</table>
  		</div>
  	</div>
</div>
</body>
</html>