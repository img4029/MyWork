<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MyInfo **</title>
<c:set var="dto" value="${requestScope.dto}"/>
</head>
<body>
<h2>** MyInfo **</h2>
<table border=1 style = "width:100%;text-align:center;">
		<tr bgcolor="Aquamarine" style="font-weight:bold;" >
			<th>sno</th><th>name</th><th>age</th>
			<th>jno</th><th>info</th><th>point</th>
		</tr>
		<c:choose>
			<c:when test ="${!empty dto}">
				<tr>
					<td>${dto.sno}</td><td>${dto.name}</td><td>${dto.age}</td>
					<td>${dto.jno}</td><td>${dto.info}</td><td>${dto.point}</td>
				</tr>
			</c:when>
			<c:otherwise>
            	<tr><td colspan = "6" style = "text-align:center;">
					MyInfo 결과가 1건도 없음
				</td></tr>
         	</c:otherwise>
		</c:choose>
	</table>
</body>
</html>