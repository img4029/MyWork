package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

@Repository
public class BoardDAO {
	// ** 전역변수 정의 
		private static Connection cn = DBConnection.getConnection();
		private static PreparedStatement pst;
		private static ResultSet rs;
		private static String sql;
			
		// ** selectList
		public List<BoardDTO> selectList() {
			sql = "select * from board order by root desc, step asc";
			// => 답글 달기 추가후 출력순서 수정
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			try {
				pst = cn.prepareStatement(sql);
				rs = pst.executeQuery(); 
				// => 결과의 존재여부 
				if (rs.next()) {
					do {
						// => setter 사용
						BoardDTO dto = new BoardDTO();
						dto.setSeq(rs.getInt(1));
						dto.setId(rs.getString(2));
						dto.setTitle(rs.getString(3));
						dto.setContent(rs.getString(4));
						dto.setRegdate(rs.getString(5));
						dto.setCnt(rs.getInt(6));
						dto.setRoot(rs.getInt(7));
						dto.setStep(rs.getInt(8));
						dto.setIndent(rs.getInt(9));

						list.add(dto);
					} while (rs.next());
					return list;
				} else {
					return null;
				}
			} catch (Exception e) {
				System.out.println("** selectJoList Exception => " + e.toString());
				return null;
			}
		} // selectList
		
		// ** selectOne 
		// => 기본자료형 매개변수 _ Call By Value
		public BoardDTO selectOne(int seq) {
			sql = "select * from board where seq=?";

			try {
				pst = cn.prepareStatement(sql);
				pst.setInt(1, seq);
				rs = pst.executeQuery();

				if (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					return dto;
				} else {
					return null;
				}
			} catch (Exception e) {
				System.out.println("** selectJoOne Exception => " + e.toString());
				return null;
			}
		} // selectOne
		

		// ** insert : 원글입력
		// => 입력 컬럼: id, title, content 
		//    default값: regdate, cnt, step, indent
		// => root : seq 와 동일한 값   
		// => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
		public int insert(BoardDTO dto) {
			sql = "insert into board values(\r\n"
					+ "	(  select * from ( select ifNull(max(seq),0)+1 from board  ) as temp ), \r\n"
					+ "	?, ?, ?, current_timestamp, 0,\r\n"
					+ "	(  select * from ( select ifNull(max(seq),0)+1 from board  ) as temp ), 0, 0\r\n"
					+ ")";
			try {
				pst = cn.prepareStatement(sql);
				pst.setString(1, dto.getId());
				pst.setString(2, dto.getTitle());
				pst.setString(3, dto.getContent());


				return pst.executeUpdate(); // 처리갯수

			} catch (Exception e) {
				System.out.println("** insert Exception => " + e.toString());
				return 0;
			}
		} // insert
		
		// ** replyInsert : 답글입력
		// => seq: IFNULL 이용
		// => 입력 컬럼: id, title, content, root, step, indent
		// => JDBC subQuery 구문 적용시 주의사항
		//     -> MySql: select 구문으로 한번더 씌워 주어야함 (insert 의 경우에도 동일)   
		// => stepUpdate 가 필요함
		//    댓글 입력 성공후 실행
		//     -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의  
		// => boardList 의 출력순서 확인
		//		order by root desc, step asc
		public int rinsert(BoardDTO dto) {
			sql = "insert into board values(\r\n"
					+ "	(  select * from ( select ifNull(max(seq),0)+1 from board  ) as temp ), \r\n"
					+ "	?, ?, ?, current_timestamp, 0,\r\n"
					+ "	?, ?, ?\r\n"
					+ ")";
			try {
				pst = cn.prepareStatement(sql);
				pst.setString(1, dto.getId());
				pst.setString(2, dto.getTitle());
				pst.setString(3, dto.getContent());
				pst.setInt(4, dto.getRoot());
				pst.setInt(5, dto.getStep());
				pst.setInt(6, dto.getIndent());
				pst.executeUpdate(); // 답글등록 성공 -> stepUpdate
				System.out.println("** stepUpdate Count=> "+stepUpdate(dto));
				
				return 1; // 처리갯수

			} catch (Exception e) {
				System.out.println("** insert Exception => " + e.toString());
				return 0;
			}
		} // rinsert
		
		// ** stepUpdate : step 값 증가
		// => 조건 root 동일 and step >= and 새글은 제외
		// => 
		public int stepUpdate(BoardDTO dto) {
			sql = "update board set step=step+1 where root=? and step >=? and "
					+ "seq <> ( select * from ( select ifNull( max(seq), 0 ) from board ) as temp )";
			try {
				pst = cn.prepareStatement(sql);
				pst.setInt(1, dto.getRoot());
				pst.setInt(2, dto.getStep());
				
				return pst.executeUpdate();
			} catch (Exception e) {
				System.out.println("** update Exception => " + e.toString());
				return 0;
			}
		} // stepUpdate
		
		// ** update 
		// => 컬럼 수정
		public int update(BoardDTO dto) {
			sql = "update board set title=?, content=? where seq=?";
			try {
				pst = cn.prepareStatement(sql);
				pst.setString(1, dto.getTitle());
				pst.setString(2, dto.getContent());
				pst.setInt(3, dto.getSeq());
				
				return pst.executeUpdate(); // 처리갯수
			} catch (Exception e) {
				System.out.println("** update Exception => " + e.toString());
				return 0;
			}
		} // update
		
		// ** delete 
		// => seq 로 삭제 
		public int delete(BoardDTO dto) {
			//원글 삭제
			if(dto.getSeq() == dto.getRoot()) sql = "delete from board where root=?";
			//답글 삭제
			else sql = "delete from board where seq=?";
			
			try {
				pst = cn.prepareStatement(sql);
				pst.setInt(1, dto.getSeq());
				
				return pst.executeUpdate(); // 처리갯수
			} catch (Exception e) {
				System.out.println("** delete Exception => " + e.toString());
				return 0;
			}
		} // delete
}
