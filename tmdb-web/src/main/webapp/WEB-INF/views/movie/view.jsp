<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>영화 디테일</title>
            <link rel="stylesheet" type="text/css" href="/css/movie-view.css">
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
            <link rel="stylesheet" type="text/css" href="/css/footer.css" />
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>
            <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp" />

            <main class="movie-detail">
                <!-- 배경 -->
                <div class="backdrop">
                    <div class="overlay">
                        <div class="detail-inner">

                            <!-- 포스터 -->
                            <div class="poster">
                                <c:forEach items="${articleMovieID.files}" var="file" begin="0" end="0">
                                    <img src="/file/${file.fileGroupId}/${file.fileNum}" />
                                </c:forEach>
                            </div>

                            <!-- 정보 -->
                            <div class="info">
                                <h1>${articleMovieID.title}</h1>

                                <p class="meta">
                                    ${articleMovieID.openDate} ·
                                    ${articleMovieID.openCountry} ·
                                    ${articleMovieID.runningTime}분
                                </p>

                                <p class="tag">${articleMovieID.movieRating}</p>

                                <h3>개요</h3>
                                <p class="overview">${articleMovieID.introduce}</p>

                                <h3>줄거리</h3>
                                <p class="overview">${articleMovieID.synopsis}</p>

                                <div class="extra">
                                    <p><b>원제:</b> ${articleMovieID.originalTitle}</p>
                                    <p><b>언어:</b> ${articleMovieID.language}</p>
                                    <p><b>상태:</b> ${articleMovieID.state}</p>
                                    <p><b>제작비:</b> ₩${articleMovieID.budget}</p>
                                    <p><b>수익:</b> ₩${articleMovieID.profit}</p>
                                </div>

                                <div class="btnList">
                                    <a href="/update/${articleMovieID.movieId}" class="btn">수정</a>
                                    <a href="/delete?movieId=${articleMovieID.movieId}" class="btn">삭제</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </main>

            <!-- 하단 -->
            <jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />

        </body>

        </html>