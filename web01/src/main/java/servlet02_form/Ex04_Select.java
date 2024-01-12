package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Select() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(job != null && job.length() > 0) {
			out.printf("직 업 : %s,\n",job);
		} else {
			out.print("직 업 : 선택안함,\n");
			
		}
		
		if(interest != null && interest.length > 0) {
			out.print("관심분야 : ");
			for (String s : interest) {
				out.print(s + " ");
			}
		} else {
			out.print("관심분야 : 선택안함\n");
		}
		out.print("<br><br><h2><a href = 'javascript:history.go(-1)'>다시입력하기</a><h2>");
	}

}
