package servlet03_flow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex04_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request 의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		String sno = request.getParameter("sno") ;
		String name = request.getParameter("name");
		String uri = "index.html";
		
		StudentService service = new StudentService();
		StudentDTO dto = service.selectOne(Integer.parseInt(sno));
		
	    // 2. Service 처리
		// => Service 의 selectOne : sno 확인
		//	  확인결과 성공이면 name 확인
		// => 성공 : index.html 
		// => 실패 : flowEx04_LoginForm.jsp (재로그인 유도)
		if(!dto.getName().equals(name) || dto == null) {
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			return;
		} 
		
	    // 3. View () : Forward
		HttpSession session = request.getSession();
		session.setAttribute("sid", sno);
		session.setAttribute("sname", name);
		session.setAttribute("sage", dto.getAge()+"");
		request.getRequestDispatcher(uri).forward(request, response);
	}

}
