<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring02_MVC02 JoList **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring02_MVC02 JoList **</h2>
<hr>
<c:if test="${!empty requestScope.massage}">
=> ${requestScope.massage}	
<hr>
</c:if>
<table border="1" style="width: 100%">
	<tr bgcolor="DeepSkyBlue" style="font-weight: bold;">
		<th>Jno</th><th>Jname</th><th>Captain</th><th>조장명</th>
		<th>Project</th><th>Slogan</th>
	</tr>
	<c:choose>
		<c:when test="${!empty requestScope.jList}">
			<c:forEach var="s" items="${requestScope.jList}">
				<tr>
					<td><a href = "detail?jo=${s.jno}">${s.jno}</a></td>
					<td>${s.jname}</td>
					<td>${s.captain}</td>
					<td>${s.name}</td>
					<td>${s.project}</td>
					<td>${s.slogan}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5" style="text-align: center;">joList 결과가 1건도 없음</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
<c:if test="${!empty requestScope.message}">
	<hr>=> ${requestScope.message}<br>
</c:if>
<hr>
&nbsp;<a href="insertForm">등록하기</a>&nbsp;
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;
</body>
</html>