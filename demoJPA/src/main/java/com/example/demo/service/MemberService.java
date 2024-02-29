package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	
	List<Member> selectList();

	Member selectOne(String id);

	List<Member> findByJno(int jno);

	// insert, update
	Member save(Member entity);
	
	//password Update
	// =>@Query 적용
	void updataPassword1(String id,String password);
	void updataPassword2(String id,String password);

	// ** Join
	List<MemberDTO> findMemberJoin();
	
	void deleteById(String id);

}