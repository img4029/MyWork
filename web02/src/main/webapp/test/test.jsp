<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tab test</title>
<link href="test.css" rel="stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script defer type="text/javascript" src="test.js?ver=1"></script>
<style type="text/css">
	h3 {
		display: inline-block;
		width: 300px;
	}
	#menu,#menu div {
		display: inline-block;
		cursor: pointer;
	}
</style>
</head>
<body>
	<h2>** Dynamic Web Project **</h2>
	<c:set value="${sessionScope.sname}" var="sname"/>
	<c:choose>
		<c:when test="${!empty sessionScope.sname}">
			<h3>${sessionScope.sname}님 안녕하세요.</h3>
		</c:when>
		<c:otherwise>
			<h3>로그인을 해주세요.</h3>
		</c:otherwise>
	</c:choose>
	<div id="menu">
		<c:choose>
			<c:when test="${!empty sname}">
				<!-- &nbsp;<a href = "/web02/mdetail">MyInfo</a>&nbsp;
				&nbsp;<a href = "/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
				&nbsp;<a href = "/web02/logout">logout</a>&nbsp;
				&nbsp;<a href = "/web02/member/deleteForm.jsp">회원탈퇴</a>&nbsp; -->
				&nbsp;<div onclick="create_Tag('/web02/mdetail','MyInfo');">MyInfo</div>&nbsp;
				&nbsp;<div onclick="create_Tag('/web02/mdetail?jCode=U','내정보수정');">내정보수정</div>&nbsp;
				&nbsp;<div onclick="create_Tag('/web02/logout');">logout</div>&nbsp;
				&nbsp;<div onclick="create_Tag('../member/deleteForm.jsp','회원탈퇴');">회원탈퇴</div>&nbsp;
			</c:when>
			<c:otherwise>
				<!-- &nbsp;<a href = "/web02/member/loginForm.jsp">Login</a>&nbsp;
				&nbsp;<a href = "/web02/member/joinForm.jsp">Join</a>&nbsp; -->
				&nbsp;<div onclick="create_Tag('../member/loginForm.jsp','Login');">Login</div>&nbsp;
				&nbsp;<div onclick="create_Tag('../member/joinForm.jsp','Join');">Join</div>&nbsp;
			</c:otherwise>
		</c:choose>
		
		
	</div>
	<!-- <button onclick="create_Tag();">추가</button> -->
	<div class="container">
		<ul class="tabs">
			<li class="tab-link current" data-tab="tab-1">
				<span>메뉴1</span>
				<span>X</span>
			</li>
			<!-- <li class="tab-link" data-tab="tab-2">
				<span>메뉴2</span>
				<span class="dis-none">X</span>
			</li>
			<li class="tab-link" data-tab="tab-3">
				<span>메뉴3</span>
				<span class="dis-none">X</span>
			</li> -->
		</ul>

		<div id="tab-1" class="tab-content current">
			<c:import url="../member/loginForm.jsp"></c:import>
		</div>
		<!-- <div id="tab-2" class="tab-content">
			<p>tab2입니다.</p>
		</div>
		<div id="tab-3" class="tab-content">
			<p>tab3입니다.</p>
		</div> -->
	</div>
</body>
</html>