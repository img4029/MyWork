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
	
	<form action="getpost" method="post">
		<input type="text" name="id" value="banana"> &nbsp;
		<input type="text" name="name" value="바나나">
		<input type="submit" value="Test">
	</form>
	<hr>
	<!-- ** 경로표기
		=> 절대경로 : / 로 시작, 프로젝트명부터 전제경로 표기
			-> /web01/images/Letsgo.png
			-> webapp 폴더는 생략됨
		=> 상대경로 : 현재위치에서 시작, / 로 시작하면 안됨
			-> ./ : 현재위치, ../1단계 상위
			-> "./images/Letsgo.png", "images/Letsgo.png" 동일
	-->
	<img alt="" src="./images/Destiny3.gif" width="1000" height="600">
	<hr>
	<c:choose>
		<c:when test="${!empty sname}">
			&nbsp;<a href = "/web01/detail">MyInfo</a>&nbsp;
			&nbsp;<a href = "/web01/logout">logout</a>&nbsp;
		</c:when>
		<c:otherwise>
			&nbsp;<a href = "/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
			&nbsp;<a href = "#">Join</a>&nbsp;
		</c:otherwise>
	</c:choose>
	&nbsp;<a href = "/web01/test01">test01</a>&nbsp;
	<hr>
	&nbsp;<a href = "/web01/hello">Hello</a>&nbsp;
	&nbsp;<a href = "/web01/list">M01List</a>&nbsp;
	&nbsp;<a href = "/web01/life">LifeCycle</a>&nbsp;
	<hr>
	&nbsp;<a href = "/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp;
	&nbsp;<a href = "/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp;
	&nbsp;<a href = "/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp;
	&nbsp;<a href = "/web01/servletTestForm/form04_Select.jsp">Select</a>&nbsp;
	<hr>
	&nbsp;<a href = "/web01/flow01">Flow01</a>&nbsp;
	&nbsp;<a href = "/web01/sessioni">SessionI</a>&nbsp;
	&nbsp;<a href = "/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp;
	&nbsp;<a href = "/web01/jsp01/ex02_mvc01List.jsp">MVC01List</a>&nbsp;
	&nbsp;<a href = "/web01/list2">MVC02List</a>&nbsp;
</body>
</html>