<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joDetail **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<style type="text/css">

	tbody, tr {
		display: flex;
	}
	tr {
		flex-direction: column;
	}
	th,td {
		
		height: 40px;
		display:flex;
		align-items: center;
	}
	th {
		width: 10vw;
		justify-content: center;
		text-align: center;
	}
	td {
		width: 40vw;
		padding-left: 10px;
	}
	input {
		border: none;
		height: 30px;
		font-size: 18px;
		width: 37vw;
	}
	input:focus {
		outline:none;
	}
</style>
</head>
<body>
<h2>** joDetail **</h2>
<table border=1>
	<tr bgcolor="Aquamarine" style="font-weight: bold;">
		<th>Jno</th><th>Jname</th><th>Captain</th><th>조장명</th>
		<th>Project</th><th>Slogan</th>
	</tr>
	<c:choose>
		<c:when test="${!empty requestScope.dto}">
			<tr>
				<td>${requestScope.dto.jno}</td>
				<td>${requestScope.dto.jname}</td>
				<td>${requestScope.dto.captain}</td>
				<td>${requestScope.dto.cname}</td>
				<td>${requestScope.dto.project}</td>
				<td>${requestScope.dto.slogan}</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">joDetail 결과가 1건도 없음
			</tr>
		</c:otherwise>
	</c:choose>
</table>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<hr>
</c:if>
<div class="divBox">
	&nbsp;<a href="detail?jo=1${requestScope.dto.jno}">수정하기</a>&nbsp;
	&nbsp;<a href="delete?jo=${requestScope.dto.jno}">삭제하기</a>&nbsp;
	&nbsp;<a href="/spring02/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
</body>
</html>