<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/idinfoSub.css">
<script src="/resources/myLib/inCheck.js"></script>
<script type="text/javascript">
	let mpCheck = { state: true, name: "현재 비밀번호", message: "mpMessage", id: "mpassword" };
	let pCheck = { state: false, name: "비밀번호", message: "pMessage", id: "password" };
	let p2Check = { state: false, name: "비밀번호 확인", message: "p2Message", id: "password2" };
	let arr = [mpCheck, pCheck, p2Check];
	
	onload = () => {
		document.getElementById('mpassword').focus();
		let inputAll = document.querySelectorAll(".inputAll");
        let inputCk = document.querySelectorAll(".inputCk");
        for (let i = 0; i < inputAll.length; i++) {
            inputAll[i].addEventListener('keydown', (e) => {
                if (e.which == 13) {
                    e.preventDefault();
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
		
		if("${message}"=="T"){
			//opener.document.getElementById('message').innerHTML = "변경이 완료되었습니다";
			opener.open("/spring02/member/loginForm","_self","");
			window.close();
		}
	}
	
	function inCheck() {
		if (arr[0].state && arr[1].state && arr[2].state) {
                //submit 진행
			if (confirm("정말 비밀번호를 수정하십니까? (Yes:확인/No:취소)")) {
				return true;
			} else {
                alert("수정이 취소되었습니다.");
				return false;
			}
		} else {
			return false;
		}
	}
</script>
<style type="text/css">
	.idinfoSubContainer {
	    padding: 100px 100px;
	    margin: auto;
	    padding-top: 200px;
	    min-width: 1200px;
	    max-width: 1200px;
	}
	.idinfo_grid>div:nth-child(2n) {
		width:800px;
	}
	
</style>
</head>
<body>
	<form action="pwUpdate" method="post">
		<div class="idinfoSubContainer">
			<div>
				<h3>비밀번호 변경</h3>
				<div class="idinfo_grid">
					<div class="grid_head"><span>*</span>현재 비밀번호</div>
                    <div>
                        <input class="inputAll inputCk" value="" type="password" name="mpassword"
                            id="mpassword" size="15">
                        <span id="mpMessage" class="eMessage"></span>
                    </div>
                    <div class="grid_head"><span>*</span>비밀번호</div>
                    <div>
                        <input class="inputAll inputCk" value="" type="password" name="password"
                            id="password" size="15">
                        <span id="pMessage" class="eMessage">${message}</span>
                    </div>
                    <div class="grid_head"><span>*</span>비밀번호 확인</div>
                    <div>
                        <input class="inputAll inputCk" value="" type="password" name="password2"
                            id="password2" size="15">
                        <span id="p2Message" class="eMessage"></span>
                    </div>
				</div>
			</div>
			<button type="submit" value=false class="registrationComplete"id="submitTag" onclick="return inCheck();">비밀번호 수정</button>
		</div>
	</form>
	
</body>
</html>