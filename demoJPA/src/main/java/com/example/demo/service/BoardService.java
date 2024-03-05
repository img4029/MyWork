package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;

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
	
	List<BoardDTO> idbList(String id);

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);
	
	int rinsert(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);

}