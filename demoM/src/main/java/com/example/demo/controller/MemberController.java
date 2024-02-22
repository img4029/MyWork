package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

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
	
	@RequestMapping( value = {"/loginForm"}, method = RequestMethod.GET )
	public void loginForm() {}
	
	// ** login
	@RequestMapping( value = {"/login"}, method = RequestMethod.POST )
	public String login(HttpSession session,Model model,MemberDTO dto,RedirectAttributes rttr) {
		
	    String password = dto.getPassword();
	    String uri = "redirect:/home"; // 성공시
	      
	    dto = service.selectOne( dto.getId() );
	    System.out.println(password);
		System.out.println(dto.getPassword());
		System.out.println(passwordEncoder.matches(password, dto.getPassword()));
		//if( dto != null && dto.getPassword().equals(password) ) {
		if( dto != null && passwordEncoder.matches( password, dto.getPassword() ) ) {
			//성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			//실패
			rttr.addFlashAttribute("message", "ID, 비밀번호 확인불가 다시 시도하세요");
			uri = "redirect:loginForm";
		}
		return uri;
	}
	
	// ** joinForm
	@RequestMapping( value = {"/joinForm"}, method = RequestMethod.GET )
	public void joinForm() {}
	
	// ** join
	@RequestMapping( value = {"/join"}, method = RequestMethod.POST )
	public String join(HttpServletRequest request, Model model, MemberDTO dto, RedirectAttributes rttr) throws IOException {
		// 1. 요청분석
		// => 이전: 한글처리, request 값 -> dto 에 set
		// => 스프링: 한글은 필터에서 request 처리는 매개변수로 자동화
		String uri = "redirect:loginForm"; // 성공시
		
		// *** Upload File 처리 *******************************************
		
		// 1) 물리적 실제 저장위치 확인
		// 1.1) 현제 웹어플리케이션의 실질적인 실행위치 확인
		//  => 개발환경(이클립스-배포전) : ~~.eclipse.~~ 포함
		//  => 톰캣 서버 배포후 : ~~.eclipse.~~ 미포함
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => " + realPath);
		
		// 1.2) realPath 를 이용해서 물리적 저장위치 (file1) 확인
		if( !realPath.contains("apache-tomcat") ) 
			realPath= "E:\\Mtest\\git\\demoM\\src\\main\\webapp\\resources\\uploadImages\\"; // 개발중.
		else realPath= "E:\\Mtest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
		
		// 1.3 폴더 만들기 (없을수도 있음을 가정, File 클래스)
		// => File type 객체 생성 : new File("경로");
		// => file.exists()
	    //   -> 파일 또는 폴더가 존재하는지 리턴
	    //   -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함. 
		//     ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
		// => file.isDirectory() : 폴더이면 true 그러므로 false 이면 file 이 존재 한다는 의미가 됨. 
		// => file.isFile()
		//   -> 파일이 존재하는 경우 true 리턴,
		//      file의 Path 가 폴더인 경우는 false 리턴
		File file = new File(realPath);
		if( !file.exists() ) {
			// 저장 폴더가 존재하지 않는경우 만들어줌
			file.mkdir();
		}
		
		// ----------------------------------------------------------------------
		// ** File Copy 하기 (IO Stream)
	    // => 기본이미지(basicman4.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
	    // => IO 발생: Checked Exception 처리
		file = new File(realPath+"basicman4.png"); // uploadImages 폴더에 화일존재 확인을 위함
	    if ( !file.isFile() ) { // 존재하지않는 경우
//	    	String basicImagePath= "E:\\Mtest\\git\\spring02\\src\\main\\webapp\\resources\\images\\basicman4.png";
	    	String basicImagePath;
	    	if( !realPath.contains("apache-tomcat") ) 
	    		basicImagePath= "E:\\Mtest\\git\\demoM\\src\\main\\webapp\\resources\\images\\basicman4.png"; // 개발중.
			else basicImagePath= "E:\\Mtest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\images\\basicman4.png";
	    	FileInputStream fi = new FileInputStream(new File(basicImagePath));
	    	// => uploadImages 읽어 파일 입력바이트스트림 생성
	    	FileOutputStream fo = new FileOutputStream(file); 
	    	// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성  
	    	FileCopyUtils.copy(fi, fo);
	    }
		// ----------------------------------------------------------------------
	    // ** MultipartFile
	    // => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
	    //    -> String getOriginalFilename(), 
	    //    -> void transferTo(File destFile),
	    //    -> boolean isEmpty()
		
		// 1.4) 저장경로 완성
		String file1="", file2="basicman4.png";
		MultipartFile uploadfilef = dto.getUploadfilef();
		if(uploadfilef != null && !uploadfilef.isEmpty()) {
			file1=realPath+uploadfilef.getOriginalFilename(); //저장경로 완성
			uploadfilef.transferTo(new File(file1));
			
			file2=uploadfilef.getOriginalFilename();
			System.out.println(file2);
		}
		
		dto.setUploadfile(file2);
		// 2. Service & 결과
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		// ** *****************************************
		// ** Transaction_AOP 적용 ********************* 
		// 1. 준비: pom.xml (dependency) 확인
		// =>  AspectJ(기본제공), AspectJ Weaver(추가)
  
		// 2. servlet-context.xml AOP 설정
  
		// 3. Rollback Test
		// 3.1) Aop xml 적용전 => insert1 은 입력되고, insert2 에서  500_Dupl..Key  오류 발생
		// 3.2) Aop xml 적용후 => insert2 에서 오류발생시 모두 Rollback 되어 insert1, insert2 모두 입력 안됨 
  
		// 3.1) Transaction 적용전 : 동일자료 2번 insert
		// => 첫번째는 입력완료(commit) 되고, 두번째자료 입력시 Key중복 오류발생 (500 발생)
		// 3.2) Transaction 적용후 : 동일자료 2번 insert
		// => 첫번째는 입력완료 되고, 두번째 자료입력시 Key중복 오류발생 하지만,
		//    rollback 되어 둘다 입력 안됨
		//service.insert(dto); // Transaction_Test, insert1 
		
		if(service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "가입에 성공했습니다. 로그인 후 이용하세요.");
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
	
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		//view_name 생략
	}
	
	// => Service, DAO 에 pwUpdate(dto) 메서드 추가
	// => 성공: 로그인창으로
	//    실패: pwUpdate, 재로그인 유도
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Model model, MemberDTO dto) {
		dto.setId((String)session.getAttribute("loginID"));
		dto.setPassword( passwordEncoder.encode( dto.getPassword() ) );
		if(service.pwUpdate(dto) > 0) {
			// => 성공
			session.invalidate();
			model.addAttribute("message", "T");
		}else {
			// => 실패
			model.addAttribute("message", "비밀번호 수정 실패, 다시입력하세요");
		}
		return "member/pwUpdate";
	}
	// ** update
	@RequestMapping( value = {"/update"}, method = RequestMethod.POST )
	public String update(HttpServletRequest request,HttpSession session, Model model, MemberDTO dto) throws IOException {
		String uri = "member/memberDetail"; // 성공시
		
		// ** uploadFile 처리
		// => newImage 선택여부
		// => 선택 -> oldImage 삭제, newImage 저장 : uploadFilef 사용
		// => 선택하지않음 -> oldImage 가 uploadFilef 전달되었으므로 그냥 사용하면 됨
		
		MultipartFile uploadfilef = dto.getUploadfilef();
		if(uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 를 선택함
			// 1) 물리적위치 저장(file1)
			String realPath = request.getRealPath("/");
			String file1;
			
			// 2) realPath 를 이용해 물리적 저장위치(file1) 확인
			if( !realPath.contains("apache-tomcat") ) 
				realPath= "E:\\Mtest\\git\\demoM\\src\\main\\webapp\\resources\\uploadImages\\"; // 개발중.
			else realPath= "E:\\Mtest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
			
			// 3) old 파일 삭제
			File delFile = new File(realPath+dto.getUploadfile());
			if(delFile.isFile()) delFile.delete(); // file 존재시 삭제
			
			// 4) newFile 저장
			file1=realPath+uploadfilef.getOriginalFilename(); //저장경로 완성
			uploadfilef.transferTo(new File(file1));
			
			// 5) Table 저장경로 완성(file2)
			dto.setUploadfile(uploadfilef.getOriginalFilename());
		}
		
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
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		if(service.delete((String)session.getAttribute("loginID")) > 0) {
			rttr.addFlashAttribute("message", "회원 탈퇴에 성공했습니다. <br>1개월 후에 재가입 가능합니다.");
			// => requestScope 의 message 를 redirect 시에도 유지하려면 
			//	  session 에 보관했다가 사용후에는 삭제해야함
			//    session 에 보관후 redirect 되어진 요청 처리시에 requestScope에 옴기고,
			//    session 의 message 삭제 
			// => 이것을 처리해주는 API가 RedirectAttributes
			session.invalidate();
		} else {
			rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. <br>관리자에게 연락하세요.");
		}
		return "redirect:/home";
	}
		
	// ** mPageList
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri = "member/mPageList";
		String mappingName = request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/")+1 );
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		if(cri.getCheck() != null && cri.getCheck().length<1) cri.setCheck(null);
		model.addAttribute("mList", service.mCheckList(cri));
		model.addAttribute("jList", jservice.selectJoList());
		
		// 3) View처리 : pageMaker 이용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	}//mCheckList
	
	// ** mPageList
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String mappingName = request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/")+1 );
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		model.addAttribute("mList", service.mPageList(cri));
		model.addAttribute("jList", jservice.selectJoList());
		
		// 3) View처리 : pageMaker 이용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
	}//mPageList
	
	// ** memberList
	@RequestMapping(value = { "/memberList" }, method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("mList", service.selectList());
	}
}
