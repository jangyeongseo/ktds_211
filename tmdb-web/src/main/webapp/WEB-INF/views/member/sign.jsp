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
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>
            <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
                <jsp:param name="showSearch" value="false"/>
            </jsp:include>

            <main class="login-page">
                <form:form modelAttribute="memberWriteVO" action="/sign" method="post">
                    <div class="login-container">
                        <h2>회원가입</h2>
                        <input type="text" name="name" id="name" placeholder="닉네임" value="${memberWriteVO.name}"
                            required />
                        <form:errors path="name" cssClass="memberError" element="div" />
                        
                        <input type="email" name="email" id="email" placeholder="이메일" value="${memberWriteVO.email}"
                            required />
                        <form:errors path="email" cssClass="memberError" element="div" />

                        <input type="password" name="password" id="password" placeholder="비밀번호"
                            value="${memberWriteVO.password}" required />
                        <form:errors path="password" cssClass="memberError" element="div" />
                        
                        <!-- 비밀번호 보여주는 클릭 버튼 -->

                        <input type="password" name="confirmPassword" id="confirmPassword" placeholder="비밀번호 확인"
                            value="${memberWriteVO.confirmPassword}" required />
                        <form:errors path="confirmPassword" cssClass="memberError" element="div" />

                        <button type="submit">회원가입</button>
                        <p>이미 계정이 있으신가요? <a href="/login">로그인</a></p>
                    </div>
                </form:form>
            </main>
        </body>

        </html>