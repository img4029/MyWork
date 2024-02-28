<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>header</title>
	<link rel="stylesheet" type="text/css" href="/resources/myLib/header.css">
    <script defer src="/resources/myLib/header.js"></script>
</head>
<body>
<header>
	<div class="header_acide">
	    <div class="header_back"></div>
	    <div id="menuBtn1" class="menu_icon_box" onclick="change()">
	        <div class="menu_icon1"></div>
	        <div class="menu_icon2"></div>
	        <div class="menu_icon3"></div>
	    </div>
	    <div class="menu_list">
	        <div class="menu_list_main">
	            <ul>
	                <li><a href = "/member/memberList">MList</a></li>
	                <li><a href = "/member/mPageList">MPage</a></li>
	                <li><a href = "/jo/joList">JList</a></li>
	                <li><a href = "/board/boardList">BList</a></li>
	                <li><a href = "/board/bPageList">BPage</a></li>
	                <li><a href = "/axTestform">AjaxTest</a></li>
	                <li><hr></li>
	                <li><a href = "/ginsert">GInsert</a></li>
	                <li><a href = "/glist">GList</a></li>
	                <li><a href = "/gupdate">GUpdate</a></li>
	                <li><a href = "/gpage">GPage</a></li>
	            </ul>
	        </div>
	        <div class="menu_list_sub">
	            <ul>
	            	<c:if test="${empty sessionScope.loginID}">
						<li><a href = "/member/loginForm">LoginF</a></li>
						<li><a href = "/member/joinForm">JoinF</a></li>
					</c:if>
					<!-- 로그인 후 -->
					<c:if test="${!empty sessionScope.loginID}">
						<li><a href = "/member/detail?jCode=D">내정보</a></li>
						<li><a href = "/member/detail?jCode=U">내정보수정</a></li>
						<li><a href = "/member/logout">logout</a></li>
						<li><a href = "/member/deleteForm">회원탈퇴</a></li>
					</c:if>
	            </ul>
	        </div>
	    </div>
	    <div class="logo_box">
	        <a href="/home" class="logo_link">
	            <img class="logo" src="https://danielstruth.com/design/dmcosmetic/hologram/pc/logo/pclogo.png"
	                alt="LOGO">
	        </a>
	    </div>
	</div>
</header>

</body>
</html>