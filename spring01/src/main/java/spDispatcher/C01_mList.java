package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;


public class C01_mList implements Controller{
	
	@Autowired(required = false)
	MemberService service;
	//IOC/DI 적용, 자동주입, 이미 생성되어있어야함
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//MemberService service = new MemberService();
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mList", service.selectList());
		mv.setViewName("member/memberList");
		return mv;
	}

}
