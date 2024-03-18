package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Program;

public interface ProgramService {
	
	List<Program> selectList();
	
	Program selectOne(String prg_id);
	
	Program save(Program entity);
	
	void deleteById(String prg_id);
}
