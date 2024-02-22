<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** UpdateForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<style type="text/css">

	tbody, tr {
		display: flex;
	}
	tr {
		flex-direction: column;
	}
	th,td {
		
		height: 40px;
		display:flex;
		align-items: center;
	}
	th {
		width: 10vw;
		justify-content: center;
		text-align: center;
	}
	td {
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
	div {
		width: 50vw;
		padding: 10px;
		text-align: center;
	}
	div>input {
		width: 20vw;
	}
	select {
		width: 15vw;
		height: 30px;
		font-size: 18px;
	}
</style>
<script>
	function pwUpdate(){
		console.log("오냐??");
		let popupX = (window.screen.width / 2) - (1300 / 2);
        let popupY = (window.screen.height / 2) - (700 / 2);
		let url="pwUpdate";
		window.open(url,'_blank','width=1300,height=700,resizable=yes,scrollbars=yes,toolbar=no,menubar=yes, left=' + popupX + ', top=' + popupY);
	}
</script>
</head>
<body>
<!-- Image Update 추가 
      => form Tag : method, enctype 확인
      => new Image 를 선택하는 경우 -> uploadfilef 사용
      => new Image 를 선택하지않는 경우 
         -> 본래 Image 를 사용 -> uploadfile 값이 필요함
--> 
<h2>** UpdateForm **</h2>
<c:set value="${requestScope.dto}" var="dto"></c:set>
<form action="update" method="post" enctype="multipart/form-data">
	<table border=1>
		<tr bgcolor="MediumOrchid" style="font-weight: bold;">
			<th style="height:310px">이미지</th><th>ID</th><th>Password</th><th>Name</th><th>Age</th><th>Jno</th>
			<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th>
		</tr>
		<c:choose>
			<c:when test="${!empty dto}">
				<tr>
					<td style="height:310px">
						<img alt="이미지" src="/spring02/resources/uploadImages/${dto.uploadfile}"
						width="300" height="300" class="select_img"> 
						<input type="hidden" id="uploadfile" name="uploadfile"
						value="${dto.uploadfile}">
						<input type="file" id="uploadfilef" name="uploadfilef">
					</td>
					<script>
						document.getElementById('uploadfilef').onchange=function(e){
         					if(this.files && this.files[0]) {
	            				let reader = new FileReader;
	            				reader.readAsDataURL(this.files[0]);
	             				reader.onload = (e) => {
					                // => jQuery를 사용하지 않는경우 
					                document.getElementsByClassName('select_img')[0].src=e.target.result;
	                
					             	//$(".select_img").attr("src", e.target.result)
					             	//            .width(70).height(90); 
				            	} // onload_function
				        	} // if   
				    	}; //change  
				    </script> 
					
					<td><input readonly id="id" name="id" value="${dto.id}"></td>
					<td>
						<button type="button" onclick="pwUpdate()">비밀번호 수정하기</button>
						<!-- <span id="message"></span> -->
						<%-- <input type="password" id="password" name="password" value="${dto.password}"> --%>
					</td>
					<td><input id="name" name="name" value="${dto.name}"></td>
					<td><input id="age" name="age" value="${dto.age}"></td>
					<td><select id="jno" name="jno">
						<option value="1" ${dto.jno == 1 ? "selected":""}>1조:Business</option>
						<option value="2" ${dto.jno == 2 ? "selected":""}>2조:static</option>
						<option value="3" ${dto.jno == 3 ? "selected":""}>3조:칭찬해조</option>
						<option value="4" ${dto.jno == 4 ? "selected":""}>4조:카톡으로얘기하조</option>
						<option value="7" ${dto.jno == 7 ? "selected":""}>7조:칠면조(관리팀)</option>
					</select></td>
					<td><input id="info" name="info" value="${dto.info}"></td>
					<td><input id="point" name="point" value="${dto.point}"></td>
					<td><input id="birthday" name="birthday" value="${dto.birthday}"></td>
					<td><input id="rid" name="rid" value="${dto.rid}"></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="9" style="text-align: center;">MyInfo 결과가 1건도 없음
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div>
		<input type="submit" value="수정">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소">
	</div>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<hr>
</c:if>
<div class="divBox">
	&nbsp;<a href="/spring02/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
<hr>
</body>
</html>