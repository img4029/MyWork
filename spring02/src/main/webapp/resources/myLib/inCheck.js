"use strict"
/**
** 입력값의 무결성 확인
** member 무결성 확인사항
// ID : 길이(4~10), 영문자,숫자 로만 구성
// Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
// Password2: 재입력후 Password 와 일치성 확인
// Name : 길이(2이상), 영문 또는 한글로 만 입력
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )  
// BirthDay : 입력 여부 확인  ( length == 10 )
// Point : 실수 ( 구간설정 100 ~ 10000 까지만 가능 )
// Jno : select 를 이용 (X)
// Info : (X)

** 작성 규칙
   => JavaScript function 으로 정의 하고 
      결과를 true or false 로 return
   => 정규식을 활용한다.
   
** match Test
   => 아래 조건에 true -> not (!)  match 적용해보면
   => 정확하지 않으므로 (부적절, replace 를 사용)
        ...       
        } else if (!id.match(/[a-z.0-9]/gi)) {
            alert(' ID는 영문자와 숫자로만 입력하세요. !!!')
            return false;
        }    
 */
function AllCheck(id) {
    let all = document.getElementById(id).value;
    let regPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    let regPass2 = /[ "']/;
    console.log(id);
    switch (id) {
        case "name":
			if (all.length < 2 || all.length > 6) {
                document.getElementById('iMessage').innerHTML = '이름은 2~6 글자 입니다.';
                return false;
            } else if (all.replace(/[가-힣]/g,'').length > 0) {
                document.getElementById('nMessage').innerHTML = '이름은 한글만 입력 가능합니다.';
                return false;
            } else {
                document.getElementById('nMessage').innerHTML = '';
                return true;
            }
        case "id":
            if (all.length < 4 || all.length > 10) {
                document.getElementById('iMessage').innerHTML = '아이디는 4~10 글자 입니다.';
                return false;
            } else if (all.replace(/[a-z.0-9]/gi,'').length > 0) {
                // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
                document.getElementById('iMessage').innerHTML = '아이디는 영문과 숫자만 가능합니다.';
                return false;
            } else {
                document.getElementById('iMessage').innerHTML = '';
                return true;
            }
        case "password":
			if (all.length < 4 || all.length > 10) {
                document.getElementById('pMessage').innerHTML = '비밀번호는 4~10 글자 입니다.';
                return false;
            } else if (all.replace(/[a-z.0-9.!@#$%^*+=-]/gi,'').length > 0) { 
                document.getElementById('pMessage').innerHTML = '비밀번호는 영문과 숫자 특수문자만 가능합니다.';
                return false;
            } else {
                document.getElementById('pMessage').innerHTML = '';
                return true;
            }
        case "password2":
			if (document.getElementById('password').value != document.getElementById('password2').value) {
                document.getElementById('p2Message').innerHTML = '비밀번호와 비밀번호 확인이 같지않습니다.';
                return false;
            }  else {
                document.getElementById('p2Message').innerHTML = '';
                return true;
            }
            break;
        case "age":

            break;
        case "point":

            break;
        case "birthday":

            break;
    }
    return true;
}


/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 <!-- 
 ** Good 정리
 => https://inpa.tistory.com/entry/JS-📚-정규식-RegExp-누구나-이해하기-쉽게-정리
 
** 정규 표현식 (정규식:Regular Expression) 객체 : RegExp
=> 자바스크립트의 기본 내장 객체 중의 하나
=> 특정한 규칙을 가진 문자열 집합을 표현하는데 사용하는 형식
* 생성
   var regExp1= new RegExp('text') ;
   var regExp2= /text/ ; 
* 메서드   
   test() : 정규식과 일치하는 문자열이 있으면 true 아니면  false 를 return 
   exec() : 정규식과 일치하는 문자열을 return 
* 예제   
   var regExp= /script/ ; 
   var s='Javascript jQuery Ajax';
   
   var output = regExp.test(s) ;
   alert(output) ; 
* 그러나 주로 문자열의 메서드와 같이 사용됨
    
/.../ : 정규식 RegExp 의 리터럴

** 플래그 문자 
g : global, 전역비교
i : 대문자는 소문자 변환후 비교 (대/소문자 구분 없음)
m : 여러줄의 검사 수행
   ( 각줄을 개별문자로 인식하고 검사해줌
    예 : 'JavaScript\njQuery\nAjax' )

\. : . 문자 (. 는 한 문자를 의미하나 \. 는 . 문자를 의미함)
a-z : abcdefghijklmnopqrstuvwxyz 와 같음
0-9 : 0123456789 와 같음
: : : 문자
_ : _ 문자
- : - 문자
[~.~] : ~ 와 ~ , Or 의 묶음
[..] : Or 의 묶음. 안에 기록된 1가지외 중복 적용됨.
[^...] : 내부내용의 부정. 기록된 이외의 것을 찾음.
+ : 하나 이상의 반복적용. (단어(?) 찾음)

*/