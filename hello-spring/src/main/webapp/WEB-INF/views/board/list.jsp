<%-- <%@ %> : Directive --%>
<%-- <%@ page %> : Page Directive --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL을 사용하기 위해서는 taglib Directive 필요.--%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
	<jsp:param name="title" value="게시글 목록" />
</jsp:include>

<!-- HTML 주석 : 브라우저 개발자 도구에서 노출되는 주석. -->
<%-- JSP 주석 : 브라우저 개발자 도구에서 노풀되지 않는 주석 --%>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>조회수</th>
			<th>등록일</th>
			<th>수정일</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty searchResult}">
				<%-- "${not empty searchResult}" : searchResult가 비어있지 않으면 --%>
				<!-- searchResult가 존재하면, 반복하여 데이터를 보여준다. -->
				<c:forEach items="${searchResult}" var="board">
					<tr>
						<td><a href="/view/${board.id}">${board.id}</a></td>
						<td>${board.subject}</td>
						<td>${board.memberVO.name}</td>
						<td>${board.viewCnt}</td>
						<td>${board.crtDt}</td>
						<td>${board.mdfyDt}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<!-- searchResult가 존재하지 않으면, "검색된 데이터가 없습니다."를 보여주고  -->
					<td colspan="6">검색된 데이터가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<div class="astyle">
    <c:choose>
        <c:when test="${not empty sessionScope.__LOGIN_DATA__}">
            <a href="/write">글 작성</a>
        </c:when>
        <c:otherwise>
            <div></div>
        </c:otherwise>
    </c:choose>
</div>

<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />
