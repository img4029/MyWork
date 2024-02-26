"use strict"
// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response (axmemberList.jsp)
//   - idbList(id별 boardList) : RESTController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// 1. List 출력 
// 1.1) Page response
// => response 를 resultArea1에 출력하기
// => 요청명 : /member/aximlist
// => response : axMemberList.jsp
function axiMList(){
    let url = "/member/aximlist";
    axios.get(url
        ).then(response => {
           console.log('** response : axMemberList 요청 담기 성공');
           document.getElementById('resultArea1').innerHTML=response.data;
        }).catch(err => {
           alert(`** response : joinForm 실패 => ${err.message}`);
        })
	
} //axiMList

// 1.2) idbList(id별 boardList)
// => RESTController, pathVariable , List_Data response
// => Server : service, Sql 구문 작성
// => request : id를 path로 전송 "/rest/idbList/banan"
// => Response
//   -> 성공 : 반복문, Table로 List 출력문 완성, resultArea2 에 출력
//   -> 출력 자료의 유/무 : Server에서 status로 (없으면 502) 처리하는걸로
//   -> 실패 : resultArea2 clear, alert 으로 에러 메세지 출력
function idbList(id){
	let url = "/rest/idbList/"+ id;
	
	axios.get(url
	).then(response => {
		alert("** 성공 => resultArea2 에 List 작성");
		console.log("result List_Data => "+response.data);
		let listData = response.data;
		let resultHtml = 
		`<div id="board-list">
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
					<tbody>`;

		for(let b of listData) {
			resultHtml += 
			`<tr>
				<td>${b.seq}</td>
				<td class="title">${b.title}</td>
				<td>${b.id}</td>
				<td>${b.regdate}</td>
				<td>${b.cnt}</td>
			</tr>`;
		}
		resultHtml += 
		`			</tbody>
				</table>
			</div>	
		</div>
		`;
		document.getElementById("resultArea2").innerHTML = resultHtml;	
	}).catch(err =>{
		// => response의 status값이 502 면 출력자료없음
		if(err.response.status == "502") {
			document.getElementById("resultArea2").innerHTML = err.response.data;
		} else {
			document.getElementById("resultArea2").innerHTML = "";
			alert("** 시스템 오류, 잠시후 다시하세요 => err.message : " + err.message);
		}
	});
	
} //idbList

// 2.2) axiDelete'${s.id}')
// => 요청명: "/rest/axidelete/"  PathVariable 적용
// => response 성공/실패 여부만 전달qkedma, 그러므로 RESTController 로 
// => 성공 : Deleted 로 변경, onclick 이벤트 해제
function axiDelete(id){
	let url = "/rest/axidelete/"+ id;
	console.log(id);
	axios.delete(url
	).then(response => {
		alert(response.data);
		document.getElementById(id).innerHtml = "Deleted";
		document.getElementById(id).style.color = "Gray";
		document.getElementById(id).style.fontWeight = "Bold"
		document.getElementById(id).classList.remove("textlink");
		document.getElementById(id).removeAttribute("onclick");
		// => 삭제 성공
		//   - Delete -> Deleted, Gray_color, Bold
		//   - onclick 이벤트 해제
		//   - style 제거
	}).catch(err => {
		if(err.response.status == "502") alert(err.response.data);
		else alert("** 시스템 오류, 잠시후 다시하세요 => err.message : " + err.message);
	});
}

// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 
function searchDB() {
	let url='axmcri'
        + '?currPage=1&rowsPerPage=5'
        +'&searchType='+document.getElementById('searchType').value
        +'&keyword='+document.getElementById('keyword').value;
	axiMListCri(url);
}

// => 2) searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear() {
	if(document.getElementById('searchType').value == 'all') 
		document.getElementById('keyword').value='';
}

// => 3) axios Code
function axiMListCri(url){
	url = "/member/" + url;
	alert(`axiMListCri url=${url}`);
	
	axios.get(url
	).then(response => {
		console.log("** response 성공 **")
		document.getElementById("resultArea1").innerHTML=response.data;
	}).catch(err => {
		document.getElementById("resultArea1").innerHTML="** axiMListCri 실패 => " + err.message;
	});
	document.getElementById("resultArea2").innerHTML="";
}

// => 4) Check 검색기능 추가
// => Check 검색 submit을 button(type 속성 주의) 으로 변경
// => MemberController : axmcri 메서드 공유
// => 단, 조건 구분을 위해 요청명은 "/axmcheck"
function checkDB() {
	let url='axmcheck'
        + '?currPage=1&rowsPerPage=5';

	let clear = document.querySelectorAll(".clear");
	
	clear.forEach(check => {
		if(check.checked) url += "&check=" + check.value;
	})
	
	/*for(let i = 0; i< clear.length; i++ ){
		if(clear[i].checked == true){
			url += "&check="+clear[i].value;
		}
	}*/
	
	axiMListCri(url);
}

function checkClear() {
	let ck = document.querySelectorAll(".clear")

	ck.forEach(element => {
		element.checked = false;
	});
	return false;
}



