package controllerM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_Login() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request 의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		String uri = "home.jsp";
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);
		
		// 2. Service 처리
		// => Service 의 selectOne : sno 확인
		// 확인결과 성공이면 password 확인
		// => 성공 : index.html
		// => 실패 : flowEx04_LoginForm.jsp (재로그인 유도)

		if (dto == null || !password.equals(dto.getPassword())) {
			uri = "member/loginForm.jsp";
			request.setAttribute("message", "로그인 실패, 다시입력하세요");
			System.out.println("로그인 실패");

			request.getRequestDispatcher(uri).forward(request, response);
			return;
		}

		// 3. View () : Forward or Redirect
		HttpSession session = request.getSession();

		session.setAttribute("sid", dto.getId());
		session.setAttribute("sname", dto.getName());
		System.out.println("로그인 성공");
		
		response.sendRedirect(uri);
	}

}
