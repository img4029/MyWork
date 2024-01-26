<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h1>** Hello Spring_MVC02 !!!</h1>

<P>*  Home_time : ${serverTime}</P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요.<br>
</c:if>
<c:if test="${empty sessionScope.loginID}">
	로그인 후 이용하세요.<br>
</c:if>
<c:if test="${!empty requestScope.message}">
	<hr>=> ${requestScope.message}<br>
</c:if>
<hr>

<!-- 로그인 전 -->
<c:if test="${empty sessionScope.loginID}">
	<img alt="mainImage" src="resources/images/Destiny2.gif" width="1000" height="600">
	<hr>
	<div class="divBox">
		&nbsp;<a href = "member/loginForm">LoginF</a>&nbsp;
		&nbsp;<a href = "member/joinForm">JoinF</a>&nbsp;
	</div>
</c:if>
<!-- 로그인 후 -->
<c:if test="${!empty sessionScope.loginID}">
	<img alt="mainImage" src="resources/images/Destiny4.gif" width="1000" height="600">
	<hr>
	<div class="divBox">
		&nbsp;<a href = "member/detail?jCode=D">내정보</a>&nbsp;
		&nbsp;<a href = "member/detail?jCode=U">내정보수정</a>&nbsp;
		&nbsp;<a href = "member/logout">logout</a>&nbsp;
		&nbsp;<a href = "member/deleteForm">회원탈퇴</a>&nbsp;
	</div>
</c:if>
<hr>
<div class="divBox">
	&nbsp;<a href = "member/memberList">MList</a>&nbsp;
	&nbsp;<a href = "jo/joList">JList</a>&nbsp;
</div>

</body>
</html>