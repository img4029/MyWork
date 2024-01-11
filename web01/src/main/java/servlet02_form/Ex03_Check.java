package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex03_Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		try {
			String[] str = request.getParameterValues("gift");

			for (String s : str) {
				out.print(s + " ");
			}
		} catch (NullPointerException e) {
			out.print("아무것도 선택하지않았습니다.try");
		}
		
//		if(request.getParameterValues("gift") != null) {
//			String[] str = request.getParameterValues("gift");
//
//			for (String s : str) {
//				out.print(s + " ");
//			}
//		} else {
//			out.print("아무것도 선택하지않았습니다.if");
//		}
		
	}

}
