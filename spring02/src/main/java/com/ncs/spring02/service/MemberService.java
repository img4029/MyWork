package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

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