<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
        <link rel="stylesheet" type="text/css" href="/css/movie-login.css">
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

                    <%-- <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <span>${sessionScope.user}</span>
                            <a href="/logout">로그아웃</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login">로그인</a>
                            <a href="/sign">회원가입</a>
                        </c:otherwise>
                        </c:choose> --%>

                        <button class="icon-btn">
                            <span class="material-symbols-outlined">search</span>
                        </button>
                </div>
            </div>
        </header>

        <main class="login-page">
            <div class="login-container">
                <h2>로그인</h2>
                <form action="/login" method="post">
                    <input type="text" name="username" placeholder="아이디" required>
                    <input type="password" name="password" placeholder="비밀번호" required>
                    <button type="submit">로그인</button>
                </form>
                <p>계정이 없으신가요? <a href="/sign">회원가입</a></p>
            </div>
        </main>

        <!-- 하단 -->
        <!-- <footer class="footer">
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
        </footer> -->
    </body>

    </html>