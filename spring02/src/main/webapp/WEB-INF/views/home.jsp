<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
    <style type="text/css">
    	main p, main h1, main span, main img{
    		padding: 20px 0;
    	}
    </style>
</head>
<body>
<c:import url="/header"></c:import>
<main>
	<h1>Hello Spring_MVC02!!!</h1>
	<P>Home_time : ${serverTime}</P>
	<c:if test="${!empty sessionScope.loginName}">
		<span>${sessionScope.loginName}님 안녕하세요.</span><br>
	</c:if>
	<c:if test="${empty sessionScope.loginID}">
		<span>로그인 후 이용하세요.</span><br>
	</c:if>
	<c:if test="${!empty requestScope.message}">
		<span>=> ${requestScope.message}</span><br>
	</c:if>
	
	<!-- 로그인 전 -->
	<c:if test="${empty sessionScope.loginID}">
		<img alt="mainImage" src="resources/images/Destiny2.gif" width="1000">
	</c:if>
	<!-- 로그인 후 -->
	<c:if test="${!empty sessionScope.loginID}">
		<img alt="mainImage" src="resources/images/Destiny4.gif" width="1000">
	</c:if>						
</main>


</body>
</html>