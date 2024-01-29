package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

public interface BoardService {

	List<BoardDTO> selectList();

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(int seq);

}