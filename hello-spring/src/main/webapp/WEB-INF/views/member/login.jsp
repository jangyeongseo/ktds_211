<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>로그인</title>
			<link rel="stylesheet" type="text/css" href="/css/member-spring.css">
		</head>

		<body>
			<div class="container">
				<h1>로그인</h1>
				<form:form modelAttribute="loginVO" method="post" action="/login">
					<main class="main-text">
						<label for="email">이메일</label>
						<form:input path="email" id="email" placeholder="이메일을 입력하세요" />
						<form:errors path="email" cssClass="signerror" element="div" />

						<label for="password">비밀번호</label>
						<form:password path="password" id="password" placeholder="비밀번호를 입력하세요" />
						<form:errors path="password" cssClass="signerror" element="div" />

						<button type="submit">로그인</button>
					</main>
				</form:form>
			</div>
		</body>

		</html>