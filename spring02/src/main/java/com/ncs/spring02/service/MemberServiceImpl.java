package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;

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

//@Component
@Service
public class MemberServiceImpl implements MemberService {
	
//	@Autowired
//	MemberDAO dao;
	//MemberDAO dao = new MemberDAO();
	
	// ** Mybatis 적용
	// => mapper의 구현객체는 스프링이 실행시 자동으로 만둘어 주입해줌.
	// => 그로므로 개발자는 interface 와 xml 만 구현하고 Service와 연결해주면 된다.
	@Autowired
	MemberMapper mapper;
	
	@Override
	public List<MemberDTO> selectList() {
//		return dao.selectList();
		return mapper.selectList();
	}
	
	@Override
	public MemberDTO selectOne(String id) {
//		return dao.selectOne(id);
		return mapper.selectOne(id);
	}
	
	@Override
	public List<MemberDTO> selectJoList(int jno) {
//		return dao.selectJoList(jno);
		return mapper.selectJoList(jno);
	}
	
	@Override
	public int insert(MemberDTO dto) {
//		return dao.insert(dto);
		return mapper.insert(dto);
	}
	
	@Override
	public int update(MemberDTO dto) {
//		return dao.update(dto);
		return mapper.update(dto);
	}
	
	@Override
	public int pwUpdate(MemberDTO dto) {
//		return dao.pwUpdate(dto);
		return mapper.pwUpdate(dto);
	}
	
	@Override
	public int delete(String id) {
//		return dao.delete(id);
		return mapper.delete(id);
	}

	
}
