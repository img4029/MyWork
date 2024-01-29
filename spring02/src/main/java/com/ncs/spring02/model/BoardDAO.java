package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

@Repository
public class BoardDAO {
	// ** 전역변수 정의 
		private static Connection cn = DBConnection.getConnection();
		private static PreparedStatement pst;
		private static ResultSet rs;
		private static String sql;
			
		// ** selectList
		public List<BoardDTO> selectList() {
			sql = "select * from board order by seq desc";
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
		
		// ** insert
		// => 컬럼 입력
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
		
		// ** update 
		// => 컬럼 수정
		public int update(BoardDTO dto) {
			sql = "update board set title=?, content=?, cnt=? where seq=?";
			try {
				pst = cn.prepareStatement(sql);
				pst.setString(1, dto.getTitle());
				pst.setString(2, dto.getContent());
				pst.setInt(3, dto.getCnt());
				pst.setInt(4, dto.getSeq());
				
				return pst.executeUpdate(); // 처리갯수
			} catch (Exception e) {
				System.out.println("** update Exception => " + e.toString());
				return 0;
			}
		} // update

		
		
		// ** delete 
		// => seq 로 삭제 
		public int delete(int seq) {
			sql = "delete from board where seq=?";
			try {
				pst = cn.prepareStatement(sql);
				pst.setInt(1, seq);
				
				return pst.executeUpdate(); // 처리갯수
			} catch (Exception e) {
				System.out.println("** delete Exception => " + e.toString());
				return 0;
			}
		} // delete
}
