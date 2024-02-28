package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Member;

import pageTest.SearchCriteria;

public interface MemberService {

	// **Member_Check_List
	List<Member> mCheckList(SearchCriteria cri);
	int mCheckRowsCount(SearchCriteria cri);
	
	// **Member_Paging
	List<Member> mPageList(SearchCriteria cri);
	int totalRowsCount(SearchCriteria cri);
	
	List<Member> selectList();

	Member selectOne(String id);

	List<Member> selectJoList(int jno);

	int insert(Member dto);

	int update(Member dto);
	
	int pwUpdate(Member dto);

	int delete(String id);

}