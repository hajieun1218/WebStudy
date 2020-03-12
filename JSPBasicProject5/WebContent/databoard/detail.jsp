<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String no=request.getParameter("no");
	String strPage=request.getParameter("page");

	// no => DAO로 전송
	FileBoardDAO dao=new FileBoardDAO();
	// DAO에서 요청한 VO를 받아온다
	FileBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
	// VO에 저장된 데이터를 HTML을 이용해서 출력 -> body에서
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.row {
	margin: 0px auto;
	width: 600px;
}
h2 {
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> <!-- import -->
<script type="text/javascript">
//<a>bbb</a>  ==> $('a').text()      => getter
// <a></a>     ==> $('a').text("bbb") => setter
// <a href="kkk" id="k"></a>  ==> $('#k').attr("href")
// <input type="text" id="p" value="kkk"/>  ==> $('#p').val()
//											==> $('#p').val("ddd")
// <td><span>ooo</span></td>  ==> $('td span').text() ==> ooo
//							  ==> $('td').text() ==> ooo
// 							  ==> $('td').html() ==> <span>ooo</span>
var i=0;
$(function(){  // 화면이 떠있는 상태 window.onload
	// $('#delBtn') =======> document.getElementById("delBtn");
	$('#delBtn').click(function(){  // 버튼을 클릭했다면 
		if(i==0) {
			$('#delBtn').val("취소");
			$('#del').show();
			i=1;
		}
		else {
			$('#delBtn').val("삭제");
			$('#del').hide();
			i=0;	
		}
	});
	
	$('#sendBtn').click(function(){
		var pwd=$('#pwd').val();
		var no=$('#no').val();
		var page=$('#page').val();
		if(pwd=="") {
			$("#pwd").focus();
			return;
		}
		
		// Ajax
		// 데이터를 보내고 delete.jsp에서 db연결 후 결과값을 가져와서 화면이 깜빡거리지 않음
		$.ajax({
			type:'POST', // 전송방식(get,post)
			url:'delete.jsp', // 데이터를 어디로 보낼건지
			data:{"no":no,"pwd":pwd},  // 변수명:값   (delete.jsp?no=1&pwd=1234)
			success:function(res) {  // 정상수행을 했다면 => 200 / error=> 404,500
				// alert(res);
				var result=res.trim();
				if(result==0) {
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else {
					location.href="list.jsp?page="+page;  // 화면이동 (response.sendRedirect(list.jsp?page=2))
				}
			}
		});
	});
});
</script>
</head>
<body>
	<div class="container">
		<h2>내용보기</h2>
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th width="20%" class="text-center success">번호</th>
					<td width="30%" class="text-center"><%=vo.getNo() %></td>
					<th width="20%" class="text-center success">작성일</th>
					<td width="30%" class="text-center"><%=vo.getRegdate() %></td>
				</tr>
				<tr>
					<th width="20%" class="text-center success">이름</th>
					<td width="30%" class="text-center"><%=vo.getName() %></td>
					<th width="20%" class="text-center success">조회수</th>
					<td width="30%" class="text-center"><%=vo.getHit() %></td>
				</tr>
				<tr>
					<th width="20%" class="text-center success">제목</th>
					<td colspan="3" class="text-left"><%=vo.getSubject() %></td>
				</tr>
				<%
					// 업로드된 파일이 있을때만 출력
					if(vo.getFilesize()!=0) {
				%>
						<tr>
							<th width="20%" class="text-center success">첨부파일</th>
							<td colspan="3" class="text-left">
								<a href="download.jsp?fn=<%=vo.getFilename() %>"><%=vo.getFilename() %></a>
								(<%=vo.getFilesize() %>Bytes)
							</td>
						</tr>
				<%
					}
				%>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap; background-color: white; border: none"><%=vo.getContent() %></pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="reply.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-success">답변</a>
						<a href="update.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-primary">수정</a>
						<input type="button" class="btn btn-xs btn-danger" id="delBtn" value="삭제"/>
						<a href="list.jsp" class="btn btn-xs btn-info">목록</a> <!-- back을 하면 조회수가 안맞음 -->
					</td>
				</tr>
				<tr id="del" style="display: none;">
					<td colspan="4" class="text-right">
					비밀번호 : <input type="password" id="pwd" size=10 class="input-sm"/>
							<input type="hidden" id="no" value="<%=no %>"/>
							<input type="hidden" id="page" value="<%=strPage %>"/>
							<input type="button" value="삭제" class="btn btn-sm btn-danger" id="sendBtn"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>