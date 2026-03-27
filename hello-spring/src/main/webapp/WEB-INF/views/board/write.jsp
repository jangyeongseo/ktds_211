<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
</head>

<body>
	<h1>게시글 작성</h1>
	<!-- action => form 내부의 value를 전송할 엔드포인트 -->
	<!-- post는 내용의 값응ㄹ 안정했기 때문에 많은 용량을 보낼 경우 post가 좋고 적은 내용일 경우 get이 좋다. -->
	<!-- url 혹은 링크 일경우 get으로 연결한다 -->
	<form method="post" action="/write">
		<div class="grid write">
			<label for="subject">제목</label> <input type="text" id="subject"
				name="subject" /> <label for="email">이메일</label> <input
				type="email" id="email" name="email" /> <label for="content">내용</label>
			<textarea id="content" name="content"></textarea>

			<div class="btn-group">
				<div class="right-align">
					<input type="submit" value="저장" />
				</div>
			</div>

		</div>
	</form>
</body>

</html>