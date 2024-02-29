package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Jo;

public interface JoService {

	List<Jo> selectList();

	Jo selectOne(int jno);

	Jo findByJno(int jno);
	
	Jo save(Jo entity);

	void deleteById(int jno);

}