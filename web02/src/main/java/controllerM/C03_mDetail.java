package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C03_mDetail() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne((String)request.getSession().getAttribute("sid"));
		String uri = "member/memberDetail.jsp";
		request.setAttribute("dto", dto);
		
		if("U".equals(request.getParameter("jCode"))) {
			uri = "member/updateForm.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}
}
