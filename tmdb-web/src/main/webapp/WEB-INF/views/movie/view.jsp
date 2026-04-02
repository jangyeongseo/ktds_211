<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 디테일</title>
<link rel="stylesheet" type="text/css" href="/css/movie-spring.css">
</head>
<body>
	<div class="container">
            <div class="header">
                <h1>영화 디테일</h1>
                <a href="/list" class="btn">&lt</a>
            </div>
			<div class="grid">
				<label>포스터 URL</label>
				<!-- 영화 내용 조회할 때 업로드 한 포스터가 이미지로 노출될 수 있도록 개선 -->
				<div>
                    <ul class="vertical-list">
					   <c:forEach items="${articleMovieID.files}" var="file">
						    <li>
							    <a href="/file/${file.fileGroupId}/${file.fileNum}">
							        <img src="/file/${file.fileGroupId}/${file.fileNum}" alt="${file.displayName}" width="200"/>
							    </a>
						    </li>
						</c:forEach>
					</ul>
                </div>

				<label>제목</label>
				<div>${articleMovieID.title}</div>

				<label>등급</label>
				<div>${articleMovieID.movieRating}</div>

				<label>개봉일</label>
				<div>${articleMovieID.openDate}</div>

				<label>개봉 국가</label>
				<div>${articleMovieID.openCountry}</div>

				<label>상영 시간</label>
				<div>${articleMovieID.runningTime}분</div>

				<label class="introduction">소개</label>
				<div>${articleMovieID.introduce}</div>

				<label class="plot">줄거리</label>
				<div>${articleMovieID.synopsis}</div>

				<label>원제</label>
				<div>${articleMovieID.originalTitle}</div>

				<label>상태</label>
				<div>${articleMovieID.state}</div>

				<label>언어</label>
				<div>${articleMovieID.language}</div>

				<label>제작비</label>
				<div class="money">${articleMovieID.budget}</div>

				<label>수익</label>
				<div class="money">${articleMovieID.profit}</div>
			</div>
			
			<div class="btnList">
				<a href="/update/${articleMovieID.movieId}" class="btn">수정</a>
				<a href="/delect?articleMovieID=${articleMovieID}" class="btn">삭제</a>
			</div>
	</div>
</body>
</html>