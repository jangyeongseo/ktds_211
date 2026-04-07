<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- /templates/header.jsp import -->
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param name="title" value="게시글 수정" />
    <jsp:param name="script" value="<script type='text/javascript' src='/js/board.js'></script>" />
</jsp:include>

        <!-- action => form 내부의 value를 전송할 엔드포인트 -->
        <!-- post는 내용의 값응ㄹ 안정했기 때문에 많은 용량을 보낼 경우 post가 좋고 적은 내용일 경우 get이 좋다. -->
        <!-- url 혹은 링크 일경우 get으로 연결한다 -->
        <form method="post" action="/update/${article.id}" enctype="multipart/form-data">
            <input type="hidden" name="fileGroupId" value="${article.fileGroupId" /> <!-- 파일 그룹 아이디를 보내줘야 한다.-->
            
            <div class="grid write">
                <label for="subject">제목</label>
                <input type="text" id="subject" name="subject" placeholder="제목을 입력하세요" value="${article.subject}" />
                
                <span>첨부파일</span>
                <div>
                    <ul class="vertical-list">
                        <c:forEach items="${article.files}" var="file">
                            <li>
                            <input type="checkbox" name="deleteFileNum" />
                                <a href="/file/${file.fileGroupId}/${file.fileNum}">
                                    ${file.displayName}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <label for="content">내용</label>
                <textarea id="content" name="content" placeholder="내용을 입력하세요">${article.content}</textarea>

                <div class="btn-group">
                    <div class="right-align">
                        <input type="submit" value="저장" />
                    </div>
                </div>

            </div>
        </form>
        
<!-- footer -->
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />
