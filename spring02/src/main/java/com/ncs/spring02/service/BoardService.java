package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardService {
	// ** Board_Check_List
	List<BoardDTO> bCheckList(SearchCriteria cri);
	int bCheckRowsCount(SearchCriteria cri);
	
	// ** Board_Paging
	// => ver01 : Criteria
	// => ver02 : SearchCriteria
	List<BoardDTO> bPageList(SearchCriteria cri);
	int totalRowsCount(SearchCriteria cri);

	List<BoardDTO> selectList();

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);
	
	int rinsert(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);

}