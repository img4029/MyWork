package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Program;
import com.example.demo.service.ProgramService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping( value = "/api/prg")
@AllArgsConstructor // 개별적으로 @Autowired 하지않아도 된다. 생략가능
public class ProgramController {

	ProgramService prgService;
	
	@GetMapping("/prgList")
	public List<Program> prgList(Model model) {
		List<Program> list = prgService.selectList();
		
		return list;
	} //prgList
	
	@GetMapping("/hi")
	public String hi() {
		
		return "hi";
	} //prgList
}

