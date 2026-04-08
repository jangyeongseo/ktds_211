<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<!-- 상단 -->
	<jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
		<jsp:param name="showSearch" value="false" />
	</jsp:include>

	<main>
		<h1>마이페이지</h1>
		<div>
			<!-- 회원 정보 -->
		</div>
		<div>
			<a href="/member/update/${articleEmail}"></a> <a
				href="/member/delete"></a>
		</div>
	</main>

	<!-- 하단 -->
	<jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />
</body>
</html>