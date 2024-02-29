package com.example.demo.controller;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

//@Log4j -> Boot 에서는 2015년 이후 지원중단
@Log4j2
@Controller
@RequestMapping( value = "/member")
@AllArgsConstructor // 개별적으로 @Autowired 하지않아도 된다. 생략가능
public class MemberController {
	
	MemberService service;
	JoService jservice;
	PasswordEncoder passwordEncoder; //DemoCofig에 설정
	
	@GetMapping("/idDoubleCheck")
	public void idDoubleCheck(@RequestParam("id") String id, Model model) {
		// 1) newID 존재 여부 확인 & 결과처리
		if(service.selectOne(id) != null) {
			// => 사용 불가능
			model.addAttribute("idUse", "F");
		}else {
			// => 사용 가능
			model.addAttribute("idUse", "T");
		}
	} //idDoubleCheck
	
	@GetMapping("/loginForm")
	public void loginForm() {}
	
	// ** login
	@PostMapping("/login")
	public String login(HttpSession session,Model model,Member entity,RedirectAttributes rttr) {
		
	    String password = entity.getPassword();
	    String uri = "redirect:/home"; // 성공시
	      
	    entity = service.selectOne( entity.getId() );

		if( entity != null && passwordEncoder.matches( password, entity.getPassword() ) ) {
			//성공
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
		} else {
			//실패
			rttr.addFlashAttribute("message", "ID, 비밀번호 확인불가 다시 시도하세요");
			uri = "redirect:loginForm";
		}
		return uri;
	}
	
	// ** logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
	// ** joinForm
	@GetMapping("/joinForm")
	public void joinForm() {}
	
	// ** join
	@PostMapping("/join")
	public String join(HttpServletRequest request, Model model, Member entity, RedirectAttributes rttr) throws IOException {
		// 1. 요청분석
		String uri = "redirect:loginForm"; // 성공시
		
		// *** Upload File 처리 *******************************************
		
		// 1) 물리적 실제 저장위치 확인
		// 1.1) 현제 웹어플리케이션의 실질적인 실행위치 확인
		String realPath = request.getRealPath("/") + "resources\\uploadImages\\";
		log.info("** realPath => " + realPath);
		// => spring_Boot_realPath => E:\\Mtest\\git\\demoJPA\\src\\main\\webapp\\
		
		File file = new File(realPath);
		if( !file.exists() ) {
			// 저장 폴더가 존재하지 않는경우 만들어줌
			file.mkdir();
		}
		
		file = new File(realPath+"basicman4.png"); // uploadImages 폴더에 화일존재 확인을 위함
	    if ( !file.isFile() ) { // 존재하지않는 경우
	    	String basicImagePath = request.getRealPath("/") + "resources\\images\\basicman4.png";
	    	
	    	FileInputStream fi = new FileInputStream(new File(basicImagePath));
	    	// => uploadImages 읽어 파일 입력바이트스트림 생성
	    	FileOutputStream fo = new FileOutputStream(file); 
	    	// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성  
	    	FileCopyUtils.copy(fi, fo);
	    }
		
		// 1.4) 저장경로 완성
		String file1="", file2="basicman4.png";
		MultipartFile uploadfilef = entity.getUploadfilef();
		if(uploadfilef != null && !uploadfilef.isEmpty()) {
			file1=realPath+uploadfilef.getOriginalFilename(); //저장경로 완성
			uploadfilef.transferTo(new File(file1));
			
			file2=uploadfilef.getOriginalFilename();
			System.out.println(file2);
		}
		
		entity.setUploadfile(file2);
		// 2. Service & 결과
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		
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
	
	// ** memberDetail or updateForm
	@GetMapping("/detail")
	public String detail(HttpSession session,Model model, @RequestParam("jCode") String jCode) {
		String uri = "member/memberDetail";
		String id = (String)session.getAttribute("loginID");
		model.addAttribute("dto", service.selectOne(id));
		if("U".equals(jCode)) {
			uri = "member/updateForm";
		}

		return uri;
	}
	
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		//view_name 생략
	}
	
