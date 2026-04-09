<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
<link rel="stylesheet" type="text/css" href="/css/movie-login.css">
<link rel="stylesheet" type="text/css" href="/css/header.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
	rel="stylesheet" />
</head>

<body>
	<jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
		<jsp:param name="showSearch" value="false" />
	</jsp:include>

	<main class="login-page">
		<div class="login-container">
			<h2>로그인</h2>
			<form:form id="loginVO" modelAttribute="loginVO" action="/login" method="post">
				<form:input path="email" id="email" placeholder="이메일" />
				<form:errors path="email" cssClass="signerror" element="div" />

				<form:password path="password" id="password" placeholder="비밀번호" />
				<form:errors path="password" cssClass="signerror" element="div" />
				<button type="submit">로그인</button>
			</form:form>
			<p>
				계정이 없으신가요? <a href="/sign">회원가입</a>
			</p>
		</div>
	</main>
</body>

</html>