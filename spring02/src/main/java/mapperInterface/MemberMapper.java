package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	
	// ** Board_Check_List
	List<MemberDTO> mCheckList(SearchCriteria cri);
	int mCheckRowsCount(SearchCriteria cri);
	
	// **Member_search_paging
	List<MemberDTO> mSearchList(SearchCriteria cri);
	int mSearchRowsCount(SearchCriteria cri);
	
//	// ** Board_Paging
//	List<MemberDTO> mPageList(SearchCriteria cri);
//	int totalRowsCount(SearchCriteria cri);
	
	List<MemberDTO> selectList();

	MemberDTO selectOne(String id);

	List<MemberDTO> selectJoList(int jno);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);
	
}