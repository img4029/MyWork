package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;
import pageTest.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper mapper;
	
	// => ver01 : Criteria
	// => ver02 : SearchCriteria
	@Override
	public List<BoardDTO> bPageList(SearchCriteria cri) {
		//return mapper.bPageList(cri);
		return mapper.bSearchList(cri);
	}
	@Override
	public int totalRowsCount(SearchCriteria cri) {
		//return mapper.totalRowsCount(cri);
		return mapper.bSearchRowsCount(cri);
	}
	
	@Override
	public List<BoardDTO> selectList() {
		return mapper.selectList();
	}
	
	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}
	
	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}
	
	@Override
	public int rinsert(BoardDTO dto) {
		 if(mapper.rinsert(dto)>0) {
			 System.out.println("** stepUpdate Count=> "+mapper.stepUpdate(dto));
			 return 1;
		 } else {
			 return 0;
		 }
	}
	
	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}
	
	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}

}
