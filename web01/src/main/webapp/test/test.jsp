<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tab test</title>
<link href="test.css" rel="stylesheet" type="text/css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<h2>tab test</h2>
	<button onclick="create_Tag();">추가</button>
	<div class="container">
		<ul class="tabs">
			<li class="tab-link current" data-tab="tab-1">
				<span>메뉴1</span>
				<span>X</span>
			</li>
			<li class="tab-link" data-tab="tab-2">
				<span>메뉴2</span>
				<span class="dis-none">X</span>
			</li>
			<li class="tab-link" data-tab="tab-3">
				<span>메뉴3</span>
				<span class="dis-none">X</span>
			</li>
		</ul>

		<div id="tab-1" class="tab-content current">
			<p>tab1입니다.</p>
			<c:import url="/jsp03_jstl/ex05_catch.jsp"></c:import>
		</div>
		<div id="tab-2" class="tab-content">
			<p>tab2입니다.</p>
			<c:import url="/jsp03_jstl/ex06_fmt.jsp"></c:import>
		</div>
		<div id="tab-3" class="tab-content">
			<p>tab3입니다.</p>
			<c:import url="/jsp03_jstl/ex07_elFunction.jsp"></c:import>
		</div>
	</div>
</body>
</html>