<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Delete **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br>
<div class="divBox">
	&nbsp;<a href="/spring02/home">Home</a>&nbsp;
</div>
</c:if>

<c:if test="${empty requestScope.message}">
<h2>정말로 탈퇴 하시겠습니까??</h2>
<form action="delete" method="post">
	<input type="submit" value="탈퇴">&nbsp;&nbsp;&nbsp;
	<input type="reset" value="취소" onClick="location.href='/web02/home.jsp'">
</form>
</c:if>

</body>
</html>