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
	width: 500px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 		<input type="text" id="id"> ==> $('#id').val()
 		<td>aaa</td>                ==> $('td').text() => aaa
 		<td><span>aaa</span></td>   ==> $('td').html() => <span>aaa</span>
 									==> $('td').text() => aaa
 		<a href="aaa">              ==> $('a').attr("href") => aaa
 		
 		<input type="text" id="id"> ==> $('id').val('admin')
 		
 		$('td').append()
 */
// 메인함수 => 자바스크립트 쓸 때는 $(function(){}) 쓰고 시작
$(function() {
	$('#findBtn').click(function(){
		var dong=$('#dong').val(); // id="dong"에 입력된 값을 가져와라
		if(dong.trim()==""){
			$('#dong').focus();
			return;
		}
		//alert(dong);
		
		// ajax
		// 원래는 html을 읽으면 안되고 xml을 읽어서 데이터 가져와야한다 
		$.ajax({
			type:'POST',  // get/post 방식
			url:'../member/postfind_result.do', // 누구한테 값을 보낼건지
			data:{"dong":dong},  // json방식으로 값 보내기
			success:function(result){  // 정상수행 했을때
				var div=$('#result').html(result);  // 결과 값을 받아서 출력
			},
			error:function(e){ // 실패했을 때
				alert(e);
			}
		})
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력: <input type="text" id="dong" size="15" class="input-sm">
						     <input type="button" id="findBtn" class="btn btn-sm btn-danger" value="검색">
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<sub style="color: red;">※동/읍/면을 입력하세요.</sub>
					</td>
				</tr>
			</table>
			<div id="result">
				
			</div>
		</div>	
	</div>
</body>
</html>