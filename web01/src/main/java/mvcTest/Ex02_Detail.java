package mvcTest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/detail")
public class Ex02_Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("dto", new StudentService().selectOne((Integer)request.getSession().getAttribute("sid")));
		request.getRequestDispatcher("mvcTestJsp/ex03_MVC02Detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
