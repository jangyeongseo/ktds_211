<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- API / AJAX 를 위한 CSRF 토큰 가져오기 -->
<sec:csrfMetaTags />
<title>${param.title}</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
${param.script}
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
</head>

<body>
	<header>
		<h1>${param.title}</h1>
		<div class="header-links">
			<sec:authorize access="isAuthenticated()">
				<span class="member-info" data-email="<sec:authentication property='principal.email' />">
				관리자 <sec:authentication property="principal.name" />
				</span>
				<a href="/">뒤로가기</a>
				<a href="/member/view/<sec:authentication property='principal.email' />">마이페이지</a>
				<a href="/logout">로그아웃</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="/login">로그인</a>
				<a href="regist">회원가입</a>
			</sec:authorize>
		</div>
	</header>