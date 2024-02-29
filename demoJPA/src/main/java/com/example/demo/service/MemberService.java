package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Member;

public interface MemberService {
	
	List<Member> selectList();

	Member selectOne(String id);

	List<Member> findByJno(int jno);

	// insert, update
	Member save(Member entity);
	
	Member pwUpdate(Member entity);

	void deleteById(String id);

}