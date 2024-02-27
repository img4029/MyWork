package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.JoDTO;

import mappers.JoMapper;

//** Service
//=> 요청클래스 와 DAO클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 DAO클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...


public class JoServiceImpl implements JoService {

	JoMapper mapper;
	
	@Override
	public List<JoDTO> selectJoList() {
		return mapper.selectJoList();
	}
	
	@Override
	public JoDTO selectJoOne(int jno) {
		return mapper.selectJoOne(jno);
	}
	
	@Override
	public int insert(JoDTO dto) {
		return mapper.insert(dto);
	}
	
	@Override
	public int update(JoDTO dto) {
		return mapper.update(dto);
	}
	
	@Override
	public int delete(int jno) {
		return mapper.delete(jno);
	}
}
