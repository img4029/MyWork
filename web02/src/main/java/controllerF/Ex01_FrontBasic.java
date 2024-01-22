package controllerF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

//** FrontController 패턴 1.
//=> 대표 컨트롤러 1개만 서블릿으로 작성
// 모든 요청을 이 대표컨트롤러(FrontController) 가 받도록 함.
// 나머지 컨트롤러는 일반 클래스로 작성 (2단계, Factory 패턴 적용)

//=> 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리
// 코드가독성, 모듈의 재사용성 떨어짐 (1단계) 

//=> Factory 패턴 적용
// 각각의 서비스를 일반클래스(컨트롤러)로 작성 Factory로부터 제공받음

@WebServlet(urlPatterns = {"*.do"})
public class Ex01_FrontBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex01_FrontBasic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		// => url 분석후 요청명 확인
		// => 한글처리
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		// ~URL => http://localhost:8080/web02/test.do
		// ~URI => /web02/test.do
		uri = uri.substring(uri.lastIndexOf("/")) ;
		// uri => /test.do
		System.out.println("** ~URL => "+ request.getRequestURL());
		System.out.println("** ~URI => "+ request.getRequestURI());
		System.out.println("** ~uri => "+ uri);
		
		// 2) Service 실행
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
		
		switch (uri) {
		case "/mlist.do":
			System.out.println("mlist.do");
			request.setAttribute("mList", service.selectList());
			uri = "member/memberList.jsp";
			break;
		case "/mdetail.do":
			request.setAttribute("dto", service.selectOne((String)request.getSession().getAttribute("sid")));
			uri = "member/memberDetail.jsp";
			break;
		default:
			request.setAttribute("mesaage", "요청에 해당하는 서비스가 없습니다.");
			uri = "home.jsp";
			break;
		}
		
		// 3) View 처리
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
