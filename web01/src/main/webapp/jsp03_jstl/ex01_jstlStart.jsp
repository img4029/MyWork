<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Start **</title>
</head>
<body>
<h2>** JSTL Start **</h2>
<pre><b>
=> Jstl Library 를 정의 (현재문서_Page 가 인식할 수 있도록)
	디렉티브 taglib 에 uri=".." prefix=".."
	
1. 출력 : out Tag
=> Java의 out객체, 표현식, EL역할
<c:out value="~~ Hello JSTL !!! 안녕 ~~"/>


2. 변수 정의
=> set
<c:set var="name" value="홍길동"/>
<c:set var="age" value="22"/>
3. 변수 출력
=> JSTL의 out Tag
* name = <c:out value="${name}"/>
* age = <c:out value="${age}"/>
=> EL
* name = ${name}
* age = ${age}
* age*100 = ${age*100}

=> Java는 Jstl 변수와 서로 호환되는가 : 안된다.
<%-- * name = <%=name%> --%>

4. 연산적용
<c:set value="${age+age}" var="add" />
\${add} = ${add}
<c:set value="${name==age}" var="bool" />
\${bool} = ${bool}
<c:set value="${age>add ? age:add}" var="max" />
\${max} = ${max}


5. 변수삭제
<c:remove var="add"/>
\${empty_add} = ${empty add}
\${empty_age} = ${empty age}
=> 정의하지않은 변수 삭제
<c:remove var="password"/>

6. 우선순위 비교
=> JSTL 변수와 pageScope Attribute
=> 나중에 정의한 값이 우선 적용
	(set변수, attribute 중 나중에 정의된 값 우선) 
<%-- <% // pageScope 에 Attribute 를 정의 후 TEST
	pageContext.setAttribute("name", "그린컴");%> --%>
* Test1) name 정의 순서 : set -> pageScope
\${name} = ${name}

* Test2) set 으로 name 재정의
<c:set var="name" value="NEW_홍길동"/>
\${name} = ${name} : 나중값 우선

</b></pre>

</body>
</html>