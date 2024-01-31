<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/boardStyle.css?a">
</head>
<body>
<c:import url="/header"></c:import>
<div id="boardDetail">
	<h3>제목 : ${requestScope.dto.title}</h3>
	<h4>작성자 : ${requestScope.dto.id}</h4>
	<h5>작성시간: ${requestScope.dto.regdate}</h5>
	<p>${requestScope.dto.content}</p>
	<hr>
	<c:forEach var="b" items="${requestScope.bList}" >
		<c:if test="${b.seq == requestScope.dto.seq && b.indent != 0}">
			<span>${b.id}</span><p>${b.content}</p>
		</c:if>
	</c:forEach>
	<form action="replyInsert" method="post">
		<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
   			서버로 보내주어야함 (퀴리스트링으로 작성)    -->
		<textarea id="content_reply" name="content"></textarea>
		<div class="missingno_under">
			<input name="seq" type="hidden" value="${requestScope.dto.seq}">
			<input name="title" type="hidden" value="${requestScope.dto.title}">
			<input name="id" type="hidden" value="${sessionScope.loginID}">
			<input name="root" type="hidden" value="${requestScope.dto.root}">
			<input name="step" type="hidden" value="${requestScope.dto.step}">
			<input name="indent" type="hidden" value="${requestScope.dto.indent}">
			<input type="submit" value="입력">&nbsp;&nbsp;&nbsp;
		</div>
	</form>

	<c:if test="${not empty requestScope.message}">
	=> ${requestScope.message}<hr>
	</c:if>
	<div class="divBox">
		<c:if test="${!empty sessionScope.loginID}">
			&nbsp;<a class="link" href="replyInsert?root=${requestScope.dto.root}&step=${requestScope.dto.step}&indent=${requestScope.dto.indent}">답글쓰기</a>&nbsp;
		</c:if>
		<c:if test="${requestScope.dto.id.equals(sessionScope.loginID)}">
			&nbsp;<a class="link" href="detail?seq=${requestScope.dto.seq}&jCode=U">수정하기</a>&nbsp;
			&nbsp;<a class="link" href="delete?seq=${requestScope.dto.seq}&root=${requestScope.dto.root}">삭제하기</a>&nbsp;
		</c:if>
		&nbsp;<a class="link" href="boardList">글목록</a>&nbsp;
		&nbsp;<a class="link" href="/spring02/home">Home</a>&nbsp;
		&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;	
	</div>
</div>


</body>
</html>