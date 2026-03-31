<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 디테일</title>
<link rel="stylesheet" type="text/css" href="/css/movie-spring.css">
</head>
<body>
	<div class="container">
		<form action="/write" method="post">
			<h1>영화 디테일</h1>
			<div class="grid">
				<label>포스터 URL</label>
				<div>${articleMovieID.posterUrl}</div>

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
				<div>${articleMovieID.budget}</div>

				<label>수익</label>
				<div>${articleMovieID.profit}</div>
			</div>
		</form>
	</div>
</body>
</html>