<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/boardStyle.css">
</head>
<body>
<table id="boardList" border="1" style="width: 100%">
<tr bgcolor="DeepSkyBlue" style="font-weight: bold;">
	<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
</tr>
<c:if test="${!empty requestScope.bList}">
	<c:forEach var="b" items="${requestScope.bList}">
		<tr>
			<td>${b.seq}</td>
			<td class="title">
				<c:if test="${!empty sessionScope.loginName}">
					<a href = "detail?seq=${b.seq}&jCode=D">${b.title}</a>
				</c:if>
				<c:if test="${empty sessionScope.loginName}">
					${b.title}
				</c:if>
			</td>
			<td>${b.id}</td>
			<td>${b.regdate}</td>
			<td>${b.cnt}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.bList}">
	<tr>
		<td colspan="5" style="text-align: center;">등록된 게시글이 없습니다.</td>
	</tr>
</c:if>
</table>
<div class="divBox">
	&nbsp;<a class="link" href="boardInsert">등록하기</a>&nbsp;
	&nbsp;<a class="link" href="/spring02/home">Home</a>&nbsp;
	&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
</body>
</html>