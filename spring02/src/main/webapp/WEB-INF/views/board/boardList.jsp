<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/test.css?c">
</head>
<body>
<c:import url="/header"></c:import>
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>게시판</h3>
			</div>
		</div>

		<!-- board seach area -->
<!-- 		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap">
							<label for="search" class="blind">공지사항 내용 검색</label> <input
								id="search" type="search" name="" placeholder="검색어를 입력해주세요."
								value="">
							<button type="submit" class="btn btn-dark">검색</button>
						</div>
					</form>
				</div>
			</div>
		</div> -->

		<!-- board list area -->
		<div id="board-list">
			<div class="container">
				<table class="board-table">
					<thead>
						<tr style="background-color: Gainsboro;">
							<th scope="col" class="th-num">번호</th>
							<th scope="col" class="th-title">제목</th>
							<th>작성자</th>
							<th scope="col" class="th-date">등록일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty requestScope.bList}">
							<c:forEach var="b" items="${requestScope.bList}">
								<tr>
									<td>
										<c:if test="${b.indent == 0}">
											${b.seq}
										</c:if>
										<c:if test="${b.indent != 0}">
											${b.root}-${b.indent}
										</c:if>
									</td>
									<td class="title">
										<c:if test="${b.indent != 0}">
											<c:forEach begin="1" end="${b.indent}" step="1">
												&nbsp;&nbsp;&nbsp;&nbsp;
											</c:forEach>
											<span style="color: blue;">re...</span>
										</c:if>
										<c:if test="${!empty sessionScope.loginName}">
											<a href="detail?seq=${b.seq}&jCode=D">${b.title}</a>
										</c:if>
										<c:if test="${empty sessionScope.loginName}">${b.title}
										</c:if>
									</td>
									<td>${b.id}</td>
									<td>${b.regdate}</td>
									<td>${b.cnt}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="divBox">
					<c:if test="${!empty sessionScope.loginName}">
						&nbsp;<a class="link" href="boardInsert">등록하기</a>&nbsp; 
					</c:if>
					&nbsp;<a class="link" href="/spring02/home">Home</a>&nbsp; 
					&nbsp;<a class="link" href="javascript:history.back();">이전으로</a>&nbsp;
				</div>
			</div>
			
		</div>
		
	</section>
	
</body>
</html>