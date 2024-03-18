package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.service.ProgramService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	ProgramService prgService;
	
}
