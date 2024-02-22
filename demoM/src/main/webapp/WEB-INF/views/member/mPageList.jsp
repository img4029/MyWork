<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/test3.css">
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/test.css?c">
</head>
<script type="text/javascript">
"use script"
// 1. 검색조건 입력후 버튼클릭

function searchDB() {
	self.location='mPageList'
        + '?currPage=1&rowsPerPage=5'
        +'&searchType='+document.getElementById('searchType').value
        +'&keyword='+document.getElementById('keyword').value;
}

// 2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear() {
	if(document.getElementById('searchType').value == 'all') 
		document.getElementById('keyword').value='';
}

function checkClear() {
	let ck = document.querySelectorAll(".clear")

	ck.forEach(element => {
		element.checked = false;
	});
	return false;
}
</script>
</head>
<body>
	<h2>** Web02_MVC02 MemberList **</h2>
	<hr>
	<c:if test="${!empty requestScope.massage}">
	=> ${requestScope.massage}	
	<hr>
	</c:if>
	<div id="board-search">
		<div class="container">
			<form action="mCheckList" method="get">
				<b>ID : </b>
				<c:if test="${!empty requestScope.jList}">
					<c:forEach items="${requestScope.jList}" var="i">
						<c:set var="checked" value=""></c:set>
						<c:forEach items="${pageMaker.cri.check}" var="c">
							<c:if test="${c==i.jno}">
								<c:set var="checked" value="checked"></c:set>
							</c:if>
						</c:forEach>
						<input class="clear" type="checkbox" name="check" value="${i.jno}" ${checked}>${i.jname}&nbsp;
					</c:forEach>
				</c:if>
				<!-- <input type="checkbox" name="check" value="simsim916">최문석&nbsp;
				<input type="checkbox" name="check" value="agr4005">김수빈&nbsp;
				<input type="checkbox" name="check" value="bamboo7">최승삼&nbsp;
				<input type="checkbox" name="check" value="kso">김수옥&nbsp;
				<input type="checkbox" name="check" value="admin">관리자&nbsp; -->
				<input type="submit" value="Search">&nbsp;
				<input type="reset" value="Clear" onclick="return checkClear()">
			</form>
			<div class="search-window">
				<div class="search-wrap" style="display: flex">
					<select name="searchType" id="searchType" onchange="keywordClear()">
						<option value="all" ${pageMaker.cri.searchType == 'all' ? 'selected' : '' }>전체</option>
						<option value="id" ${pageMaker.cri.searchType == 'id' ? 'selected' : '' }>ID</option>
						<option value="name" ${pageMaker.cri.searchType == 'name' ? 'selected' : '' }>Name</option>
						<option value="age" ${pageMaker.cri.searchType == 'tc' ? 'selected' : '' }>Age</option>
						<option value="info" ${pageMaker.cri.searchType == 'info' ? 'selected' : '' }>Info</option>
						<option value="birthday" ${pageMaker.cri.searchType == 'birthday' ? 'selected' : '' }>Birthday</option>
						<option value="rid" ${pageMaker.cri.searchType == 'rid' ? 'selected' : '' }>추천인</option>
					</select>
					<label for="keyword" class="blind">게시판 내용 검색</label> <input
						id="keyword" type="search" name="keyword" placeholder="검색어를 입력해주세요."
						value="${pageMaker.cri.keyword}">
					<button type="button" id="searchBtn" 
					class="btn btn-dark" onclick="searchDB()">검색</button>
					<!-- CheckBox Test -->
				</div>
				
			</div>
		</div>
	</div>
	<table border="1" style="width: 100%">
		<tr bgcolor="DeepSkyBlue" style="font-weight: bold;">
			<th>ID</th><!-- <th>Password</th> --><th>Name</th><th>Age</th><th>Jno</th>
			<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>이미지</th>
		</tr>
		<c:choose>
			<c:when test="${!empty requestScope.mList}">
				<c:forEach var="s" items="${requestScope.mList}">
					<tr>
						<td>${s.id}</td>
						<%-- <td>${s.password}</td> --%>
						<td>${s.name}</td>
						<td>${s.age}</td>
						<td>${s.jno}</td>
						<td>${s.info}</td>
						<td>${s.point}</td>
						<td>${s.birthday}</td>
						<td>${s.rid}</td>
						<td>
							<img alt="이미지" src="/spring02/resources/uploadImages/${s.uploadfile}"
							width="50" height="70">  
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="9" style="text-align: center;">memberList 결과가 1건도 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<div align="center">
		<c:choose>
	     	<c:when test="${pageMaker.prev && pageMaker.spageNo > 1}">
	     		<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
	     		<a href="${pageMaker.searchQuery(pageMaker.spageNo - 1)}">&LT;</a>&nbsp;&nbsp;
	     	</c:when>
	     	<c:otherwise>
	     		<font color="gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
			</c:otherwise>
		</c:choose>
	     
		<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
			<c:if test="${i==pageMaker.cri.currPage}">
				<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
			</c:if>
			<c:if test="${i!=pageMaker.cri.currPage}">
				<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
			</c:if>
		</c:forEach>
	
		<c:choose>
	     	<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
	     		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo + 1)}">&GT;</a>
	     		&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
	     	</c:when>
	     	<c:otherwise>
	     		<font color="gray">&nbsp;&GT;&nbsp;LP</font>
			</c:otherwise>
		</c:choose>
	</div>
	<hr>
	&nbsp;<a href="/spring02/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;
</body>
</html>