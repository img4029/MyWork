package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;

@Repository
public class JoDAO {
	// ** 전역변수 정의 
	private static Connection cn = DBConnection.getConnection();
	/* private static Statement st; */
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
		
	// ** selectList
	public List<JoDTO> selectJoList() {
		sql = "select A.jno, A.jname, A.captain, B.name, A.project, A.slogan\r\n"
				+ "from jo A LEFT JOIN member B \r\n"
				+ "on A.captain = B.id";
		List<JoDTO> list = new ArrayList<JoDTO>();
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery(); 
			// => 결과의 존재여부 
			if (rs.next()) {
				do {
					// => setter 사용
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));


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
	public JoDTO selectJoOne(int jno) {
		sql = "select A.jno, A.jname, A.captain, B.name, A.project, A.slogan\r\n"
				+ "from jo A LEFT JOIN member B \r\n"
				+ "on A.captain = B.id\r\n"
				+ "WHERE A.jno=?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs = pst.executeQuery();

			if (rs.next()) {
				JoDTO dto = new JoDTO();
				dto.setJno(rs.getInt(1));
				dto.setJname(rs.getString(2));
				dto.setCaptain(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setProject(rs.getString(5));
				dto.setSlogan(rs.getString(6));
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
	// => 모든 컬럼 입력
	public int insert(JoDTO dto) {
		sql = "insert into jo values(?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getCaptain());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());

			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert
	
	// ** update 
	// => jno(P.Key) 제외한 모든컬럼 수정
	public int update(JoDTO dto) {
		sql = "update jo set jname=?, captain=?, project=?, slogan=? where jno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getCaptain());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());
			
			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	// ** delete 
	// => jno 로 삭제 
	public int delete(int jno) {
		sql = "delete from jo where jno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			
			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete
}
