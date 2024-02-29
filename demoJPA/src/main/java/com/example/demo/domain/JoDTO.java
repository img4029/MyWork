package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoDTO {
	// ** private 으로 맴버변수 정의
	private int jno;
	protected String jname;
	private String captain;
	private String name;
	protected String project;
	private String slogan;
	
	// ** 생성자
	// ** setter/getter
	// ** toString

} //class
