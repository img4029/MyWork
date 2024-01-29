package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	List<JoDTO> selectJoList();

	JoDTO selectJoOne(int jno);

	int insert(JoDTO dto);

	int update(JoDTO dto);

	int delete(int jno);

}