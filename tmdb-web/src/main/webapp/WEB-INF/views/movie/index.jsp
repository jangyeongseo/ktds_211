<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 목록</title>
<link rel="stylesheet" type="text/css" href="/css/index-spring.css">
</head>
<body>
	<div class="container">

		<!-- 헤더 -->
		<div class="header">
			<h1>영화 목록</h1>
			<a href="/write" class="btn">등록</a>
		</div>

		<!-- 영화 리스트 -->
		<div class="grid">
			<c:forEach items="${movieList}" var="movie">
				<div class="card">
					<img src="${movie.posterUrl}" alt="poster">
					<div class="card-body">
						<h3>${movie.title}</h3>
						<p class="info">${movie.openDate}</p>
					</div>
				</div>
			</c:forEach>

			<!-- 데이터 없을 때 -->
			<c:if test="${empty movieList}">
				<p class="empty">등록된 영화가 없습니다.</p>
			</c:if>
		</div>
	</div>

</body>
</html>