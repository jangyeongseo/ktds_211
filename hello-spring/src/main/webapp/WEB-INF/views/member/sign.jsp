<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>회원가입</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/members.js"></script>

            <link rel="stylesheet" type="text/css" href="/css/member-spring.css">
        </head>

        <body>
            <div class="container">
                <h1>회원가입</h1>
                <form:form modelAttribute="writeVO" method="post" action="/regist">
                    <main class="main-text">
                        <label for="email">이메일</label>
                        <input type="email" name="email" id="email" placeholder="이메일을 입력하세요"
                            value="${inputModel.email}" />
                        <form:errors path="email" cssClass="signerror" element="div" />

                        <label for="name">이름</label>
                        <input type="text" name="name" id="name" placeholder="성함을 입력하세요" value="${inputModel.name}" />
                        <form:errors path="name" cssClass="signerror" element="div" />

                        <label for="password">비밀번호</label>
                        <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" />
                        <form:errors path="password" cssClass="signerror" element="div" />

                        <button type="submit">회원가입</button>
                    </main>
                </form:form>
            </div>
        </body>

        </html>