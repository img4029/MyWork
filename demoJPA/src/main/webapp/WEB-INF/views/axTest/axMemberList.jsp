<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
<style type="text/css">

	#jo tbody, #jo tr {
		display: flex;
	}
	#jo tr {
		flex-direction: column;
	}
	#jo th, #jo td {
		
		height: 40px;
		display:flex;
		align-items: center;
	}
	#jo th {
		width: 10vw;
		justify-content: center;
		text-align: center;
	}
	#jo td {
		width: 40vw;
		padding-left: 10px;
	}
	input {
		border: none;
		height: 30px;
		font-size: 18px;
		width: 37vw;
	}
	input:focus {
		outline:none;
	}
</style>
<body>
	<h2>** Web02_MVC02 MemberList **</h2>
	<hr>
	<c:if test="${!empty requestScope.massage}">
	=> ${requestScope.massage}	
	<hr>
	</c:if>
	
	<table border="1" style="width: 100%">
		<tr bgcolor="DeepSkyBlue" style="font-weight: bold;">
			<th>ID</th><!-- <th>Password</th> --><th>Name</th><th>Age</th><th>Jno</th>
			<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>이미지</th><th>Delete</th>
		</tr>
		<c:choose>
			<c:when test="${!empty requestScope.mList}">
				<c:forEach var="s" items="${requestScope.mList}">
					<tr>
						<!-- idbList(id별 boardList) : id 별 boardList
							=> 선택된 id를 function 에 전달 (매개변수를 활용)
							idbList('banana')
						-->
						<td>
							<%-- <span class="textlink" onclick="idbList('${s.id}')">${s.id}</span> --%>
							<a href="#" onclick="idbList('${s.id}')" >${s.id}</a>
						</td>
						<td>${s.name}</td>
						<td>${s.age}</td>
						<td style="position:relative;" id="jotable" onmouseenter="showJoDetail('${s.id}','${s.jno}')" onmouseleave="hideJoDetail('${s.id}')">${s.jno}<span id="jno${s.id}"></span></td>
						<!-- style="display:flex" -->
						<td>${s.info}</td>
						<td>${s.point}</td>
						<td>${s.birthday}</td>
						<td>${s.rid}</td>
						<td>
							<img alt="이미지" src="/resources/uploadImages/${s.uploadfile}"
							width="50" height="70">  
						</td>
						<!-- Delete 기능 추가
							=> 선택된 id를 function 에 전달 (매개변수를 활용)
							=> 결과는 성공/실패 여부만 전달: RESTController 로 
							=> 성공 : Deleted 로 변경, onclick 이벤트 해제 -->
						<td><span id="${s.id}" class="textlink" onclick="axiDelete(event,'${s.id}')">Delete</span></td>
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
	
	<hr>
	&nbsp;<a href="/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;
</body>
</html>