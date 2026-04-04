<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>영화 등록</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/movies.js"></script>
            <link rel="stylesheet" type="text/css" href="/css/movie-write.css">
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

            <main class="write-page">
                <div class="write-container">
                    <div class="write-header">
                        <h1>영화 등록</h1>
                        <a href="/" class="back-btn">&lt</a>
                    </div>

                    <form:form modelAttribute="movieWriteVO" action="/write" method="post"
                        enctype="multipart/form-data">
                        <div class="form-grid">
                            <!-- 포스터 -->
                            <div class="poster-upload">
                                <label>포스터</label>
                                <input type="file" name="attachFile" />
                            </div>

                            <!-- 오른쪽 입력 -->
                            <div class="form-fields">
                                <input type="text" id="title" name="title" placeholder="영화 제목" />
                                <form:errors path="title" cssClass="movieError" />

                                <input type="text" id="movieRating" name="movieRating" placeholder="등급 (예: 15)" />
                                <form:errors path="movieRating" cssClass="movieError" />

                                <input type="date" id="openDate" name="openDate" />

                                <input type="text" id="openCountry" name="openCountry" placeholder="국가 (KR)" />

                                <input type="number" id="runningTime" name="runningTime" placeholder="상영 시간 (분)" />

                                <textarea name="introduce" id="introduce" placeholder="영화 소개"></textarea>

                                <textarea name="synopsis" id="synopsis" placeholder="줄거리"></textarea>
                                <form:errors path="synopsis" cssClass="movieError" />

                                <input type="text" id="originalTitle" name="originalTitle" placeholder="원제" />

                                <input type="text" id="state" name="state" placeholder="상태 (Released)" />
                                <form:errors path="state" cssClass="movieError" />

                                <input type="text" id="language" name="language" placeholder="언어 (KO)" />
                                <form:errors path="language" cssClass="movieError" />

                                <div class="money-row">
                                    <input type="number" id="budget" name="budget" placeholder="제작비" />
                                    <input type="number" id="profit" name="profit" placeholder="수익" />
                                </div>
                            </div>
                        </div>

                        <div class="submit-area">
                            <input type="submit" value="등록하기" />
                        </div>
                    </form:form>
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