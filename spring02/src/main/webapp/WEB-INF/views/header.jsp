<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>header</title>
	<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/header.css">
    <script defer src="/spring02/resources/myLib/header.js"></script>
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
	                <li><a href = "/spring02/member/memberList">MList</a></li>
	                <li><a href = "/spring02/jo/joList">JList</a></li>
	                <li><a href = "/spring02/board/boardList">BList</a></li>
	                <li><a href = "/spring02/board/bPageList">BPage</a></li>
	                <li><a href = "/spring02/bcrypt">BCrypt</a></li>
	            </ul>
	        </div>
	        <div class="menu_list_sub">
	            <ul>
	            	<c:if test="${empty sessionScope.loginID}">
						<li><a href = "/spring02/member/loginForm">LoginF</a></li>
						<li><a href = "/spring02/member/joinForm">JoinF</a></li>
					</c:if>
					<!-- 로그인 후 -->
					<c:if test="${!empty sessionScope.loginID}">
						<li><a href = "/spring02/member/detail?jCode=D">내정보</a></li>
						<li><a href = "/spring02/member/detail?jCode=U">내정보수정</a></li>
						<li><a href = "/spring02/member/logout">logout</a></li>
						<li><a href = "/spring02/member/deleteForm">회원탈퇴</a></li>
					</c:if>
	            </ul>
	        </div>
	    </div>
	    <div class="logo_box">
	        <a href="/spring02/" class="logo_link">
	            <img class="logo" src="https://danielstruth.com/design/dmcosmetic/hologram/pc/logo/pclogo.png"
	                alt="LOGO">
	        </a>
	    </div>
<!-- 	    <div class="hearder_icon">
	        <ul>
	            <li>
	                <a href="">
	                    <img src="https://danielstruth.com/design/dmcosmetic/hologram/pc/icon/basketbtn.png"
	                        alt="장바구니">
	                    <span class="shopping_basket"></span>
	                </a>
	            </li>
	            <li>
	                <a class="testing_logo" href="">
	                    <img src="https://danielstruth.com/design/dmcosmetic/hologram/pc/icon/mypagebtn.png"
	                        alt="마이페이지">
	                </a>
	            </li>
	            <li>
	                <a href="">
	                    <img src="https://danielstruth.com/design/dmcosmetic/hologram/pc/icon/searchbtn.png"
	                        alt="검색">
	                </a>
	            </li>
	        </ul>
	    </div> -->
	</div>
<!-- 	<div class="header_top">
	    <div>
	        <div></div>
	        <div></div>
	    </div>
	    <a href="../index.html">Daniel's Truth</a>
	</div> -->
</header>

</body>
</html>