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
    let regPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{4,10}$/;
    let regPass2 = /^(?=.*[a-zA-Z.0-9.!@#$%^*+=-])/;
    let regPass3 = /[!@#$%^*+=-]/g;
    switch (id) {
        case "name":
            if (all.length < 2) {
                document.getElementById('nMessage').innerHTML = '이름은 2 글자 이상 입니다.';
                return false;
            } else if (all.replace(/[a-z.가-힣]/gi, '').length > 0) {
                document.getElementById('nMessage').innerHTML = '이름은 영어와 한글만 입력 가능합니다.';
                return false;
            } else {
                document.getElementById('nMessage').innerHTML = '';
                return true;
            }
        case "id":
            if (all.length < 4 || all.length > 10) {
                document.getElementById('iMessage').innerHTML = '아이디는 4~10 글자 입니다.';
                return false;
            } else if (all.replace(/[a-z.0-9]/gi, '').length > 0) {
                // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
                document.getElementById('iMessage').innerHTML = '아이디는 영문과 숫자만 입력 가능합니다.';
                return false;
            } else {
                document.getElementById('iMessage').innerHTML = '';
                return true;
            }
        case "password":
            if (all.length < 4 || all.length > 10) {
                document.getElementById('pMessage').innerHTML = '비밀번호는 4~10 글자 입니다.';
                return false;
            } else if (!regPass2.test(all)) {
                document.getElementById('pMessage').innerHTML = '비밀번호는 영문과 숫자 특수문자로 구성되어야 합니다.';
                return false;
            } else if (!regPass3.test(all)) {
                document.getElementById('pMessage').innerHTML = '비밀번호는 특수문자가 반드시 포함되어야 합니다.';
                return false;
            } else {
                document.getElementById('pMessage').innerHTML = '';
                return true;
            }
        case "password2":
            if (document.getElementById('password').value != document.getElementById('password2').value) {
                document.getElementById('p2Message').innerHTML = '비밀번호와 비밀번호 확인이 같지 않습니다.';
                return false;
            } else {
                document.getElementById('p2Message').innerHTML = '';
                return true;
            }
        case "age":
            // Age (정수)
            // => 정수의 조건: 숫자이면서 소수점이 없어야함
            // => Number.isInteger(n) : n 이 정수일때만 true
            //   -> 단, n 은 숫자 Type 이여야함
            //   -> value 속성의 값은 문자 Type 이므로 숫자화_parseInt 가 필요함
            //   -> 단, parseInt(age) 사용시 주의
            //      - 실수의 경우는 정수만 사용(123.56 -> 123)
            //      - 숫자 뒤쪽에 문자 포함시 앞쪽의 숫자만 가져와 return (123abc -> 123)
            //      - 문자로 시작하면 문자로 취급, NaN(Not a Nimber) 을 return (abc123 -> NaN)
            // => 숫자 아닌값이 있는지 확인, Number.isInteger(....) 확인
            // console.log(all);
            // console.log(`** parseInt(all) => ${parseInt(all)}`);
            // console.log(`** Number.isInteger(all) => ${Number.isInteger(all)}`);
            // console.log(`** Number.isInteger(parseInt(all)) => ${Number.isInteger(parseInt(all))}`);
            if (all.replace(/[^0-9]/g, '').length < all.length || !Number.isInteger(parseInt(all))) {
                document.getElementById('aMessage').innerHTML = '나이는 정수만 입력 가능합니다.';
                return false;
            } else {
                document.getElementById('aMessage').innerHTML = '';
                return true;
            }

        /*            if (all.replace(/[0-9]/g, '').length > 0) {
                        document.getElementById('aMessage').innerHTML = '나이는 숫자만 입력 가능합니다.';
                        return false;
                    } else if (all.length < 1 || all.length > 3) {
                        // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
                        document.getElementById('aMessage').innerHTML = '나이는 1~3 글자 입니다.';
                        return false;
                    } else {
                        document.getElementById('aMessage').innerHTML = '';
                        return true;
                    }*/
        case "jno":
            if (all == 0) {
                document.getElementById('jMessage').innerHTML = '조를 선택해주세요.';
                return false;
            } else {
                document.getElementById('jMessage').innerHTML = '';
                return true;
            }

        case "point":
            // Point
            // => 정수 또는 실수 허용
            // => 범위: 100 ~ 10000
            // => parseFloat(point)
            //      -> 오류 또는 입력값이 없는 경우 NaN return
            //      -> 확인 : Number.isNaN(parseFloat(point)) 
            //    	-> 단, 숫자로 시작하면 뒤쪽에 문자가 섞여있어도 숫자값만 사용함 ( NaN 을 return 하지않음 )
			// console.log(`** all => ${all}`);
            // console.log(`** parseFloat(all) => ${parseFloat(all)}`);
            // console.log(`** Number.isNaN(all) => ${Number.isNaN(all)}`);
            // console.log(`** Number.isNaN(parseFloat(all)) => ${Number.isNaN(parseFloat(all))}`);
            // console.log(`** all.replace(/[^0-9./.]/g, '').length => ${all.replace(/[^0-9.\.]/g, '').length} < ${all.length}`);
			console.log(`** all.replaceAll('.','') => ${all.replaceAll('.','')}`);
            if (Number.isNaN(parseFloat(all)) || all.replace(/[^0-9.\.]/g, '').length < all.length) {
                document.getElementById('oMessage').innerHTML = 'Point는 실수와 정수만 입력 가능합니다.';
                return false;
            } else if (parseFloat(all) < 100 || parseFloat(all) > 10000) {
                // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
                document.getElementById('oMessage').innerHTML = 'Point는 100 ~ 10000 입니다.';
                return false;
            } else if (all.length-1 > all.replaceAll('.','').length) {
                // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK  
                document.getElementById('oMessage').innerHTML = '" . " 은 하나만 입력하세요';
                return false;
            } else {
                document.getElementById('oMessage').innerHTML = '';
                return true;
            }
        case "birthday":
            let InputDate = new Date(all);
            let today = new Date();

            if (InputDate > today) {
                document.getElementById('bMessage').innerHTML = '생년월일이 올바르지 않습니다, 다시 입력해주세요.';
                return false;
            } else {
                document.getElementById('bMessage').innerHTML = '';
                return true;
            }
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