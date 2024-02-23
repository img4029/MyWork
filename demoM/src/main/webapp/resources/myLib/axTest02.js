/*
// ** Ajax_REST API Join Test **
// => axios
// => file_UpLoad 가 포함된 formData 처리
// => joinForm 요청: MemberController -> 웹 Page return
// => join 요청: RTestController -> 결과 Text return  

// => Axios 메서드형식 적용 (00_AJAX(공유).pptx 16p)
/*   - GET   : axios.get( url[, config] )
   	 - POST  : axios.post( url, data[, config] )
     - PUT   : axios.put( url, data[, config] )
     - PATCH : axios.patch( url[, data[, config]] )
     - DELETE: axios.delete( url[, config] )     */

"use strict"

// 1) joinForm 요청
// => response: 웹 Page
function rsJoinf(){
	let url = "/member/joinForm"
	
	axios.get(url
	).then(response => {
		console.log('** response : joinForm 성공');
		
		document.getElementById('resultArea1').innerHTML = response.data
	}).catch(err => {
		alert(`** response : joinForm 실패 => ${err.message}`);
	});
	document.getElementById('resultArea1').innerHTML = "";
} //rsJoinf

// 2) join 처리
// => fileUpload 포함된 경우 : JS 의 내장객체 FormData 에 담아서 전송
// => Data전송: JS 의 FormData 사용, 요청_headers "Content-Type" 변경

function axiJoin(){
	// 2.1) Data 전송준비
	let formData = new FormData( document.getElementById('myform') )
	
	
	// 2.2) Axios 요청처리
	let url = "/rest/rsjoin";
	axios.post(url, formData, {
		headers: { 'content-type': 'multipart/form-data' }
	}).then(response => {
		alert(`** Join 성공 => ${response.data}`)
		location.reload;
	}).catch(err => {
		if( err.response.status == '502' ) alert(" ~ 입력 오류 다시하세요")
		else alert(`시스템 오류 다시하세요 => ${err.message}`)
	});
	document.getElementById('resultArea1').innerHTML = "";
}
