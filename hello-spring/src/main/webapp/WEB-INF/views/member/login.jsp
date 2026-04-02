<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
        <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
        <script type="text/javascript" src="/js/members.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/member-spring.css">
    </head>

    <body>
        <div class="container">
            <h1>로그인</h1>
            <form method="get" action="/member">
                <main class="main-text">
                    <label for="email">이메일</label>
                    <input type="email" name="email" id="email" placeholder="이메일을 입력하세요" />

                    <label for="password">비밀번호</label>
                    <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" />

                    <button type="submit">로그인</button>
                </main>
            </form>
        </div>
    </body>

    </html>