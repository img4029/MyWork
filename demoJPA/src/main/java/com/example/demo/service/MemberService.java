package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

	// **Member_Check_List
	List<MemberDTO> mCheckList(SearchCriteria cri);
	int mCheckRowsCount(SearchCriteria cri);
	
	// **Member_Paging
	List<MemberDTO> mPageList(SearchCriteria cri);
	int totalRowsCount(SearchCriteria cri);
	
	List<MemberDTO> selectList();

	MemberDTO selectOne(String id);

	List<MemberDTO> selectJoList(int jno);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);

}