<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>${param.title}</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
${param.script}
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
</head>

<body>
	<header>
		<h1>${param.title}</h1>
		<div class="header-links">
			<c:choose>
				<c:when test="${not empty sessionScope.__LOGIN_DATA__}">
					<span>관리자 ${sessionScope.__LOGIN_DATA__.email}</span>
					<a href="/">뒤로가기</a>
					<a href="/member/view/${sessionScope.__LOGIN_DATA__.email}">마이페이지</a>
					<a href="/logout">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="/login">로그인</a>
					<a href="/sign">회원가입</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>