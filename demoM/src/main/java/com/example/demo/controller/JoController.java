package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/jo")
@AllArgsConstructor
// => 모든 맴버변수를 초리화 하는 생성자 자동 추가 & 사용
//    그러므로 아래의 @Autowired는 생략 가능
public class JoController {
	//@Autowired
	JoService service;
	//@Autowired
	MemberService mservice;
	
	// ** joList
	@GetMapping("/joList")
	public void jList(Model model) {
		model.addAttribute("jList", service.selectJoList());
	}
	
	// ** memberDetail or updateForm
	@GetMapping("/detail")
	public String detail(HttpSession session,Model model, @RequestParam("jo") int jno) {
		String uri = "jo/joDetail";
		if(jno > 10) {
			uri = "jo/updateForm";
			jno -= 10;
		} else {
			model.addAttribute("list", mservice.selectJoList(jno));
		}
		model.addAttribute("dto", service.selectJoOne(jno));
		
		return uri;
	}
	
	// ** joinForm
	@GetMapping("/insertForm")
	public void joinForm() {}
	
	// ** join
	@PostMapping("/insert")
	public String join(Model model, JoDTO dto, RedirectAttributes rttr) {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 필터에서 request 처리는 매개변수로 자동화
		String uri = "redirect:joList"; // 성공시
		
		// 2. Service & 결과
		if(service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "조 등록에 성공했습니다");
		} else {
			uri = "jo/joinForm";
			model.addAttribute("message", "조 등록에 실패했습니다, 다시입력하세요");
		}
		return uri;
	}
	
	// ** update
	@PostMapping("/update")
	public String update(Model model, JoDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:detail?jo="+dto.getJno(); // 성공시
		model.addAttribute("dto", dto);
		if(service.update(dto) > 0) {
			rttr.addFlashAttribute("message", "조 정보 수정 성공!");
		} else {
			uri = "jo/updateForm";
			model.addAttribute("message", "조 정보 수정 실패, 다시입력하세요");
		}
		return uri;
	}
	
	// ** delete
	@GetMapping("/delete")
	public String delete(@RequestParam("jo") int jno, Model model, RedirectAttributes rttr) {
		if(service.delete(jno) > 0) {
			rttr.addFlashAttribute("message", "조 삭제에 성공했습니다.");

		} else {
			rttr.addFlashAttribute("message", "조 삭제에 실패했습니다.");
		}
		return "redirect:joList";
	}
}