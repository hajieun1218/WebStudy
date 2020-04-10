<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
	margin: 0px auto;
	width: 600px;
}
h2 {
	text-align: center;
}
th{
	color: black;
}
td{
	background-color: white;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#pwd2').keyup(function(){
		var no=$('#no').val();
		var pwd=$(this).val();
		console.log(pwd)
		
		$.ajax({
			type:'POST',
			url:'../reply/password_check.do',
			data:{"no":no,"pwd":pwd},
			success:function(res){
				var check=res.trim();
				if(check==1) {
					$('#result').html("<font color=blue>비밀번호 일치</font>");
					$('#updateBtn').attr('disabled',false);
				}
				else {
					$('#result').html("<font color=red>비밀번호 불일치</font>");
					$('#updateBtn').attr('disabled',true);
				}
			}
		})
	});
});

</script>
</head>
<body>
<div class="wrapper row2">
	<h2>수정하기</h2>
  	<div id="services" class="clear"> 
  		<form method="post" action="../reply/update_ok.do">
			<table class="table table-hover">
				<tr>
					<th width="20%" class="text-right success">이름</th>
					<td width="80%">
						<input type="text" name="name" id="name" size="15" value="${vo.name }" required/>
						<input type="hidden" name="no" id="no" value="${vo.no }">
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">제목</th>
					<td width="80%">
						<input type="text" name="subject" id="subject" size="50" value="${vo.subject }" required/>
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">내용</th>
					<td width="80%">
						<textarea rows="8" cols="55" name="content" id="content" required>${vo.content }</textarea>
					</td>
				</tr>
				
				<tr>
					<th width="20%" class="text-right success">비밀번호</th>
					<td width="80%">
						<input type="password" name="pwd" id="pwd2" size="10" required/> <!-- 로그인 pwd랑 겹치니까 pwd2로! -->
						<div id="result"></div>
					</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan="2">
						<input type="submit" value="수정하기" class="btn btn-sm btn-primary" id="updateBtn" disabled/>
						<input type="button" value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>