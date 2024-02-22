package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/rest")
@Log4j2
@AllArgsConstructor
public class RESTController {
	
	MemberService service;
	JoService jservice;

	// hello 리턴을 위한 메서드 작성
	@GetMapping("/hello")
	// 메뉴없이 직접 요청 : http://localhost:8080/spring02/rest/hello
	// return 한 string 값이 response에 담겨져 전송되었으며, 출력되는 것
	public String hello() {
		log.info("** Rest API Test **");
		return "Hello Spring MVC Rest API";
	}
	
	@GetMapping("/order/{test1}/{test2}")
	public String[] order(	@PathVariable("test1") String category,
							@PathVariable("test2") String color	) {	// 매개변수를 연결하여 값을 각각의 매핑주소에 넣어주기
		return new String[] {"category : "+category,"color : "+color};
	}
	// 6) @RequestBody
	// => JSON 형식으로 전달된 Data를 컨트롤러에서 사용자정의 객체(DTO) _Java객체 로 변환할때 사용
	// => 요청 url : http://localhost:8080/spring02/rest/convert
	// => Payload : {"jno":33, "jname":"삼삼오오", "captain":"victory", "project":"RequestBody Test 중"}
	
	@PostMapping("/convert")
	public ResponseEntity<?> convert(@RequestBody JoDTO dto) {
		ResponseEntity<JoDTO> result = null;
		if (dto != null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** convert Test HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("** convert Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	}
	
	@GetMapping(value="/gettext", produces="text/plain; charset=UTF-8")
	public String getText() {
		log.info("** MIME Type MediaType 클래스 적용 => "+MediaType.TEXT_PLAIN_VALUE);
		return "<h2>안녕하세요? Spring MVC Rest API";
	}
	
	// 2가지의 경우를 모두 사용하는 경우 : xml 호출(default)
	@GetMapping(value="/getDTO1", 
					produces= {	MediaType.APPLICATION_JSON_VALUE,
								MediaType.APPLICATION_XML_VALUE})
	public JoDTO getDTO1() {
		return new JoDTO(9,"Rest조","restTest","Rest API 테스트","레스트 API 화이팅","레스트");
	}
	
	
}
