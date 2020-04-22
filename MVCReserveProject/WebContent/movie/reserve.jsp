<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 전체 기본 틀, 다 클릭했을때 출력해주는 부분 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'movie.do',
		success:function(res) {
			$('#movie-list').html(res);
		}
	})
	
	$.ajax({
		type:'post',
		url:'date.do',
		success:function(res) {
			$('#movie-date').html(res);
		}
	})
})
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">영화 예매</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" height="500">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">영화선택</td>
							</tr>
						</table>
						<div style="overflow-y: scroll; height: 450px;" id="movie-list"></div>
					</td>
					<td width="20%" height="500">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">극장선택</td>
							</tr>
						</table>
						<div style="overflow-y: scroll; height: 450px;" id="movie-theater"></div>
					</td>
					<td width="30%" height="500">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">날짜선택</td>
							</tr>
						</table>
						<div id="movie-date"></div>
					</td>
					<td width="20%" rowspan="2">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">예매정보</td>
							</tr>
						</table>
						<table class="table">
							<tr>
								<td class="text-center">
									<img src="def.png" width="250" height="300" id="movie-poster">
								</td>
							</tr>
							<tr>
								<td>
									<b id="movie-title"></b>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">별점</span>
									<span id="movie-score"></span>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">극장</span>
									<span id="movie-theater2"></span>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">날짜</span>
									<span id="movie-date2"></span>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">시간</span>
									<span id="movie-time2"></span>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">인원</span>
									<span id="movie-inwon2"></span>
								</td>
							</tr>
							<tr>
								<td>
									<span style="color: #999">금액</span>
									<span id="movie-price"></span>
								</td>
							</tr>
							<tr>
								<td class="text-center">
									<form method="post" action="reserve_ok.do">
									<%--
										RNO          NUMBER         : 자동증가   
										ID           VARCHAR2(20)   : session
										MNO          NUMBER         : 영화번호
										TNAME        VARCHAR2(100)  : 극장
										RDATE        VARCHAR2(100)  : 날짜
										RTIME        VARCHAR2(100)  : 시간
										RINWON       VARCHAR2(20)   : 인원
										RPRICE       VARCHAR2(20)   : 금액
										ISRESERVE    NUMBER         : 0 => 승인(1)
									 --%>
									 	<input type="hidden" name="mno" value="" id="mno"/>
									 	<input type="hidden" name="tname" value="" id="tname"/>
									 	<input type="hidden" name="rdate" value="" id="rdate"/>
									 	<input type="hidden" name="rtime" value="" id="rtime"/>
									 	<input type="hidden" name="rinwon" value="" id="rinwon"/>
									 	<input type="hidden" name="rprice" value="" id="rprice"/>
									 	
										<input type="submit" value="예매하기" class="btn btn-sm btn-danger" id="resBtn" disabled>
										<a href="mypage.do" class="btn btn-sm btn-primary">마이페이지</a>
									</form>
									
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="200">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">시간선택</td>
							</tr>
						</table>
						<div id="movie-time"></div>
					</td>
					<td width="20%" height="200">
						<table class="table">
							<tr>
								<td bgcolor="#ccccff" class="text-center">인원선택</td>
							</tr>
						</table>
						<div id="movie-inwon"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>