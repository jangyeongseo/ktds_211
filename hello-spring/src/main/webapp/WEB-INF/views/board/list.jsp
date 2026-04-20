<%-- <%@ %> : Directive --%>
	<%-- <%@ page %> : Page Directive --%>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<%-- JSTL을 사용하기 위해서는 taglib Directive 필요.--%>
				<%@ taglib prefix="c" uri="jakarta.tags.core" %>
				<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

					<!-- /templates/header.jsp import -->
					<jsp:include page="/WEB-INF/views/templates/header.jsp">
						<jsp:param name="title" value="게시글 목록" />
						<jsp:param name="script" value="<script type='text/javascript' src='/js/board.js'></script>" />
					</jsp:include>

					<!-- HTML 주석 : 브라우저 개발자 도구에서 노출되는 주석. -->
					<%-- JSP 주석 : 브라우저 개발자 도구에서 노풀되지 않는 주석 --%>
						<div class="search-box">
							<select id="list-size">
								<option value="10" ${pagination.listSize eq "10" ? "selected" : "" }>10개씩</option>
								<option value="20" ${pagination.listSize eq "20" ? "selected" : "" }>20개씩</option>
								<option value="30" ${pagination.listSize eq "30" ? "selected" : "" }>30개씩</option>
								<option value="40" ${pagination.listSize eq "40" ? "selected" : "" }>40개씩</option>
								<option value="50" ${pagination.listSize eq "50" ? "selected" : "" }>50개씩</option>
							</select>
							<div>
								<select id="search-type">
									<option value="email" ${pagination.searchType eq "email" ? "selected" : "" }>Email로 검색</option>
									<option value="name" ${pagination.searchType eq "name" ? "selected" : "" }>작성자 이름으로 검색</option>
									<option value="subject" ${pagination.searchType eq "subject" ? "selected" : "" }>제목으로 검색</option>
									<option value="content" ${pagination.searchType eq "content" ? "selected" : "" }>내용으로 검색</option>
									<option value="subject content" ${pagination.searchType eq "subject content" ? "selected" : "" }>제목 + 내용으로 검색</option>
								</select>
								<input type="text" placeholder="검색어를 입력" id="search-keyword" value="${pagination.searchKeyWord}" />
								<button type="button" class="search-button">검색</button>
							</div>
						</div>

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
							<sec:authorize access="isAuthenticated()">
								<a href="/write">글 작성</a>
							</sec:authorize>
						</div>

						<!-- 페이지네이션 -->
						<c:if test="${pagination.pageCount > 0}">
							<ul class="pagination">
								<c:if test="${pagination.hasPrevPageGroup}">
									<li><a data-page-no="0" href="javascript:void(-1);">처음</a></li>
									<li>
										<a data-page-no="${pagination.prevPageGroupStartPageNo}" href="javascript:void(-1);">이전</a>
									</li>
								</c:if>
								<c:forEach begin="${pagination.groupStartPageNo}" end="${pagination.groupEndPageNo}"
									var="page">
									<li class="${page eq pagination.pageNo ? 'active' : ''}">
									   <a data-page-no="${page}" href="javascript:void(-1);"> ${page + 1}</a>
									</li>
								</c:forEach>
								<c:if test="${pagination.hasNextPageGroup}">
									<li><a data-page-no="${pagination.nextPageGroupStartPageNo}" href="javascript:void(-1);">다음</a>
									</li>
									<li><a data-page-no="${pagination.pageCount -1}" href="javascript:void(-1);">마지막</a>
									</li>
								</c:if>
							</ul>
						</c:if>

						<!-- footer -->
						<jsp:include page="/WEB-INF/views/templates/footer.jsp" />