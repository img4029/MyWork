<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "mvcTest.StudentDTO,mvcTest.StudentService
					,java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jsp StudentList_MVC01 **</title>
</head>
<body>
	<%!
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();
		/* list = null; */
	%>
	<h2 style = 'color:blue;'>** Jsp StudentList_MVC01 **</h2>
	<table border=1 style = "width:100%;text-align:center;">
		<tr bgcolor="Lime" style="font-weight:bold;" >
			<th>sno</th>
			<th>name</th>
			<th>age</th>
			<th>jno</th>
			<th>info</th>
			<th>point</th>
		</tr>
		<%
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
		%>
	</table>
</body>
</html>