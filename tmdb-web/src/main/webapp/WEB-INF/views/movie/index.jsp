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

        <!-- 영화 리스트 그리드 -->
        <div class="grid">
            <c:forEach items="${movieList}" var="movie">
                <div class="card">
                    <a href="/view/${movie.movieId}">
                        <!-- 포스터 이미지: 영화에 첨부된 첫 번째 파일 표시 -->
                        <c:choose>
						    <c:when test="${not empty movie.files}">
						        <c:forEach items="${movie.files}" var="file" begin="0" end="0">
						            <img src="/file/${file.fileGroupId}/${file.fileNum}" alt="${file.displayName}" />
						        </c:forEach>
						    </c:when>
						    <c:otherwise>
						        <div class="default-poster"></div>
						    </c:otherwise>
						</c:choose>

                        <!-- 카드 내용 -->
                        <div class="card-body">
                            <h3>${movie.title}</h3>
                            <p class="info">${movie.openDate}</p>
                        </div>
                    </a>
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