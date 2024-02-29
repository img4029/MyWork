package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Jo;
import com.example.demo.repository.JoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//** Service
//=> 요청클래스 와 DAO클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 DAO클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...

@Service
@Log4j2
@RequiredArgsConstructor
public class JoServiceImpl implements JoService {

	private final JoRepository repository;
	
	@Override
	public List<Jo> selectList() {
		return repository.findAll();
	}
	
	@Override
	public Jo selectOne(int jno) {
		Optional<Jo> result = repository.findById(jno);
		
		if( result.isPresent() ) return result.get();
		else return null;
	}
	
	@Override
	public Jo findByJno(int jno) {
		return repository.findByJno(jno);
	}

	@Override
	public Jo save(Jo entity) {
		log.info("** register : entity => " + entity);
		repository.save( entity ); // 처리후 entity 를 return
		
		return entity;
	}

	@Override
	public void deleteById(int jno) {
		repository.deleteById(jno);
	}
}
