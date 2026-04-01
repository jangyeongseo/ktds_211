<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
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
				<div>
				    <ul class="vertical-list">
				        <c:forEach items="${articleId.files}" var="file">
				            <li>
				                <a href="/file/${file.fileGroupId}/${file.fileNum}">
				                    ${file.displayName}
				                </a>
				            </li>
				        </c:forEach>
				    </ul>
				</div>

				<span>내용</span>
				<pre>${articleId.content}</pre>
				<!-- pre : Presentation -->

				<div class="btn-group">
					<div class="right-align">
						<!-- 수정 - pathvariable(패스베리어블? 로 받는다 -->
						<a href="/update/${articleId.id}">수정</a> <a href="/delete?id=${articleId.id}">삭제</a>
					</div>
				</div>
			</div>
		</div>
	</body>

	</html>