<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
	<h2>** Dynamic Web Project **</h2>
	<c:set value="${sessionScope.sname}" var="sname"/>
	<c:choose>
		<c:when test="${!empty sname}">
			<h3>${sname}님 안녕하세요.</h3>
		</c:when>
		<c:otherwise>
			<h3>로그인을 해주세요.</h3>
		</c:otherwise>
	</c:choose>
	<hr>
	<img alt="" src="./images/Destiny3.gif" width="1000" height="600">
	<hr>
	<c:choose>
		<c:when test="${!empty sname}">
			&nbsp;<a href = "/web02/mdetail">MyInfo</a>&nbsp;
			&nbsp;<a href = "/web02/logout">logout</a>&nbsp;
		</c:when>
		<c:otherwise>
			&nbsp;<a href = "/web02/member/loginForm.jsp">Login</a>&nbsp;
			&nbsp;<a href = "/web02/member/joinForm.jsp">Join</a>&nbsp;
		</c:otherwise>
	</c:choose>
	<hr>
	&nbsp;<a href = "/web02/list">MList</a>&nbsp;
</body>
</html>