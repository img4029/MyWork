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
function axiDelete(e, id){
	let url = "/rest/axidelete/"+ id;
	console.log(e.target);
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

// 2.3) JoDetail
// 2.3.1) MouseOver : showJoDetail 
// => jno mouseover : JoDetail content Div에 출력 (마우스포인터 위치에)
// => request: axios, get, RESTController 에 "/jodetail" 요청
// => response: 성공시 JoDTO 객체
function showJoDetail(id,jno) {
	// ** 마우스포인터 위치 확인
   	// => 이벤트객체 활용
   	//     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   	//     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   	//    - e.pageX, e.pageY : 전체 Page 기준
   	//     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함
	let url = "/rest/jotable/"+ jno;
	let jnoid = document.getElementById("jno"+id);
	
	jnoid.style.position = "absolute";
	jnoid.style.left = '50%';
	jnoid.style.top = '50%';
	jnoid.style.zIndex = 50;
	
	axios.get(url
		).then(response => {
			console.log('** response : jotable 요청 담기 성공');
			jnoid.innerHTML = 
			`<table id="jo" border=1>
				<tr bgcolor="DarkGray" style="font-weight: bold;">
					<th>Jno</th><th>Jname</th><th>Captain</th><th>조장명</th>
					<th>Project</th><th>Slogan</th>
				</tr>
				<tr bgcolor="White">
					<td onmouseenter="showMemberDetail('${id}','${jno}')" onmouseleave="hideMemberDetail('${id}')">${response.data.jno}
						<span id="memberJo${id}""></span>
					</td>
					<td>${response.data.jname}</td>
					<td>${response.data.captain}</td>
					<td>${response.data.name}</td>
					<td>${response.data.project}</td>
					<td>${response.data.slogan}</td>
				</tr>
			</table>`;
		}).catch(err => {
			if(err.response.status == "502") alert(err.response.data);
			else alert("** 시스템 오류, 잠시후 다시하세요 => err.message : " + err.message);
		});
}

function hideJoDetail(id) {
	let jnoid = document.getElementById("jno"+id);
	jnoid.style.position = "default";
	jnoid.innerHTML = "";
}

function tcshowJoDetail(e, jno) {
   // ** 마우스포인터 위치 확인
   // => 이벤트객체 활용
   //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   //    - e.pageX, e.pageY : 전체 Page 기준
   //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함   
   console.log(`** e.pageX=${e.pageX}, e.pageY=${e.pageY}`);
   console.log(`** e.clientX=${e.clientX}, e.clientY=${e.clientY}`);
   
   let url="/rest/jodetail/"+jno ;
   let mleft=e.pageX+20;
   let mtop=e.pageY;
   
   axios.get(url
   ).then(response => {
      console.log(`** response 성공: JS객체 => ${response.data}`);
      
      // ** JSON.stringify 적용 비교
      let jj =JSON.stringify(response.data);   
      // => Object -> JSON : Data를 나열해줌 
      // => JS 객체포맷을 JSON 포맷화 하면 key:value 형태로 나열해줌
      //    (즉, 하나의 긴문자열, 문자Type 이됨)
      //    console.log 로 response.data 의 내용을 확인할때 사용하면 편리함.  
      console.log(`** response 성공: JSON포맷 => ${jj}`);
      
      let jo=response.data;
      console.log("** Data: jo.jno => "+jo.jno);
      let resultHtml = `
      <table>
         <tr height="10"><td>Jno</td><td>${jo.jno}</td></tr>
         <tr height="10"><td>JoName</td><td>${jo.jname}</td></tr>
         <tr height="10"><td>CaptainID</td><td>${jo.captain}</td></tr>
         <tr height="10"><td>Project</td><td>${jo.project}</td></tr>
         <tr height="10"><td>Slogan</td><td>${jo.slogan}</td></tr>
      </table>`;
      document.getElementById('content').innerHTML=resultHtml;
      document.getElementById('content').style.display='block';
      document.getElementById('content').style.left=mleft+"px";
      document.getElementById('content').style.top=mtop+"px";
      
   }).catch(err => {
      if ( err.response.status=='502' ) alert(err.response.data)
      else alert("** 시스템 오류, 잠시후 다시 하세요 => "+err.message);
   });   
} //showJoDetail


function showMemberDetail(id,jno) {
	let url = "/rest/membertable/"+ jno;
	
	axios.get(url
		).then(response => {
			console.log('** response : jotable 요청 담기 성공');
			let jnoid = 
			`<table border="1" style="width: 100%;">
				<tbody style="flex-direction:column;">
				<tr bgcolor="DarkGray" style="font-weight:bold; flex-direction:row;">
					<th style="width: 5vw;">ID</th>
					<th style="width: 30vw;">Password</th>
					<th style="width: 5vw;">Name</th>
					<th style="width: 5vw;">Age</th>
					<th style="width: 5vw;">Jno</th>
					<th style="width: 15vw;">Info</th>
					<th style="width: 10vw;">Point</th>
					<th style="width: 10vw;">Birthday</th>
					<th style="width: 5vw;">추천인</th>
				</tr>`;
			for(let s of response.data) {
				jnoid += 
				`<tr bgcolor="White" style="flex-direction:row;">
					<td style="width: 5vw;">${s.id}</td>
					<td style="width: 30vw;">${s.password}</td>
					<td style="width: 5vw;">${s.name}</td>
					<td style="width: 5vw;">${s.age}</td>
					<td style="width: 5vw;">${s.jno}</td>
					<td style="width: 15vw;">${s.info}</td>
					<td style="width: 10vw;">${s.point}</td>
					<td style="width: 10vw;">${s.birthday}</td>
					<td style="width: 5vw;">${s.rid}</td>
				</tr>`;
			}
			jnoid += `</tbody></table>`;
			
			document.getElementById("memberJo"+id).innerHTML = jnoid;
			document.getElementById("memberJo"+id).style.position = "absolute";
			document.getElementById("memberJo"+id).style.top = '100%';
			document.getElementById("memberJo"+id).style.left = '-40%';
		}).catch(err => {
			if(err.response.status == "502") alert(err.response.data);
			else alert("** 시스템 오류, 잠시후 다시하세요 => err.message : " + err.message);
		});
}

function hideMemberDetail(id) {
	let jnoid = document.getElementById("memberJo"+id);
	jnoid.style.position = "default";
	jnoid.innerHTML = "";
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