	// => Service, DAO 에 pwUpdate(dto) 메서드 추가
	// => 성공: 로그인창으로
	//    실패: pwUpdate, 재로그인 유도
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Model model, Member entity) {
		entity.setId((String)session.getAttribute("loginID"));
		entity.setPassword( passwordEncoder.encode( entity.getPassword() ) );
		
		try {
			service.updataPassword1(entity.getId(), entity.getPassword());
			log.info(" member updataPassword 성공 " );
			session.invalidate();
			model.addAttribute("message", "T");
		} catch (Exception e) {
			log.info(" member updataPassword Exception => " + e.toString());
			model.addAttribute("message", "비밀번호 수정 실패, 다시입력하세요");
		}
		
		return "member/pwUpdate";
	}
	
	// ** update
	@PostMapping("/update")
	public String update(HttpServletRequest request,HttpSession session, Model model, Member entity) throws IOException {
		String uri = "member/memberDetail"; // 성공시
		
		// ** uploadFile 처리
		// => newImage 선택여부
		// => 선택 -> oldImage 삭제, newImage 저장 : uploadFilef 사용
		// => 선택하지않음 -> oldImage 가 uploadFilef 전달되었으므로 그냥 사용하면 됨
		
		MultipartFile uploadfilef = entity.getUploadfilef();
		if(uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 를 선택함
			// 1) 물리적위치 저장(file1)
			String realPath = request.getRealPath("/") + "resources\\uploadImages\\";
			String file1;
			
			// 2) realPath 를 이용해 물리적 저장위치(file1) 확인
			
			// 3) old 파일 삭제
			File delFile = new File(realPath+entity.getUploadfile());
			if(delFile.isFile()) delFile.delete(); // file 존재시 삭제
			
			// 4) newFile 저장
			file1=realPath+uploadfilef.getOriginalFilename(); //저장경로 완성
			uploadfilef.transferTo(new File(file1));
			
			// 5) Table 저장경로 완성(file2)
			entity.setUploadfile(uploadfilef.getOriginalFilename());
		}
		
		model.addAttribute("dto", entity);
		
		try {
			log.info(" member update 성공 => " + service.save(entity));
			model.addAttribute("message", "회원 정보 수정 성공!");
		} catch (Exception e) {
			uri = "member/updateForm";
			log.info(" member update Exception => " + e.toString());
			model.addAttribute("message", "회원 정보 수정 실패, 다시입력하세요");
		}
		
		return uri;
	}
	
	// ** deleteForm
	@GetMapping("/deleteForm")
	public void deleteForm() {}
	
	// ** delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		
		try {
			service.deleteById((String)session.getAttribute("loginID"));
			log.info(" member delete 성공 " );
			session.invalidate();
		} catch (Exception e) {
			log.info(" member delete Exception => " + e.toString());
			rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. <br>관리자에게 연락하세요.");
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("/mjoinList")
	public void mjoinList(Model model) {
		model.addAttribute("banana", service.findMemberJoin());
	}
	
	// ** mCheckList
//	@GetMapping("/mCheckList")
//	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
//		String uri = "member/mPageList";
//		String mappingName = request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/")+1 );
//		// 1) Criteria 처리
//		cri.setSnoEno();
//		
//		// 2) Service
//		if(cri.getCheck() != null && cri.getCheck().length<1) cri.setCheck(null);
//		model.addAttribute("mList", service.mCheckList(cri));
//		model.addAttribute("jList", jservice.selectJoList());
//		
//		// 3) View처리 : pageMaker 이용
//		pageMaker.setCri(cri);
//		pageMaker.setMappingName(mappingName);
//		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
//		model.addAttribute("pageMaker", pageMaker);
//		
//		return uri;
//	}//mCheckList
	
	// ** mPageList
//	@GetMapping("/mPageList")
//	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
//		String mappingName = request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/")+1 );
//		// 1) Criteria 처리
//		cri.setSnoEno();
//		
//		// 2) Service
//		model.addAttribute("mList", service.mPageList(cri));
//		model.addAttribute("jList", jservice.selectJoList());
//		
//		// 3) View처리 : pageMaker 이용
//		pageMaker.setCri(cri);
//		pageMaker.setMappingName(mappingName);
//		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
//		model.addAttribute("pageMaker", pageMaker);
//		
//	}//mPageList
	
	// ** memberList
	@GetMapping("/memberList")
	public void mList(Model model) {
		model.addAttribute("mList", service.selectList());
	}
	
	// axiMemberList
	@GetMapping(value="/aximlist")
	public String axiMemberList(Model model) {
		model.addAttribute("mList", service.selectList());
		log.info("rsjoin HttpStatus.OK" + HttpStatus.OK);
		
		return "axTest/axMemberList";
	}
	
	// axiMemberList
	// => ver01 : "/axmcri" 만 구현 (search 기능만 구현)
	// => ver02 : "/axmcheck" 요청도 처리할 수 있도록 구현
	//			-> mappingName 에 check가 포함되어있으면 service 를 아래 메서드로 처리하도록
//	@GetMapping({"/axmcri","axmcheck"})
//	public String axmcri(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
//		
//		// 1) Criteria 처리
//		cri.setSnoEno();
//		
//		// 2) 요청확인 & Service
//		String mappingName = request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/")+1 );
//		pageMaker.setMappingName(mappingName);
//		pageMaker.setCri(cri);
//		System.out.println("??" + mappingName);
//		
//		if( mappingName.contains("check") ) {
//			// => Check 조건처리
//			model.addAttribute("mList", service.mCheckList(cri));
//			pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
//		} else {
//			// => Search 조건 처리
//			model.addAttribute("mList", service.mPageList(cri));
//			pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
//		}
//		model.addAttribute("jList", jservice.selectJoList());
//		
//		// 3) View처리 : pageMaker 이용
//		// => 요청명을 url에 포함하기 위함
//		model.addAttribute("pageMaker", pageMaker);
//		
//		return "axTest/axmPageList";
//	}
	
}
