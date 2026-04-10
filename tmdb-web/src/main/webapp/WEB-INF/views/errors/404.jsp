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
        <h1>존재하지 않는 게시글</h1>
        <p>
            존재하지 않는 게시글입니다.<br>주소를 다시 확인하거나 아래 버튼을 눌러 홈으로 돌아가세요.
        </p>
        <a href="/" class="btn-home">홈으로 돌아가기</a>
    </div>

    <!-- 하단 -->
    <jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />
</body>
</html>