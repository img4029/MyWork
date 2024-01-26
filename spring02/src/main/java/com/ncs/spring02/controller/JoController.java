package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.JoService;

@Controller
@RequestMapping( value = "/jo")
public class JoController {
	
	@Autowired(required = false)
	JoService service;
	
	// ** joList
	@RequestMapping(value = { "/joList" }, method = RequestMethod.GET)
	public void jList(Model model) {
		model.addAttribute("jList", service.selectJoList());
	}
	
	// ** memberDetail or updateForm
	@RequestMapping( value = {"/detail"}, method = RequestMethod.GET )
	public String detail(HttpSession session,Model model, @RequestParam("jo") int jno) {
		String uri = "jo/joDetail";
		if(jno > 10) {
			uri = "jo/updateForm";
			jno -= 10;
		}
		model.addAttribute("dto", service.selectJoOne(jno));
		return uri;
	}
	
	// ** joinForm
	@RequestMapping( value = {"/insertForm"}, method = RequestMethod.GET )
	public void joinForm() {}
	
	// ** join
	@RequestMapping( value = {"/insert"}, method = RequestMethod.POST )
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
	@RequestMapping( value = {"/update"}, method = RequestMethod.POST )
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
	@RequestMapping( value = {"/delete"}, method = RequestMethod.GET )
	public String delete(@RequestParam("jo") int jno, Model model, RedirectAttributes rttr) {
		if(service.delete(jno) > 0) {
			rttr.addFlashAttribute("message", "조 삭제에 성공했습니다.");

		} else {
			rttr.addFlashAttribute("message", "조 삭제에 실패했습니다.");
		}
		return "redirect:joList";
	}
}