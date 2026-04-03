<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>영화 등록</title>
            <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
            <script type="text/javascript" src="/js/movies.js"></script>
            <link rel="stylesheet" type="text/css" href="/css/movie-spring.css">
        </head>

        <body>
            <div class="container">
                <form:form methodParam="movieWriteVO" action="/write" method="post" enctype="multipart/form-data">
                    <div class="header">
                        <h1>영화 등록</h1>
                        <a href="/list" class="btn">&lt</a>
                    </div>
                    <div class="grid">
                        <!-- tmdb 프로젝트에서 영화를 등록할 때, 포스터 한 장을 업로드 할 수 있도록 개선. -->
                        <label>포스터 URL</label>
                        <input type="file" name="attachFile" />

                        <label>제목</label>
                        <input type="text" name="title" placeholder="영화 제목을 등록하세요." />
                        <form:errors path="title" cssClass="movieError" element="div" />

                        <label>등급</label>
                        <input type="text" maxlength="3" name="movieRating" placeholder="영화 관람 가능한 나이를 등록하세요." />
                        <form:errors path="movieRating" cssClass="movieError" element="div" />

                        <label>개봉일</label>
                        <input type="date" min="2026-04-02" max="2027-12-31" name="openDate" />

                        <label>개봉 국가</label>
                        <input type="text" maxlength="2" name="openCountry" placeholder="영화가 개봉하는 국가를 등록하세요." />

                        <label>상영 시간</label>
                        <input type="number" min="1" max="300" name="runningTime" placeholder="영화 상영 시간을 등록하세요." />

                        <label class="introduction">소개</label>
                        <textarea name="introduce" placeholder="영화에 관한 간단한 소객를 등록하세요."></textarea>

                        <label class="plot">줄거리</label>
                        <textarea id="synopsis" name="synopsis" placeholder="영화의 줄거리를 등록하세요."></textarea>
                        <form:errors path="synopsis" cssClass="movieError" element="div" />

                        <label>원제</label>
                        <input type="text" name="originalTitle" placeholder="영화의 실제 제목을 등록하세요." />

                        <label>상태</label>
                        <input type="text" id="state" name="state" placeholder="영화의 개봉 상태를 등록하세요." />
                        <form:errors path="state" cssClass="movieError" element="div" />

                        <label>언어</label>
                        <input type="text" id="language" name="language" placeholder="영화의 언어를 등록하세요." />
                        <form:errors path="language" cssClass="movieError" element="div" />

                        <label>제작비</label>
                        <input class="money" type="number" min="1" name="budget" placeholder="영화 총 제작비를 등록하세요." />

                        <label>수익</label>
                        <input class="money" type="number" min="1" name="profit" placeholder="영화의 수익을 등록하세요." />
                    </div>
                    <div class="btn-group">
                        <input type="submit" value="등록" />
                    </div>
                </form:form>
            </div>
        </body>

        </html>