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
<form action="insert" method="post">
	<div id="boardUpdate">
		<input id="id" name="id" value="${sessionScope.loginID}"><br>
		<span>제목 : </span>
		<input id="title" name="title" ><br>
		<h4>작성자 : ${sessionScope.loginID}</h4>
		<p>내용 : </p>
		<textarea id="content" name="content"></textarea>
		<div>
			<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="리셋">
		</div>
		<hr>
			<c:if test="${not empty requestScope.message}">
			=> ${requestScope.message}<hr>
			</c:if>
		<div class="divBox">
			&nbsp;<a class="link" href="/spring02/home">Home</a>&nbsp;
			&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;		
		</div>
	</div>
</form>

</body>
</html>