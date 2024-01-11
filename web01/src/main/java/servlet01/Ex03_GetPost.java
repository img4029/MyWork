package servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost")
public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex03_GetPost() {
        super();

    } //생성자
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1) request 의 Parameter 처리
	    // => 한글처리, getParameter 전에 해야함
	    //   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
	    //   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
	    //     단, post 방식에서는 반드시 처리해야함 ) 
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		// => name 이 id 인 input Tag의 value 값을 return
		String name = request.getParameter("name");
		
		// => 해당하는 Parameter가 없는 경우 null 을 return
		// => Parameter는 존재하지만 값이 없는 경우 : null이 아니며 값은 없음(빈문자열)
		//    예시(http://localhost:8080/web01/getpost?id=banana&name=%EB%B0%94%EB%82%98%EB%82%98&password=)
		String password = request.getParameter("password");
		if (password != null && password.length() > 0) {
			System.out.println(" password => " + password);
		} else {
			System.out.println(" password is null ");
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style = 'color:blue;'> ** Get/Post Test **</h2>");
		out.print("<h3> => 전달된 Parameter 확인</h3>");
		out.print("<h3> => id : " + id + "</h3>");
		out.print("<h3> => name : " + name + "</h3>");
		out.print("</body></html>");
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
			doGet(request,response);
	} //doPost

} //class
