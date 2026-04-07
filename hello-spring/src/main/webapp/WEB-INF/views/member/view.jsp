<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>마이페이지</title>
        <link rel="stylesheet" type="text/css" href="/css/member-spring.css">
    </head>

    <body>
        <div class="container">
		        <div>
		            <h1>마이페이지</h1>
		            <a href="/">뒤로 가기</a>
		        </div>
                <main class="main-text">
                    <label for="email">이메일</label>
                    <div>${member.email}</div>

                    <label for="name">이름</label>
                    <div>${member.name}</div>

                    <label for="password">비밀번호</label>
                    <div>${member.password}</div>
                    
                    <div class="btn-group">
		                <div class="right-align">
		                    <a href="/member/update/${member.email}">수정</a> 
		                    <a href="/member/delete?email=${member.email}">삭제</a>
		                    <a href="//delete-me">탈퇴하기</a>
		                </div>
		            </div>
                </main>
        </div>
    </body>

    </html>