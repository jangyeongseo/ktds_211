<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
        <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
        <script type="text/javascript" src="/js/member.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/movie-login.css">
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
        <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
    </head>

    <body>
         <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
            <jsp:param name="showSearch" value="false"/>
         </jsp:include>

        <main class="login-page">
            <div class="login-container">
                <h2>로그인</h2>
                <form action="/login" method="post">
                    <input type="email" name="email" placeholder="이메일" id="email" required>
                    <input type="password" name="password" placeholder="비밀번호" required>
                    <button type="submit">로그인</button>
                </form>
                <p>계정이 없으신가요? <a href="/sign">회원가입</a></p>
            </div>
        </main>
    </body>

    </html>