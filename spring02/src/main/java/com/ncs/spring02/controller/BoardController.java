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
import com.ncs.spring02.service.JoService;

import lombok.AllArgsConstructor;
import pageTest.Criteria;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	BoardService service;
	JoService jservice;
		
	// ** Board Check_List
	@GetMapping("/bCheckList")
	public String bCheckList(Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri = "board/bPageList";
		// 1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		// => ver02: Ver01 + searchType, keyword 도 동일하게 cri에 set
		cri.setSnoEno();
		
		// 2) Service
		// => 출력 대상인 Rows 를 select
		// => ver01, 02 모두 같은 service 메서드 사용,
		//    mapper interface 에서 사용하는 Sql 구문만 교체
		// 즉, BoardMapper.xml 에 새로운 Sql 구문 추가 BoardMapper.java interface 수정
		if(cri.getCheck() != null && cri.getCheck().length<1) cri.setCheck(null);
		model.addAttribute("bList", service.bCheckList(cri));
		model.addAttribute("jList", jservice.selectJoList());
		// 3) View처리 : pageMaker 이용
		// => cri, totalRowsConut (Read from DB)
		pageMaker.setCri(cri);
		pageMaker.setTotalRowsCount(service.bCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	}//bCheckList
	
	// ** bPageList
	// => ver01 : Criteria 사용
	// public void bPageList(Model model, Criteria cri, PageMaker pageMaker) {
	// => ver02 : searchCriteria 사용(검색기능 추가)
	@GetMapping("/bPageList")
	public void bPageList(Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		// => ver02: Ver01 + searchType, keyword 도 동일하게 cri에 set
		cri.setSnoEno();
		
		// 2) Service
		// => 출력 대상인 Rows 를 select
		// => ver01, 02 모두 같은 service 메서드 사용,
		//    mapper interface 에서 사용하는 Sql 구문만 교체
		// 즉, BoardMapper.xml 에 새로운 Sql 구문 추가 BoardMapper.java interface 수정
		model.addAttribute("bList", service.bPageList(cri));
		model.addAttribute("jList", jservice.selectJoList());
		// 3) View처리 : pageMaker 이용
		// => cri, totalRowsConut (Read from DB)
		pageMaker.setCri(cri);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
	}//bPageList
	
	// ** boardList
	@GetMapping("/boardList")
	public void jList(Model model) {
		model.addAttribute("bList", service.selectList());
	}//jList
	
	// ** Detail or Update
	// => 글요청 처리중, 글을 읽기전
	// => 조회수 증가
	//	  -> loginID 와 board 의 id가 다른 경우
	@GetMapping("/detail")
	public String detail(HttpSession session,Model model,
			@RequestParam("seq") int seq, @RequestParam("jCode") String jCode) {
		String uri = "board/boardDetail";
		BoardDTO dto = service.selectOne(seq);
		
		if(jCode.equals("U")) {
			uri = "board/boardUpdate";
		} else if(!session.getAttribute("loginID").equals( dto.getId() ) ){
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}
		model.addAttribute("dto", dto);
		
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
			rttr.addFlashAttribute("message", "글 등록 성공했습니다");
		} else {
			uri = "board/joinForm";
			model.addAttribute("message", "글 등록 실패했습니다, 다시입력하세요");
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
	public String delete(BoardDTO dto, Model model, RedirectAttributes rttr) {
		if(service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "게시글 삭제에 성공했습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시글 삭제에 실패했습니다.");
		}
		return "redirect:boardList";
	}
	
	// ** replyInsert
	@GetMapping("/replyInsert")
	public void missingno(Model model, BoardDTO dto) {
		// => 답글처리를위해 부모글의 root, step, indent 를 인자로 전달받으면,
	    //    이 인자에 담겨진 값은 requestScope 과 동일 
	    // => 그러므로 response 전송 전까지는 서버(Jsp)에서 사용가능
	    //    단, 객체명의 첫문자를 소문자로 해서 접근가능 ( ${boardDTO.~~} )
	}

	@PostMapping("/replyInsert")
	public String missingno(Model model, RedirectAttributes rttr, BoardDTO dto) {
		String uri = "redirect:boardList"; // 성공시
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message", "글 등록 성공했습니다");
		} else {
			uri = "board/replyInsert";
			model.addAttribute("message", "글 등록 실패 했습니다, 다시입력하세요");
		}
		
		return uri;
	}
}
