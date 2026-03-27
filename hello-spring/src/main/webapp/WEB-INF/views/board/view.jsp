<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
    </head>

    <body>
        <div class="container">
            <h1>게시글 내용 조회</h1>
            <div class="grid view">
                <span>아이디</span>
                <div>${articleId.id}</div>

                <span>제목</span>
                <div>${articleId.subject}</div>

                <span>조회수</span>
                <div>${articleId.viewCnt}</div>

                <span>작성일</span>
                <div>${articleId.crtDt}</div>

                <span>마지막 수정일</span>
                <div>${articleId.mdfyDt}</div>

                <span>첨부파일</span>
                <div>${articleId.fileName}</div>

                <span>내용</span>
                <div>${articleId.content}</div>

                <div class="btn-group">
                    <div class="right-align">
                        <a href="/delete?id=${articleId.id}">삭제</a>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>