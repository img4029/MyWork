package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radio")
public class Ex02_RadioText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_RadioText() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String gender = request.getParameter("gender");
		String mailcheck = request.getParameter("mailcheck");
		String content = request.getParameter("content");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.printf("성별 : %s,\n",gender);
		out.printf("메일수신 : %s,\n",mailcheck);
		
		if(content.equals("")) {
			out.print("가입인사 : 입력안함\n");
		} else {
			out.printf("가입인사 : %s\n",content);
		}
		out.print("<br><br><h2><a href = 'javascript:history.go(-1)'>다시입력하기</a><h2>");
	}

}
