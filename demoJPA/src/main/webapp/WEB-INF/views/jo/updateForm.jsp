<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** UpdateForm **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css">
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
	div {
		width: 50vw;
		padding: 10px;
		text-align: center;
	}
	div>input {
		width: 20vw;
	}
	select {
		width: 15vw;
		height: 30px;
		font-size: 18px;
	}
</style>
</head>
<body>
<h2>** UpdateForm **</h2>
<c:set value="${requestScope.dto}" var="dto"></c:set>
<form action="update" method="post">
	<table border=1>
		<tr bgcolor="MediumOrchid" style="font-weight: bold;">
			<th>Jno</th><th>Jname</th><th>Captain</th>
			<th>Project</th><th>Slogan</th>
		</tr>
		<c:choose>
			<c:when test="${!empty dto}">
				<tr>
					<td><input readonly id="jno" name="jno" value="${dto.jno}"></td>
					<td><input id="jname" name="jname" value="${dto.jname}"></td>
					<td><input id="captain" name="captain" value="${dto.captain}"></td>
					<td><input id="project" name="project" value="${dto.project}"></td>
					<td><input id="slogan" name="slogan" value="${dto.slogan}"></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5" style="text-align: center;">MyInfo 결과가 1건도 없음
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div>
		<input type="submit" value="수정">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소">
	</div>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<hr>
</c:if>
<div class="divBox">
	&nbsp;<a href="/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
<hr>
</body>
</html>