<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Join Form **</title>
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
		width: 20vw;
	}
	input:focus {
		outline:none;
	}
	div {
		width: 50vw;
		padding: 10px;
		text-align: center;
	}
	input {
		width: 20vw;
	}
	select {
		width: 10vw;
		height: 30px;
		font-size: 18px;
	}
</style>
</head>
<body>
<h2>** Jo Join Form **</h2>
<form action="insert" method="post">
<table border=1>
	<tr bgcolor="Aquamarine" style="font-weight: bold;">
		<th>Jno</th><th>Jname</th><th>Captain</th>
		<th>Project</th><th>Slogan</th>
	</tr>
	<tr>
		<td><input type="text" id="jno" name="jno" placeholder="조를 입력하세요" value=""></td>
		<td><input type="text" id="jname" name="jname" placeholder="조 이름을 입력하세요" value=""></td>
		<td><input type="text" id="captain" name="captain" placeholder="조장의 ID를 입력하세요" value=""></td>
		<td><input type="text" id="project" name="project" placeholder="조의 목표 프로젝트를 입력하세요" value=""></td>
		<td><input type="text" id="slogan" name="slogan" placeholder="조의 슬로건을 입력하세요" value=""></td>
	</tr>
</table>
<div>
	<input type="submit" value="가입">&nbsp;&nbsp;&nbsp;
	<input type="reset" value="취소">
</div>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br>
</c:if>
<hr>
<div class="divBox">
	&nbsp;<a href="/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
</body>
</html>