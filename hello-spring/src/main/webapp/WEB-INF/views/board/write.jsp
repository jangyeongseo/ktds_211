<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
	<jsp:param name="title" value="게시글 작성" />
	<jsp:param name="script" value="<script type='text/javascript' src='/js/board.js'></script>" />
</jsp:include>

<!-- action => form 내부의 value를 전송할 엔드포인트 -->
<!-- post는 내용의 값응ㄹ 안정했기 때문에 많은 용량을 보낼 경우 post가 좋고 적은 내용일 경우 get이 좋다. -->
<!-- url 혹은 링크 일경우 get으로 연결한다 -->
<!-- 텍스트로만 전달하고 있는 상황 - 파일을 전송할 경우 -->
<!-- enctype="multipart/form-data" => 파일 전송이 가능해진다 -->

<!-- form:form modelAttribute => 
                        form 태그 내부의 input, textarea, select 등을 컨트롤러 보내기 위한 아이디 
                        보편적으로 변수의 이름(엔드포인트의) -->
<form:form id="writeVO" modelAttribute="writeVO" method="post"
	action="/write" enctype="multipart/form-data">
	<h1>게시글 작성</h1>
	<div class="grid write">
		<label for="subject">제목</label> 
		<input type="text" id="subject" name="subject" placeholder="제목을 입력하세요" value="${inputData.subject}" />
		<form:errors path="subject" cssClass="validation-error" element="div" />

		<!-- input한테는 file의 값을 줄 수 없다. -->
		<label>첨부 파일</label>
		<div class="attach-files">
			<div class="file-row">
				<input type="file" name="attachFiles" />
				<button type="button" class="add-files">+</button>
			</div>
		</div>

		<label for="content">내용</label>
		<textarea id="content" name="content" placeholder="내용을 입력하세요">${inputData.content}</textarea>

		<div class="btn-group">
			<div class="right-align">
				<input type="submit" value="저장" />
			</div>
		</div>
	</div>
</form:form>

<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />
