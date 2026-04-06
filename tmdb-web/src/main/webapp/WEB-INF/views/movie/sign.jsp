<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>회원가입</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/member.js"></script>
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

                        <button class="icon-btn">
                            <span class="material-symbols-outlined">search</span>
                        </button>
                    </div>
                </div>
            </header>

            <main class="login-page">
                <form:form modelAttribute="memberWriteVO" action="/sign" method="post">
                    <div class="login-container">
                        <h2>회원가입</h2>
                        <input type="email" name="email" id="email" placeholder="이메일" value="${memberWriteVO.email}" />
                        <form:errors path="email" cssClass="memberError" element="div" />

                        <input type="password" name="password" id="password" placeholder="비밀번호"
                            value="${memberWriteVO.password}" />
                        <form:errors path="password" cssClass="memberError" element="div" />

                        <input type="password" name="confirmPassword" id="confirmPassword" placeholder="비밀번호 확인"
                            value="${memberWriteVO.confirmPassword}" />
                        <form:errors path="confirmPassword" cssClass="memberError" element="div" />

                        <button type="submit">회원가입</button>
                        <p>이미 계정이 있으신가요? <a href="/login">로그인</a></p>
                    </div>
                </form:form>
            </main>

            <!-- 하단 -->
            <!--         <footer class="footer">
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