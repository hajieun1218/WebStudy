<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
	margin: 0px auto;
	width: 350px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#id').focus();
	$('#checkBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			return;
		}
		
		// MemberModel에 id 전송
		// data{"id":"admin","pwd":"1234"} => idcheck_result.do?id=admin&pwd=1234
		// success: 200,  error: 404,500
		/* 시스템에 의해 자동 호출되는 함수 ==> callback 함수
			1) AJAX
				XMLHttpRequest request => 얻어온다 (각부라우저에 내장)
			2) 연결
				request.open('POST|GET','../member/idcheck_result.do',true)
																	  ===== 비동기화/동기화
			3) 결과값을 가지고 오는 자동 호출 함수를 제작
				request.onreadystatechange=sendMessage
			4) 전송할 데이터 설정
				request.send(id=admin&pwd=1234)
			
				
			function sendMessage() {
				0 => open전 => 연결중
				1 => open후 => 연결완료
				2 => send준비
				3 => send완료
				4 => 정상연결, send완료 확인
				if(request.readyState==4) {
					if(request.status==200) {  // success
						
					}
					else {  // error
						
					}
				}
			}
			
			데이터 형태(result) : JSON, XML, HTML
		*/
		$.ajax({
			type:'POST',
			url:'../member/idcheck_result.do',
			data:{"id":id},
			success:function(result){ // result : idcheck_result.jsp의 내용 읽어옴(0,1)
				let count=parseInt(result.trim());  // parseInt 대신 Number 가능 (정수로 변환)
				if(count==0) { // 사용가능한 아이디
					let msg='<font color="blue"><b>'+id+'는(은) 사용 가능한 아이디입니다.</b></font>';
					$('#result').html(msg);
					$('#ok').html(
						'<input type="button" value="확인" class="btn btn-sm btn-success" onclick=ok()>'
					);
				}
				else { // 이미 사용중인 아이디
					let msg='<font color="red"><b>'+id+'는(은) 이미 사용중인 아이디입니다.</b></font>';
					$('#result').html(msg);
				}
			},
			error:function(e){
				alert(e);
			}
		})
		// 결과값을 읽어서 처리
	})
});

function ok() {
	let id=$('#id').val();
	parent.frm.id.value=id;  // join.jsp의 id에 값을 채워라
	parent.Shadowbox.close();  // join에서 Shadowbox open했으니까 join이 parent
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력: <input type="text" id="id" class="input-sm" size="15">
							 <input type="button" value="아이디체크" class="btn btn-sm btn-primary" id="checkBtn">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<span id="result"></span>
					</td>
				</tr>
				<tr>
					<td class="text-center" id="ok"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>