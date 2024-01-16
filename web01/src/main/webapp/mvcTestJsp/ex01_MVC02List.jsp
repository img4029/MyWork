<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, mvcTest.StudentDTO"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02_List Java_Scriptlet **</title>
</head>
<body>
	<%-- <%
		List<StudentDTO> list = (List<StudentDTO>)request.getAttribute("myList");
	%> --%>
	<h2 style = 'color:blue;'>** MVC02_List Java_Scriptlet **</h2>
	<c:set var="list" value="${requestScope.myList}"/>
	<table border=1 style = "width:100%;text-align:center;">
		<tr bgcolor="Lime" style="font-weight:bold;" >
			<th>sno</th>
			<th>name</th>
			<th>age</th>
			<th>jno</th>
			<th>info</th>
			<th>point</th>
		</tr>
		<c:choose>
			<c:when test ="${list != null}">
				<c:forEach var="s" items="${list}">
					<tr>
						<td>${s.getSno()}</td>
						<td>${s.getName()}</td>
						<td>${s.getAge()}</td>
						<td>${s.getJno()}</td>
						<td style = "text-align:left;padding-left:10px;">
							${s.getInfo()}
						</td>
						<td>${s.getPoint()}</td>
					</tr>
	   			</c:forEach>
			</c:when>
			<c:otherwise>
            	<tr><td colspan = "6" style = "text-align:center;">
					selectList 결과가 1건도 없음
				</td></tr>
         </c:otherwise>
		</c:choose>
		<%-- <%
			if (list!=null) {
				for (StudentDTO s:list) { %>
					<tr>
						<td><%=s.getSno()%></td>
						<td><%=s.getName()%></td>
						<td><%=s.getAge()%></td>
						<td><%=s.getJno()%></td>
						<td style = "text-align:left;padding-left:10px;">
							<%=s.getInfo()%>
						</td>
						<td><%=s.getPoint()%></td>
					</tr>
		<%		}
			} else {%>
				<tr><td colspan = "6" style = "text-align:center;">
					selectList 결과가 1건도 없음
				</td></tr>
		<%  }
		%> --%>
	</table>
</body>
</html>