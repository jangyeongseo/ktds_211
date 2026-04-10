<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>

		<!-- header -->
		<jsp:include page="/WEB-INF/views/templates/header.jsp">
			<jsp:param name="title" value="게시글 내용 조회 : ${articleId.id}" />
			<jsp:param name="script" value="<script type='text/javascript' src='/js/reply.js'></script>" />
		</jsp:include>

		<div class="container" data-article-id="${articleId.id}">
			<div class="grid view">
				<span>이름</span>
				<div>
					${articleId.memberVO.name} / 가입 날짜: ${articleId.memberVO.registDate}
				</div>

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

				<div class="btn-group">
					<div class="right-align">
						<c:if test="${sessionScope.__LOGIN_DATA__.email eq articleId.email}">
							<a href="/update/${articleId.id}">수정</a>
							<a href="/delete?id=${articleId.id}">삭제</a>
						</c:if>
					</div>
				</div>
			</div>

			<!-- 댓글 영역 -->
			<div class="replies-count">
				총 <span class="count"></span>개의 댓글이 검색되었습니다.
			</div>

			<ul class="replies"></ul>

			<!-- 댓글 작성 -->
			<div class="reply-form">
				<input type="hidden" class="parent-reply-id" readonly />
				<textarea class="reply-content" placeholder="댓글을 입력하세요"></textarea>
				<input type="file" class="reply-attach-file" multiple />
				<button class="reply-save" data-article-id="${articleId.id}">등록</button>
			</div>

			<!-- 업데이트 댓글 템플릿 -->
			<template class="reply-item-update-files">
				<div>
					<input type="checkbox" id="#fileGroupId#-#fileNum#" name="deleteFileNum" value="#fileNum#" />
					<label for="#fileGroupId#-#fileNum#">#fileDisplayName#</label>
				</div>
			</template>

			<template class="reply-item-update-template">
				<div class="update-form">
					<textarea></textarea>
					<div class="update-file-list"></div>
					<input type="file" class="reply-update-attach-file" multiple />
					<div class="update-button-area">
						<button class="update-save">저장</button>
						<button class="update-cancel">취소</button>
					</div>
				</div>
			</template>

			<!-- 댓글 템플릿 -->
			<template class="reply-item-template">
				<li class="reply-item" data-reply-id="#replyId#">
					<div class="writer">
						<span class="writer-name">#name#</span>
						<span class="writer-email">(#email#)</span>
						<span class="recommend-count">#recommendCount#</span> 추천
					</div>

					<div class="dates">
						<div class="create-date">#createDate# 작성</div>
						<div class="modify-date">#modifyDate#</div>
					</div>

					<pre class="content">#content#</pre>
					<div class="reply-attach-files" data-files=""></div>

					<div class="links">
						<span class="links-write">답글</span>
						<span class="links-recommend">추천</span>
						<span class="links-update">수정</span>
						<span class="links-delete">삭제</span>
					</div>
				</li>
			</template>

		</div>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/templates/footer.jsp" />