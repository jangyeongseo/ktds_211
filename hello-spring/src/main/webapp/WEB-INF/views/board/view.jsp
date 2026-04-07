<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
	<jsp:param value="게시글 내용 조회 : ${articleId.id}" name="title" />
</jsp:include>

<div class="container">
	<div class="grid view">
		<span>이름</span>
		<div>${articleId.memberVO.name} 가입 날짜: ${articleId.memberVO.registDate}</div>

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
					   <a href="/file/${file.fileGroupId}/${file.fileNum}">${file.displayName}</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<span>내용</span>
		<pre>${articleId.content}</pre>
		<!-- pre : Presentation -->

		<div class="btn-group">
			<div class="right-align">
				<c:if test="${sessionScope.__LOGIN_DATA__.email eq articleId.email}">
					<!-- 수정 - pathvariable(패스베리어블? 로 받는다 -->
					<a href="/update/${articleId.id}">수정</a> 
					<a href="/delete?id=${articleId.id}">삭제</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />