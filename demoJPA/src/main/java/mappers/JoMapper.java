package mappers;

import java.util.List;

import com.example.demo.domain.JoDTO;

public interface JoMapper {
	
	List<JoDTO> selectJoList();

	JoDTO selectJoOne(int jno);

	int insert(JoDTO dto);

	int update(JoDTO dto);

	int delete(int jno);
	
}
