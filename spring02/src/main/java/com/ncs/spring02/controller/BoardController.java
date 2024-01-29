package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	BoardService service;
	
	// ** joList
	@GetMapping("/boardList")
	public void jList(Model model) {
		model.addAttribute("bList", service.selectList());
	}
	
	// ** memberDetail or updateForm
	@GetMapping("/detail")
	public String detail(HttpSession session,Model model,
			@RequestParam("seq") int seq, @RequestParam("jCode") String jCode) {
		String uri = "board/boardDetail";
		if(jCode.equals("U")) {
			uri = "board/boardUpdate";
		} else {

		}
		model.addAttribute("dto", service.selectOne(seq));
		
		return uri;
	}
	
	// ** joinForm
	@GetMapping("/boardInsert")
	public void joinForm() {}
	
	// ** insert
	@PostMapping("/insert")
	public String join(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 필터에서 request 처리는 매개변수로 자동화
		String uri = "redirect:boardList"; // 성공시
		
		// 2. Service & 결과
		if(service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "조 등록에 성공했습니다");
		} else {
			uri = "board/joinForm";
			model.addAttribute("message", "조 등록에 실패했습니다, 다시입력하세요");
		}
		return uri;
	}
	
	// ** update
	@PostMapping("/update")
	public String update(Model model, BoardDTO dto, RedirectAttributes rttr,@RequestParam("seq") int seq) {
		String uri = "redirect:detail?seq="+dto.getSeq()+"&&jCode=D"; // 성공시
		model.addAttribute("dto", dto);
		if(service.update(dto) > 0) {
			rttr.addFlashAttribute("message", "게시글 수정 성공!");
		} else {
			uri = "board/boardUpdate";
			model.addAttribute("message", "게시글 수정 실패, 다시입력하세요");
		}
		return uri;
	}
	
	// ** delete
	@GetMapping("/delete")
	public String delete(@RequestParam("seq") int seq, Model model, RedirectAttributes rttr) {
		if(service.delete(seq) > 0) {
			rttr.addFlashAttribute("message", "게시글 삭제에 성공했습니다.");

		} else {
			rttr.addFlashAttribute("message", "게시글 삭제에 실패했습니다.");
		}
		return "redirect:boardList";
	}
}
