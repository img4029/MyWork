package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adder")
public class Ex01_Adder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex01_Adder() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		// => request 처리 : 한글, Parameter
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			
			int add = num1 + num2;
			
			out.print(add);
		} catch (NumberFormatException e) {
			out.print("뭐든입력해주세요.try");
		}
		
//		String num1 = request.getParameter("num1");
//		String num2 = request.getParameter("num2");
//		if(num1 != null && num2 != null && num1.length()>0 && num2.length()>0) {
//			int add = Integer.parseInt(num1) + Integer.parseInt(num2);
//			out.print(add);
//		} else {
//			out.print("값을 입력해주세요.if");
//		}
		
		
		
		
		// 2) 서비스 & 결과 처리
		// => response 한글처리, 출력객체생성 & response
		
		
		
	} //doGet

}
