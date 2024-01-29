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
<div id="boardDetail">
	<h3>제목 : ${requestScope.dto.title}</h3>
	<h4>작성자 : ${requestScope.dto.id}</h4>
	<h5>작성시간: ${requestScope.dto.regdate}</h5>
	<p>${requestScope.dto.content}</p>
	
	<c:if test="${requestScope.dto.id.equals(sessionScope.loginID)}">
		&nbsp;<a class="link" href="detail?seq=${requestScope.dto.seq}&jCode=U">수정하기</a>&nbsp;
		&nbsp;<a class="link" href="delete?seq=${requestScope.dto.seq}">삭제하기</a>&nbsp;
	</c:if>
	<hr>
	<c:if test="${not empty requestScope.message}">
	=> ${requestScope.message}<hr>
	</c:if>
	<div class="divBox">
		&nbsp;<a class="link" href="/spring02/home">Home</a>&nbsp;
		&nbsp;<a class="link" href="boardList">MList</a>&nbsp;
		&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;	
	</div>
</div>


</body>
</html>