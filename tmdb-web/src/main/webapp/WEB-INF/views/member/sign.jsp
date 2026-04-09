<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>회원가입</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/member.js"></script>
            <link rel="stylesheet" type="text/css" href="/css/movie-sign.css">
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>
            <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
                <jsp:param name="showSearch" value="false"/>
            </jsp:include>

            <main class="sign-page">
                <form:form modelAttribute="memberWriteVO" action="/sign" method="post">
				    <div class="sign-container">
				        <h2>회원가입</h2>
				
				        <form:input path="name" id="name" placeholder="닉네임" />
				        <form:errors path="name" cssClass="memberError" element="div" />
				
				        <form:input path="email" id="email" placeholder="이메일" />
				        <form:errors path="email" cssClass="memberError" element="div" />
				
				        <div class="password-wrapper">
				            <form:password path="password" id="password" placeholder="비밀번호" />
				            <button type="button" class="toggle-password" data-target="password">
				                <span class="material-symbols-outlined">visibility</span>
				            </button>
				        </div>
				        <form:errors path="password" cssClass="memberError" element="div" />
				
				        <!-- 전체 에러 -->
				        <%-- <form:errors path="*" cssClass="memberError" element="div" /> --%>
				
				        <form:password path="confirmPassword" id="confirmPassword" placeholder="비밀번호 확인" />
				        <form:errors path="confirmPassword" cssClass="memberError" element="div" />
				
				        <button type="submit">회원가입</button>
				        <p>이미 계정이 있으신가요? <a href="/login">로그인</a></p>
				    </div>
				</form:form>
            </main>
        </body>

        </html>