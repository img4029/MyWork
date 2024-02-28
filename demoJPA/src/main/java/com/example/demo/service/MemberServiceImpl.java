package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import pageTest.SearchCriteria;

// ** Mybatis 적용
// => CRUD 처리를 Mapper 를 이용
// => DAO 대신 Mapper interface ->  ~Mapper.xml

// ** Mybatis interface 방식으로 적용
// => MemberDAO 대신 MemberMapper 사용
// => MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
//    (스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
// => 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//    MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper 가 역할을 대신함

// => SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라 함 
// => Mapper 작성규칙
//    -> mapperInterface 와 패키지명, 화일명이 동일해야함
//    -> 즉, 
//    -> 그리고 해당메서드는 Mapper 의 xml 구문의 id속성값으로 찾음
/*
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private final MemberRepository repository;
	
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	
	@Override
	public Member selectOne(String id) {
		return repository.selectOne(id);
	}
	
	@Override
	public List<Member> selectJoList(int jno) {
		return repository.selectJoList(jno);
	}
	
	@Override
	public int insert(Member dto) {
		return repository.insert(dto);
	}
	
	@Override
	public int update(Member dto) {
		return repository.update(dto);
	}
	
	@Override
	public int pwUpdate(Member dto) {
		return repository.pwUpdate(dto);
	}
	
	@Override
	public int delete(String id) {
		return repository.delete(id);
	}

	// ** Board_Check_List
	@Override
	public List<Member> mCheckList(SearchCriteria cri){
		return repository.mCheckList(cri);
	}
	
	@Override
	public int mCheckRowsCount(SearchCriteria cri) {
		return repository.mCheckRowsCount(cri);
	}
	
	@Override
	public List<Member> mPageList(SearchCriteria cri) {
		//return mapper.bPageList(cri);
		return repository.mSearchList(cri);
	}
	
	@Override
	public int totalRowsCount(SearchCriteria cri) {
		return repository.mSearchRowsCount(cri);
	}
	
}
*/

@Service
public class MemberServiceImpl {
	
	
}