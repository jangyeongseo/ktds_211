<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>영화 등록</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/movies.js"></script>
            <link rel="stylesheet" type="text/css" href="/css/movie-write.css">
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
            <link rel="stylesheet" type="text/css" href="/css/footer.css" />
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>

            <!-- 상단 -->
            <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp" />
            
            <main class="write-page">
                <div class="write-container">
                    <div class="write-header">
                        <h1>영화 등록</h1>
                        <a href="/" class="back-btn">&lt</a>
                    </div>

                    <form:form modelAttribute="movieWriteVO" action="/write" method="post"
                        enctype="multipart/form-data">
                        <div class="form-grid">
                            <!-- 포스터 -->
                            <div class="poster-upload">
                                <label>포스터</label>
                                <input type="file" name="attachFile" />
                            </div>

                            <!-- 오른쪽 입력 -->
                            <div class="form-fields">
                                <input type="text" id="title" name="title" placeholder="영화 제목" />
                                <form:errors path="title" cssClass="movieError" />

                                <input type="text" id="movieRating" name="movieRating" placeholder="등급 (예: 15)" />
                                <form:errors path="movieRating" cssClass="movieError" />

                                <input type="date" id="openDate" name="openDate" />

                                <input type="text" id="openCountry" name="openCountry" placeholder="국가 (KR)" />

                                <input type="number" id="runningTime" name="runningTime" placeholder="상영 시간 (분)" />

                                <textarea name="introduce" id="introduce" placeholder="영화 소개"></textarea>

                                <textarea name="synopsis" id="synopsis" placeholder="줄거리"></textarea>
                                <form:errors path="synopsis" cssClass="movieError" />

                                <input type="text" id="originalTitle" name="originalTitle" placeholder="원제" />

                                <input type="text" id="state" name="state" placeholder="상태 (Released)" />
                                <form:errors path="state" cssClass="movieError" />

                                <input type="text" id="language" name="language" placeholder="언어 (KO)" />
                                <form:errors path="language" cssClass="movieError" />

                                <div class="money-row">
                                    <input type="number" id="budget" name="budget" placeholder="제작비" />
                                    <input type="number" id="profit" name="profit" placeholder="수익" />
                                </div>
                            </div>
                        </div>

                        <div class="submit-area">
                            <input type="submit" value="등록하기" />
                        </div>
                    </form:form>
                </div>
            </main>
            
            <!-- 하단 -->
            <jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />
        </body>

        </html>