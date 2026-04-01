<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록</title>
<link rel="stylesheet" type="text/css" href="/css/movie-spring.css">
</head>
<body>
    <div class="container">
        <form action="/write" method="post">
            <h1>영화 등록</h1>
            <div class="grid">
                <!-- tmdb 프로젝트에서 영화를 등록할 때, 포스터 한 장을 업로드 할 수 있도록 개선. -->
                <label>포스터 URL</label>
                <input type="file" name="attachFiles" placeholder="영화 포스터 이미지 파일을 등록하세요." required />
                <!-- <input type="text" name="attachFiles" placeholder="영화 포스터 링크를 등록하세요." required /> -->

                <label>제목</label>
                <input type="text" name="title" placeholder="영화 제목을 등록하세요." required />

                <label>등급</label>
                <input type="text" name="movieRating" placeholder="영화 관람 가능한 나이를 등록하세요." />

                <label>개봉일</label>
                <input type="date" name="openDate" />

                <label>개봉 국가</label>
                <input type="text" name="openCountry" placeholder="영화가 개봉하는 국가를 등록하세요." />

                <label>상영 시간</label>
                <input type="number" name="runningTime" placeholder="영화 상영 시간을 등록하세요." />

                <label class="introduction">소개</label>
                <textarea name="introduce" placeholder="영화에 관한 간단한 소객를 등록하세요." ></textarea>

                <label class="plot">줄거리</label>
                <textarea name="synopsis" placeholder="영화의 줄거리를 등록하세요." required></textarea>

                <label>원제</label>
                <input type="text" name="originalTitle" placeholder="영화의 실제 제목을 등록하세요." />

                <label>상태</label>
                <input type="text" name="state" placeholder="영화의 개봉 상태를 등록하세요."  required/>

                <label>언어</label>
                <input type="text" name="language" placeholder="영화의 언어를 등록하세요." required />

                <label>제작비</label>
                <input class="money" type="number" name="budget" placeholder="영화 총 제작비를 등록하세요." />

                <label>수익</label>
                <input class="money" type="number" name="profit" placeholder="영화의 수익을 등록하세요." />

                <div class="btn-group">
                    <input type="submit" value="등록">
                </div>

            </div>
        </form>
    </div>
</body>
</html>