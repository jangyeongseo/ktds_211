<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>회원 정보 수정</title>
        <link rel="stylesheet" type="text/css" href="/css/member-spring.css">
    </head>

    <body>
        <div class="container">
            <h1>회원 정보 수정</h1>
            <form method="post" action="/member/update/${articleEmail}">
                <main class="main-text">
                    <label for="email">이메일</label>
                    <input type="email" name="email" id="email" placeholder="이메일을 입력하세요" value="${article.email}" />

                    <label for="name">이름</label>
                    <input type="text" name="name" id="name" placeholder="성함을 입력하세요" value="${article.name}" />

                    <label for="password">비밀번호</label>
                    <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" value="${article.password}" />

                    <button type="submit">저장</button>
                </main>
            </form>
        </div>
    </body>

    </html>