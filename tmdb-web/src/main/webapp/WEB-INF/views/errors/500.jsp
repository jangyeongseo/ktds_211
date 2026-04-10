<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/header.css" />
<link rel="stylesheet" type="text/css" href="/css/error.css" />
<link rel="stylesheet" type="text/css" href="/css/footer.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
		<jsp:param name="showSearch" value="true" />
	</jsp:include>


	<div class="error-container">
		<i class="fas fa-exclamation-triangle error-icon"></i>
		<h1>시스템 에러가 발생</h1>
		<p>
			잠시후 다시 시도해주세요.<br>시스템 관리자는 에러 내용을 확인해주세요.
        </p>
        <p>다시 시작하여도 문제가 생긴다면 밑에 주소로 연락주세요.</p>
        <p>test@naver.com</p>
		
        <a href="/" class="btn-home">홈으로 돌아가기</a>
    </div>

    <!-- 하단 -->
    <jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />
</body>
</html>