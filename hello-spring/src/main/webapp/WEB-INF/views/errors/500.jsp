<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param name="title" value="403 error" />
</jsp:include>

<h1>시스템 에러가 발생했습니다</h1>
<p>잠시후 다시 시도해주세요.</p>
<p>시스템 관리자는 에러 내용을 확인해주세요</p>

<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />