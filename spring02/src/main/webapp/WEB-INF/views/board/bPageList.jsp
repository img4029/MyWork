<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/test.css?c">
<script type="text/javascript">
"use script"
// 1. 검색조건 입력후 버튼클릭
// => 입력된 값을 서버로 전송요청 처리 : 로케이션(location)

// ** self.location   
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// 2) location 객체의 메서드
// => href, replace('...'), reload() 
function searchDB() {
	self.location ='bPageList${pageMaker.makeQuery(1)}'
	+'&searchType='+document.getElementById('searchType').value
	+'&keyword='+document.getElementById('keyword').value;
}
//** JS 코드 내부에서 el Tag 사용시 주의사항
//=> JS 코드의 스트링 내에서 사용한 el Tag 는 JSP 가 처리해주므로   
//사용가능 하지만, 이 스크립트가 외부 문서인 경우에는 처리해주지 않으므로 주의
//이 코드를 외부문서로 작성하면 "${pageMaker.makeQuery(1)}" 이 글자 그대로 적용되어 404 발생 

// 2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear() {
	if(document.getElementById('searchType').value == 'all') 
		document.getElementById('keyword').value='';
}

</script>
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
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap" style="display: flex">
							<select name="searchType" id="searchType" onchange="keywordClear()">
								<option value="all">전체</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="id">작성자</option>
								<option value="regdate">등록일</option>
								<option value="tc">내용 및 작성자</option>
							</select>
							<label for="keyword" class="blind">게시판 내용 검색</label> <input
								id="keyword" type="search" name="keyword" placeholder="검색어를 입력해주세요."
								value="">
							<button type="button" id="searchBtn" 
							class="btn btn-dark" onclick="searchDB()">검색</button>
						</div>
					</form>
				</div>
			</div>
		</div>

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
				<br>
				<div align="center">
				<!-- ** Paging Block ** 
				   => ver01: QueryString 수동 입력 -> 자동생성
				     1) FirstPage, Prev  
				     => OLD
				     	<a href="bPageList?currPage=1&rowPerPage=5">FP</a>&nbsp;
				     	<a href="bPageList?currPage=${pageMaker.spageNo - 1}&rowPerPage=5">&LT;</a>&nbsp;&nbsp;
				     =>	ver01 makeQuery 메서드 적용	-->
					<c:choose>
				     	<c:when test="${pageMaker.prev && pageMaker.spageNo > 1}">
				     		<a href="bPageList${pageMaker.makeQuery(1)}">FP</a>&nbsp;
				     		<a href="bPageList${pageMaker.makeQuery(pageMaker.spageNo - 1)}">&LT;</a>&nbsp;&nbsp;
				     	</c:when>
				     	<c:otherwise>
				     		<font color="gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
						</c:otherwise>
					</c:choose>
				     
				<!-- 2) Display PageNo
					=> currPage	제외한 
					=> OLD
						<a href="bPageList?currPage=${i}&rowPerPage=5">${i}</a>&nbsp;
					=> ver01 makeQuery 메서드 적용		-->
					<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
						<c:if test="${i==pageMaker.cri.currPage}">
							<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
						</c:if>
						<c:if test="${i!=pageMaker.cri.currPage}">
							<a href="bPageList${pageMaker.makeQuery(i)}">${i}</a>&nbsp;
						</c:if>
					</c:forEach>
				
				<!-- 3) Next, LastPage 
					 => OLD
						&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo + 1}&rowPerPage=5">&GT;</a>
				     	&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo}&rowPerPage=5">LP</a>
					 => ver01: makeQuery() 메서드 사용 -->
					<c:choose>
				     	<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
				     		&nbsp;<a href="bPageList${pageMaker.makeQuery(pageMaker.epageNo + 1)}">&GT;</a>
				     		&nbsp;<a href="bPageList${pageMaker.makeQuery(pageMaker.lastPageNo)}">LP</a>
				     	</c:when>
				     	<c:otherwise>
				     		<font color="gray">&nbsp;&GT;&nbsp;LP</font>
						</c:otherwise>
					</c:choose>
				<!-- => ver02: searchQuery() 메서드 사용 -->
				</div>

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