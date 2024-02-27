<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Login Form **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/member.css">
<script src="/resources/myLib/inCheck.js"></script>
<style type="text/css">
	tr {
		height: 40px;
	}
	table tr:nth-child(1) td:nth-child(1)
	,table tr:nth-child(2) td:nth-child(1) {
		background-color: gold;
		text-align: center;
	}
	.login_container span{
		font-size: 12px;
		height : 17px;
		text-align: left;
		color:red;
	}
</style>
<script>
	let iCheck = { state: false, name: "아이디", message: "iMessage", id: "id" };
	let pCheck = { state: false, name: "비밀번호", message: "pMessage", id: "password" };
	let arr = [iCheck, pCheck];
	
 	onload = () => {
		let inputAll = document.querySelectorAll(".inputAll");
        let inputCk = document.querySelectorAll(".inputCk");
        for (let i = 0; i < inputAll.length; i++) {
            inputAll[i].addEventListener('keydown', (e) => {
                if (e.which == 13) {
                    if(inputAll[i].id != 'password') {
                    	e.preventDefault();
                    } else {
                    	pCheck.state = AllCheck(pCheck.id);
                    }
                   	
                    if (i != inputAll.length - 1) {
                        inputAll[i + 1].focus();
                    } else {
                        document.getElementById('fullAgreement').focus();
                    }
                }
            });
        }
        for (let i = 0; i < inputCk.length; i++) {
            inputCk[i].addEventListener('focusout', (e) => {
                arr[i].state = AllCheck(inputCk[i].id);
            });
        }
        console.log(document.getElementById('iMessage').value);
        if( document.getElementById('iMessage').value == "ID, 비밀번호 확인불가 다시 시도하세요"){
     		alert("ID, 비밀번호 확인불가 다시 시도하세요");
     	}
	} 
 	
 	function inCheck() {
		if (arr[0].state && arr[1].state ) {
			return true;
		} else {
			alert("아이디와 비밀번호 양식이 올바르지 않습니다.");
			return false;
		}
 	}
 	
</script>
</head>
<body>
<c:import url="/header"></c:import>
<main>
<form action="login" method="post">
	<div class="main_container">
    <!-- 로그인 3글자 -->
    <h3>로그인</h3>
    <!-- 메인 로그인 창 -->
    <div class="login_container">
        <div>
            <h3>회원 로그인</h3>
            <p>가입하신 아이디와 비밀번호를 입력해 주세요<br>
                비밀번호는 대소문자를 구분합니다.</p>
            <label class="idLabel" for="id"></label>
            <input class="textFont inputAll inputCk" type="text" id="id" name="id" placeholder="MEMBER ID"
                onfocus="this.placeholder = ''">
            <label class="pwLabel " for="password"></label>
            <span id="iMessage" class="eMessage">${message}</span>
            
            <input class="textFont inputAll inputCk" type="password" id="password" name="password" placeholder="PASSWORD"
                onfocus="this.placeholder = ''">
			<span id="pMessage" class="eMessage"></span>
			<input class="login_btn" type="submit" value="로그인" onclick="return inCheck();">
        </div>
        <div>
            <h3>회원가입</h3>
            <p>아직 회원이 아니신가요?<br>
                회원가입을 하시면 다양한 혜택을 편리하게 이용하실 수 있습니다.</p>
            <a class="member_btn" href="joinForm">JOIN-US</a>
            <p>아이디 혹은 비밀번호를 잊으셨나요?<br>
                간단한 정보를 입력 후 잃어버린 정보를 찾으실 수 있습니다.</p>
            <a class="findIdPw_btn" href="#">ID/PASSWORD</a>
            <div class="member_img">
                <a href="../lostpass/index.html">
                    <img src="https://danielstruth.com/design/dmcosmetic/img/log_pc.jpg" alt="로그인">
                </a>
            </div>
        </div>
    </div>
    <!-- 카카오 네이버 로그인 -->
        <div class="reference_login">
            <div>
                <a href="">
                    <img src="https://image.makeshop.co.kr/makeshop/d3/basic_simple/member/member_sns_login_kakao.jpg"
                        alt="카카오톡 로그인">
                </a>
            </div>
            <div>
                <a href="">
                    <img src="https://image.makeshop.co.kr/makeshop/d3/basic_simple/member/member_sns_login_naver.jpg"
                        alt="네이버 로그인">
                </a>
            </div>
        </div>
    </div>
</form>
</main>
<hr>
<div class="divBox">
	&nbsp;<a href="/spring02/home">Home</a>&nbsp;
	&nbsp;<a href="javascript:history.back();">이전으로</a>&nbsp;	
</div>
	
</body>
</html>