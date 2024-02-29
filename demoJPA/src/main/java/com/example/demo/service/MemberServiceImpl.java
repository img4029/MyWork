package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;

import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;
	
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	
	@Override
	public Member selectOne(String id) {
		Optional<Member> result = repository.findById(id);
		
		if( result.isPresent() ) return result.get();
		else return null;
	}
	
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}
	
	@Override
	public Member save(Member entity) {
		log.info("** register : entity => " + entity);
		repository.save( entity ); // 처리후 entity 를 return
		
		return entity;
	}
	
	@Override
	public Member pwUpdate(Member entity) {
		return null;
	}
	
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}
	
}