package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C06_mDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		String uri = "home.jsp";
		if(service.delete((String)request.getSession().getAttribute("sid")) < 1) {
			uri = "member/deleteForm.jsp";
			request.setAttribute("message", "탈퇴 실패, 잠시후 다시 시도하세요");
			request.getRequestDispatcher(uri).forward(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect(uri);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
