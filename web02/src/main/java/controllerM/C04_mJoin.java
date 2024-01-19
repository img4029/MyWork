package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mjoin")
public class C04_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C04_mJoin() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 1. 요청분석
    	// => 한글, request 의 Parameter 처리
    	// => 성공: 로그인 유도 (loginForm.jsp)
    	// => 실패: 재가입 유도 (joinForm.jsp)
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String uri = "member/loginForm.jsp";
		String id = request.getParameter("id").trim();
//		String idc = request.getParameter("idCk").trim();
		String pw = request.getParameter("password").trim();
//		String pwc = request.getParameter("passwordCk").trim();
		String name = request.getParameter("name").trim();
		String age = request.getParameter("age").trim();
		String jno = request.getParameter("jno").trim();
		String info = request.getParameter("info").trim();
		String point = request.getParameter("point").trim();
		String birthday = request.getParameter("birthday").trim();
		String rid = request.getParameter("rid").trim();
		
		// 2. Service 처리
		// => Service, DTO 객체 생성
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
//		if(!(id.length() > 3 && id.length() < 11) || !idc.equals("true")) {
//			uri = "member/joinForm.jsp";
//			request.setAttribute("message", "가입 실패, ID가 조건에 맞지않습니다.");
//			request.getRequestDispatcher(uri).forward(request, response);
//			return;
//		}
		
//		if(!pw.equals(pwc)) {
//			uri = "member/joinForm.jsp";
//			request.setAttribute("message", "가입 실패, 비밀번호와 비밀번호확인이 일치하지않습니다.");
//			request.getRequestDispatcher(uri).forward(request, response);
//			return;
//		}
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setAge(Integer.parseInt(age));
		dto.setJno(Integer.parseInt(jno));
		dto.setInfo(info);
		dto.setPoint(Double.parseDouble(point));
		dto.setBirthday(birthday);
		dto.setRid(rid);
		
		if(service.insert(dto) < 1) {
			uri = "member/joinForm.jsp";
			request.setAttribute("message", "가입 실패, 다시입력하세요");
		}
		
		// 3. View () : Forward or Redirect
		request.getRequestDispatcher(uri).forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request,response);
	}
}
