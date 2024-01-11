package servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public Ex02_MVC01List() {
        super();
    }

    // MVC 패턴1 StudentList 출력하기
    // => 요청 Service 처리
    // => 결과 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// => 요청 Service 처리
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();
//		List<StudentDTO> list = null;
		// => 결과 출력 : 출력내용을 Response 객체의 Body 영역에 write
		//   " 한글처리
		//   " 출력객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("<h2 style = 'color:blue;'> ** Servlet_MVC1 StudentList **</h2>");
//		out.print("<h3> => ContextPath : " + request.getContextPath() + "</h3>");
//		out.print("<h3> => 여기는 Get 입니다. </h3>");
		out.print("<table border=1>");
		out.print("<tr>"
				+ "<td>sno</td><td>name</td><td>age</td>"
				+ "<td>jno</td><td>info</td><td>point</td>"
				+ "</tr>");
		if (list!=null) {
			for (StudentDTO s:list) {
				out.print("<tr>"
						+ "<td>"+s.getSno()+"</td><td>"+s.getName()+"</td><td>"+s.getAge()+"</td>"
						+ "<td>"+s.getJno()+"</td><td>"+s.getInfo()+"</td><td>"+s.getPoint()+"</td>"
						+ "</tr>");	
			}
			out.print("</table>");
			out.print("</body></html>");
		}else {
			out.print("<tr><td colspan="+'"'+"6"+'"'+">selectList 결과가 1건도 없음</td></tr>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
