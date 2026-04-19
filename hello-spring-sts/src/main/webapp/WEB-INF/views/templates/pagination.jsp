<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- 페이지네이션 -->
<c:if test="${pagination.pageCount > 0}">
	<ul class="page-navigator">
		<c:if test="${pagination.hasPrevPageGroup}">
			<li><a data-page-no="0" href="javascript:void(-1);">처음</a></li>
			<li><a data-page-no="${pagination.prevPageGroupStartPageNo}"
				href="javascript:void(-1);">이전</a></li>
		</c:if>
		<c:forEach begin="${pagination.groupStartPageNo}"
			end="${pagination.groupEndPageNo}" var="page">
			<li class="${page eq pagination.pageNo ? 'active' : ''}"><a
				data-page-no="${page}" href="javascript:void(-1);"> ${page + 1}</a>
			</li>
		</c:forEach>
		<c:if test="${pagination.hasNextPageGroup}">
			<li><a data-page-no="${pagination.nextPageGroupStartPageNo}"
				href="javascript:void(-1);">다음</a></li>
			<li><a data-page-no="${pagination.pageCount -1}"
				href="javascript:void(-1);">마지막</a></li>
		</c:if>
	</ul>
</c:if>
