package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberMapper {
	
	List<MemberDTO> selectList();

	MemberDTO selectOne(String id);

	List<MemberDTO> selectJoList(int jno);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);
	
}