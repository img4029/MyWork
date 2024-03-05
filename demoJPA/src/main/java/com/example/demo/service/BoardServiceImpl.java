package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardDTO;

import mappers.BoardMapper;
import pageTest.Criteria;
import pageTest.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	BoardMapper mapper;
	
	// ** Board_Check_List
	@Override
	public List<BoardDTO> bCheckList(SearchCriteria cri){
		return mapper.bCheckList(cri);
	}
	@Override
	public int bCheckRowsCount(SearchCriteria cri) {
		return mapper.bCheckRowsCount(cri);
	}
	
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
	public List<BoardDTO> idbList(String id) {
		return mapper.idbList(id);
	};
	
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