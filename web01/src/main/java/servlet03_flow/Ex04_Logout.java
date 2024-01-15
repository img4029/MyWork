package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Ex04_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex04_Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		String uri = "index.html";
		System.out.println("로그아웃 되었습니다.");
		response.sendRedirect(uri);
	}

}
