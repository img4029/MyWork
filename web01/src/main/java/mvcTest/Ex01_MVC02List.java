package mvcTest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public Ex01_MVC02List() {
        super();
    }

    // MVC 패턴2 StudentList 출력하기
    // => 요청 Service 처리
    // => 결과 출력(Java 스크립트)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// => 요청 Service 처리
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();

		// => 결과 출력 : Jsp, Java 스크립트
		// => Service 결과물을 Jsp 가 출력할수 있도록 Attribute 만들어 보관.
		//    request.setAttribute(...)
		request.setAttribute("myList",list);
		// => 
		String uri = "mvcTestJsp/ex01_MVC02List.jsp";
		uri = "mvcTestJsp/ex02_MVC02List.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
