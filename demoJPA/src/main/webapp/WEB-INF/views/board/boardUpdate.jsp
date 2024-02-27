<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/boardStyle.css?a">
</head>
<body>
<c:import url="/header"></c:import>
<form action="update" method="post">
	<div id="boardUpdate">
		<input id="seq" name="seq" value="${requestScope.dto.seq}"><br>
		<span>제목 : </span>
		<input id="title" name="title" value="${requestScope.dto.title}"><br>
		<h4>작성자 : ${requestScope.dto.id}</h4>
		<p>내용 : </p>
		<textarea id="content" name="content">${requestScope.dto.content}</textarea>
		<div>
			<input type="submit" value="수정">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="리셋">
		</div>
		<hr>
			<c:if test="${not empty requestScope.message}">
			=> ${requestScope.message}<hr>
			</c:if>
		<div class="divBox">
			&nbsp;<a class="link" href="/home">Home</a>&nbsp;
			&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;		
		</div>
	</div>
</form>

</body>
</html>