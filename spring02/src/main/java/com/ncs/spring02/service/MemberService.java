package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberService {

	List<MemberDTO> selectList();

	MemberDTO selectOne(String id);

	List<MemberDTO> selectJoList(int jno);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);

}