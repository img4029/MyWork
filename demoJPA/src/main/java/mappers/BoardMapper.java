package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardMapper {
	
	// ** Board_Check_List
	List<BoardDTO> bCheckList(SearchCriteria cri);
	int bCheckRowsCount(SearchCriteria cri);
	
	// **board_search_paging
	List<BoardDTO> bSearchList(SearchCriteria cri);
	int bSearchRowsCount(SearchCriteria cri);
	
	// **board_paging
	List<BoardDTO> bPageList(Criteria cri);
	int totalRowsCount(Criteria cri);
	
	
	List<BoardDTO> selectList();
	
	@Select("select * from board where id=#{id} order by root desc, step asc")
	List<BoardDTO> idbList(String id);

	BoardDTO selectOne(int seq);

	int insert(BoardDTO dto);
	
	int rinsert(BoardDTO dto);
	
	int stepUpdate(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);
}
