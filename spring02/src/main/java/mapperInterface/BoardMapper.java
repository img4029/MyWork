package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

public interface BoardMapper {

	List<BoardDTO> selectList();

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);
	
	int rinsert(BoardDTO dto);
	
	int stepUpdate(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);
}
