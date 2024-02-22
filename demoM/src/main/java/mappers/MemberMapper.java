package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	
	// ** JUnit Test
	// => selectDTO, xml 대신 @ 으로 Sql 구현 가능
	@Select("select * from member where id=#{id}")
	MemberDTO selectDTO(MemberDTO dto);
	
	// => @Param 적용 Test
	//   -> 기본규칙: Mybatis 에서는 매개변수 Type은 무관하지만, 갯수는 1개만 허용
	//   -> @Param: mapper 에서 #{...} 적용, 복수갯수 사용 가능 (단, 기본자료형 사용불가_JUnit 에서는 가능 
	@Select("select * from member where id=#{id} and jno=#{jno}")
	MemberDTO selectParam(@Param("id") String id, @Param("jno") int jno);
	
	// ** Board_Check_List
	List<MemberDTO> mCheckList(SearchCriteria cri);
	int mCheckRowsCount(SearchCriteria cri);
	
	// **Member_search_paging
	List<MemberDTO> mSearchList(SearchCriteria cri);
	int mSearchRowsCount(SearchCriteria cri);
	
	List<MemberDTO> selectList();

	MemberDTO selectOne(String id);

	List<MemberDTO> selectJoList(int jno);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);
	
}