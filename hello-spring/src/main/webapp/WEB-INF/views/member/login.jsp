<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/member-spring.css">
</head>

<body>
	<div class="container">
		<h1>로그인</h1>
		<form:form modelAttribute="loginVO" method="post"
			action="/login?go=/write">
			<main class="main-text">
				<label for="email">이메일</label>
				<form:input path="email" id="email" placeholder="이메일을 입력하세요" />
				<form:errors path="email" cssClass="signerror" element="div" />

				<label for="password">비밀번호</label>
				<form:password path="password" id="password"
					placeholder="비밀번호를 입력하세요" />
				<form:errors path="password" cssClass="signerror" element="div" />

				<c:if test="${not empty errorMessage}">
					<div class="signerror">${errorMessage}</div>
				</c:if>

				<button type="submit">로그인</button>
				<a href="/regist" class="btn btn-secondary">회원가입</a>
			</main>
		</form:form>
	</div>
</body>

</html>