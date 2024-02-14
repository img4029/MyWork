package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardService {
	
	// **board_paging
	List<BoardDTO> bPageList(Criteria cri);
	int totalRowsCount(Criteria cri);

	List<BoardDTO> selectList();

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);
	
	int rinsert(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);

}