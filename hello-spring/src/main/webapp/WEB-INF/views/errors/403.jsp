<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param name="title" value="403 error" />
</jsp:include>

<h1>잘못된 접근입니다.</h1>

<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />