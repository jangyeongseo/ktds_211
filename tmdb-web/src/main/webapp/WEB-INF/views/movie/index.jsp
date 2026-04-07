<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="kr">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>TMDB Clone</title>
            <link rel="stylesheet" type="text/css" href="/css/index-spring.css" />
            <link rel="stylesheet" type="text/css" href="/css/header.css" />
            <link rel="stylesheet" type="text/css" href="/css/footer.css" />
            <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
        </head>

        <body>
            <jsp:include page="/WEB-INF/views/header/tmdbHeader.jsp">
			    <jsp:param name="showSearch" value="true"/>
			</jsp:include>

            <!-- 영화 내용 -->
            <main>
                <!-- 검색 섹션 -->
                <section class="search-section">
                    <div class="search-inner">
                        <h1>환영합니다</h1>
                        <p>수백만 개의 영화, TV 프로그램 및 인물을 발견하세요. 지금 살펴보세요.</p>

                        <div class="search-box">
                            <input type="text" placeholder="영화, TV 프로그램, 인물 검색...">
                            <button>검색</button>
                        </div>
                    </div>
                </section>

                <!-- 트렌드 -->
                <section class="results-section">
                    <div class="section-inner">
                        <div class="title-row">
                            <h2 class="section-title">트렌드</h2>
                            
                            <c:choose>
                                <c:when test="${not empty sessionScope.__LOGIN_DATA__}">
	                                <div class="list-header">
	                                   <a href="/write" class="write-btn">등록</a>
	                                </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="list-header"></div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="movie-grid">
                            <c:forEach items="${movieList}" var="movie">
                                <div class="movie-card">
                                    <a href="/view/${movie.movieId}">

                                        <c:choose>
                                            <c:when test="${not empty movie.files}">
                                                <c:forEach items="${movie.files}" var="file" begin="0" end="0">
                                                    <img src="/file/${file.fileGroupId}/${file.fileNum}" />
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="default-poster"></div>
                                            </c:otherwise>
                                        </c:choose>

                                        <h3>${movie.title}</h3>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </section>

                <!-- 최신 예고편 -->
                <section class="results-section">
                    <div class="section-inner">
                        <h2 class="section-title">최신 예고편</h2>
                        <div class="movie-grid"></div>
                    </div>
                </section>

                <!-- 인기 콘텐츠 -->
                <section class="results-section">
                    <div class="section-inner">
                        <h2 class="section-title">인기 콘텐츠</h2>
                        <div class="movie-grid"></div>
                    </div>
                </section>

                <!-- 무료 시청-->
                <section class="results-section">
                    <div class="section-inner">
                        <h2 class="section-title">무료 시청</h2>
                        <div class="movie-grid"></div>
                    </div>
                </section>

                <!-- 지금 바로 가입하세요! 글.. -->
                <section class="results-section">
                    <div class="section-inner">
                        <h2 class="section-title">지금 바로 가입하세요!</h2>
                        <div class="movie-grid"></div>
                    </div>
                </section>
            </main>

            <!-- 하단 -->
            <jsp:include page="/WEB-INF/views/footer/tmdbFooter.jsp" />

        </body>

        </html>