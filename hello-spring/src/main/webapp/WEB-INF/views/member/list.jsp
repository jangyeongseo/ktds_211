<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>회원 목록</title>
			<link rel="stylesheet" type="text/css" href="/css/member-spring.css">
		</head>

		<body>
			<div class="container member-list-container">
				<h1>회원 목록</h1>

				<c:choose>
					<c:when test="${not empty memberResult}">
						<!-- 회원이 있을 때 -->
						<table>
							<thead>
								<tr>
									<th>이메일</th>
									<th>이름</th>
									<th>비밀번호</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${memberResult}" var="member">
									<tr>
										<td><a href="/member/view/${member.email}">${member.email}</a>
										</td>
										<td>${member.name}</td>
										<td>${member.password}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<p>총 회원 수: ${memberCount}</p>
					</c:when>

					<c:otherwise>
						<!-- 회원이 없을 때 -->
						<p>등록된 회원이 없습니다.</p>
					</c:otherwise>
				</c:choose>

				<a href="/regist">회원가입</a>
			</div>
		</body>

		</html>