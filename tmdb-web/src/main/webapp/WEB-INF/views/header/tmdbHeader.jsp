<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <header class="header">
            <div class="header-inner">
                <div class="logo">
                    <a href="/"> <img src="/imgs/log.png"></a>
                </div>

                <nav class="nav">
                    <a href="/">영화</a>
                    <a href="#">TV 프로그램</a>
                    <a href="#">인물</a>
                    <a href="#">Awards</a>
                    <a href="#">More</a>
                </nav>

                <div class="header-right">
                    <button class="icon-btn">
                        <span class="material-symbols-outlined">add</span>
                    </button>

                    <button class="lang-btn">KO</button>

                    <c:choose>
                        <c:when test="${not empty sessionScope.__LOGIN_DATA__}">
                            <a href="/mypage" class="btn">마이페이지</a>
                            <a href="/logout" class="btn">로그아웃</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" class="btn">로그인</a>
                            <a href="/sign" class="btn">회원가입</a>
                        </c:otherwise>
                    </c:choose>
                    
                    <button class="icon-btn">
                        <span class="material-symbols-outlined">search</span>
                    </button>
                </div>
            </div>

            <!-- 검색창 -->
            <c:if test="${showSearch}">
                <div class="header-search">
                    <div class="search-wrapper">
                        <span class="material-symbols-outlined search-icon">search</span>
                        <input type="text" placeholder="영화, TV 프로그램, 인물 검색">
                    </div>
                </div>
            </c:if>
        </header>