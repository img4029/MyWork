package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/jo")
@AllArgsConstructor
// => 모든 맴버변수를 초리화 하는 생성자 자동 추가 & 사용
//    그러므로 아래의 @Autowired는 생략 가능
public class JoController {

	JoService service;
	MemberService mservice;
	
	// ** joList
	@GetMapping("/joList")
	public void jList(Model model) {
		model.addAttribute("jList", service.selectList());
	}
	
	// ** memberDetail or updateForm
	@GetMapping("/detail")
	public String detail(HttpSession session,Model model, @RequestParam("jo") int jno, @RequestParam("jcode") String jcode) {
		String uri = "jo/joDetail";
		if("U".equals(jcode)) {
			uri = "jo/updateForm";
		} else {
			model.addAttribute("list", mservice.findByJno(jno));
		}
		model.addAttribute("dto", service.findByJno(jno));
		
		return uri;
	}
	
	// ** joinForm
	@GetMapping("/insertForm")
	public void joinForm() {}
	
	// ** join
	@PostMapping("/insert")
	public String join(Model model, Jo entity, RedirectAttributes rttr) {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 필터에서 request 처리는 매개변수로 자동화
		String uri = "redirect:joList"; // 성공시
		
		// 2. Service & 결과
		
		try {
			log.info(" member insert 성공 => " + service.save(entity));
			rttr.addFlashAttribute("message", "가입에 성공했습니다. 로그인 후 이용하세요.");
		} catch (Exception e) {
			uri = "member/joinForm";
			log.info(" member insert Exception => " + e.toString());
			model.addAttribute("message", "가입 실패, 다시입력하세요");
		}

		return uri;
	}
	
	// ** update
	@PostMapping("/update")
	public String update(Model model, Jo entity, RedirectAttributes rttr) {
		String uri = "redirect:detail?jo="+entity.getJno()+"&jcode=D"; // 성공시
		model.addAttribute("dto", entity);
		
		try {
			log.info(" member insert 성공 => " + service.save(entity));
			rttr.addFlashAttribute("message", "가입에 성공했습니다. 로그인 후 이용하세요.");
		} catch (Exception e) {
			uri = "member/joinForm";
			log.info(" member insert Exception => " + e.toString());
			model.addAttribute("message", "가입 실패, 다시입력하세요");
		}
		
		return uri;
	}
	
	// ** delete
	@GetMapping("/delete")
	public String delete(@RequestParam("jo") int jno, Model model, RedirectAttributes rttr) {
		try {
			service.deleteById(jno);
			log.info(" Jo delete 성공 " );
		} catch (Exception e) {
			log.info(" Jo delete Exception => " + e.toString());
			rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. <br>관리자에게 연락하세요.");
		}

		return "redirect:joList";
	}
}