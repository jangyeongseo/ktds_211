<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>회원 정보</title>
        <link rel="stylesheet" type="text/css" href="/css/member-spring.css">
    </head>

    <body>
        <div class="container">
            <h1>회원정보</h1>
                <main class="main-text">
                    <label for="email">이메일</label>
                    <div>${articleEmail.email}</div>=

                    <label for="name">이름</label>
                    <div>${articleEmail.name}</div>

                    <label for="password">비밀번호</label>
                    <div>${articleEmail.password}</div>
                    
                    <div class="btn-group">
		                <div class="right-align">
		                    <a href="/member/update/${articleEmail.id}">수정</a> 
		                    <a href="/member/delete?id=${articleEmail.id}">삭제</a>
		                </div>
		            </div>
                </main>
        </div>
    </body>

    </html>