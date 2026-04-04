<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>영화 디테일</title>
            <link rel="stylesheet" type="text/css" href="/css/movie-view.css">
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>
            <!-- 상단 -->
            <header class="header">
                <div class="header-inner">
                    <!-- 로고 -->
                    <div class="logo">
                        <a href="/">
                            <img src="/imgs/log.png">
                        </a>
                    </div>

                    <!-- 메뉴 -->
                    <nav class="nav">
                        <a href="/">영화</a>
                        <a href="#">TV 프로그램</a>
                        <a href="#">인물</a>
                        <a href="#">Awards</a>
                        <a href="#">More</a>
                    </nav>

                    <!-- 오른쪽 -->
                    <div class="header-right">
                        <button class="icon-btn">
                            <span class="material-symbols-outlined">add</span>
                        </button>

                        <button class="lang-btn">KO</button>

                        <a href="/login" class="login-btn">로그인</a>
                        <a href="/sign" class="signup-btn">회원가입</a>

                        <button class="icon-btn">
                            <span class="material-symbols-outlined">search</span>
                        </button>
                    </div>
                </div>

                <!-- 검색창 -->
                <div class="header-search">
                    <div class="search-wrapper">
                        <span class="material-symbols-outlined search-icon">search</span>
                        <input type="text" placeholder="영화, TV 프로그램, 인물 검색">
                    </div>
                </div>
            </header>

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
            <footer class="footer">
                <div class="footer-container">
                    <div class="footer-logo">TMDB Clone</div>

                    <div class="footer-links">
                        <a href="#">About</a>
                        <a href="#">Contact</a>
                        <a href="#">API</a>
                        <a href="#">Privacy Policy</a>
                    </div>

                    <p class="copyright">
                        © 2026 TMDB Clone. All rights reserved.
                    </p>
                </div>
            </footer>

        </body>

        </html>