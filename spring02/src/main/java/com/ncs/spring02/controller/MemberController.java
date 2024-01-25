package com.ncs.spring02.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

// ** IOC/DI 적용 ( @Component 의 세분화 ) 
// => 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
// => @Controller :사용자 요청을 제어하는 Controller 클래스
//                 DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.    
//    -> 사용자 요청을 제어하는 Controller 객체로 인식하게 해줌.
//    -> DispatcherServlet이 해당 객체를 Controller 객체로 인식하게 해줌.
//    -> 이로 인해 메서드 handleRequest() 의 오버라이딩 의무 없어짐
//	  -> 이로 인해 메서드명, 매개변수, 리턴타입(ModelAndView, String, void 중 선택) 에 자유로워짐
//    -> 그리고 클래스 와 메서드 단위로 매핑이 가능한 @RequestMapping 사용가능
//    -> 그러므로 하나의 컨트롤러 클래스에 여러개의 매핑메서드 구현이 가능해짐
//    -> 그래서 주로 테이블(엔티티) 작성함 ( MemberController.java )

// =>  @Service : 비즈니스로직을 담당하는 Service 클래스
// =>  @Repository : DB 연동을 담당하는 DAO 클래스
//                   DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가



@Controller
@RequestMapping( value = "/member")
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;
	
	// => ver01 : return String
	//public String loginForm() {
	//	return "member/loginForm";
	//}
	
	// => ver02 : return void
	// => viewName 생략
	//	  - 요청명과 동일한 viewName 을 찾음
	//    - "/WEB-INF/views/member/loginForm.jsp" 완성됨
	@RequestMapping( value = {"/loginForm"}, method = RequestMethod.GET )
	public void loginForm() {}
	
	// ** login
	@RequestMapping( value = {"/login"}, method = RequestMethod.POST )
	public String login(HttpSession session,Model model,MemberDTO dto) {
		// 1. 요청분석
	    // => requst 로 전달되는 id, password 처리: 
	    //    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
	    //   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
	    // => 전달된 password 보관
	    String password = dto.getPassword();
	    String uri = "redirect:/home"; // 성공시
	      
	    // 2. 서비스 & 결과 처리
	    // => id 확인 
	    // => 존재하면 Password 확인
	    // => 성공: id, name은 session에 보관, home 으로
	    // => 실패: 재로그인 유도
		dto = service.selectOne( dto.getId() );
		
		if( dto != null && dto.getPassword().equals(password) ) {
			//성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			//실패
			model.addAttribute("message", "ID, 비밀번호 확인불가 다시 시도하세요");
			uri = "member/loginForm";
		}
		return uri;
	}
	
	// ** joinForm
	@RequestMapping( value = {"/joinForm"}, method = RequestMethod.GET )
	public void joinForm() {}
	
	// ** join
	@RequestMapping( value = {"/join"}, method = RequestMethod.POST )
	public String join(Model model, MemberDTO dto) {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 필터에서 request 처리는 매개변수로 자동화
		String uri = "member/loginForm"; // 성공시
		
		// 2. Service & 결과
		if(service.insert(dto) > 0) {
			model.addAttribute("message", "가입에 성공했습니다. 로그인 후 이용하세요.");
		} else {
			uri = "member/joinForm";
			model.addAttribute("message", "가입 실패, 다시입력하세요");
		}
		return uri;
	}
	
	// ** memberDetail or updateForm
	@RequestMapping( value = {"/detail"}, method = RequestMethod.GET )
	// 단일 Parmeter의 경우 @RequestParam("...")
	// String jCode = request.getParmeter("jCode")과 동일
	public String detail(HttpSession session,Model model, @RequestParam("jCode") String jCode) {
		String uri = "member/memberDetail";
		String id = (String)session.getAttribute("loginID");
		model.addAttribute("dto", service.selectOne(id));
		if("U".equals(jCode)) {
			uri = "member/updateForm";
		}
		return uri;
	}
	
	// ** update
	@RequestMapping( value = {"/update"}, method = RequestMethod.POST )
	public String update(HttpSession session, Model model, MemberDTO dto) {
		String uri = "member/memberDetail"; // 성공시
		model.addAttribute("dto", dto);
		if(service.update(dto) > 0) {
			session.setAttribute("loginName", dto.getName());
			model.addAttribute("message", "회원 정보 수정 성공!");
		} else {
			uri = "member/updateForm";
			model.addAttribute("message", "회원 정보 수정 실패, 다시입력하세요");
		}
		return uri;
	}
	
	// ** logout
	@RequestMapping( value = {"/logout"}, method = RequestMethod.GET )
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
	// ** deleteForm
	@RequestMapping( value = {"/deleteForm"}, method = RequestMethod.GET )
	public void deleteForm() {}
	
	// ** delete
	@RequestMapping( value = {"/delete"}, method = RequestMethod.POST )
	public String delete(HttpSession session, Model model) {
		if(service.delete((String)session.getAttribute("loginID")) > 0) {
			model.addAttribute("message", "회원 탈퇴에 성공했습니다. <br>1개월 후에 재가입 가능합니다.");
			session.invalidate();
		} else {
			model.addAttribute("message", "회원 탈퇴에 실패했습니다. <br>관리자에게 연락하세요.");
		}
		return "member/deleteForm";
	}
		
	// ** memberList
	@RequestMapping(value = { "/memberList" }, method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("mList", service.selectList());
	}
}
