package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	// 필드
	@Autowired
	private DataSource dataSource;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private int count = 0;

	// 생성자
	// 메소드 겟셋
	// 메소드 일반

	// DB접속 메소드
	public void getConnection() {
		try {
			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리 메소드
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	///////////////////////////////////////////////////////

	// 내용 출력(리스트)
	public List<GuestVo> getList() {
		List<GuestVo> gList = new ArrayList<GuestVo>();

		getConnection();

		try {
			// SQL문 준비 / 바인딩 / 실행 
			String query = "";
			query += " SELECT  no, ";
			query += "         name, ";
			query += "         password, ";
			query += "         content, ";
			query += "         to_char(reg_date, 'YYYY-MM-DD HH24:MI:SS') reg_date ";
			query += " FROM guestbook ";
			query += " order by no desc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 결과 처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestVo gVo = new GuestVo(no, name, password, content, regDate);
				gList.add(gVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return gList;
	}

	// 내용 삭제 (조건 2개 달아서 쿼리문 1개로 처리)
	public int contentDelete(int no, String password) {

		getConnection();

		try {
			// SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? and password = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			count = pstmt.executeUpdate();

			// 결과 처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return count;
	}

	// 내용 등록
	public int contentAdd(GuestVo gVo) {

		getConnection();

		try {
			// SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into guestbook ";
			query += " VALUES(seq_no.nextval, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, gVo.getName());
			pstmt.setString(2, gVo.getPassword());
			pstmt.setString(3, gVo.getContent()); 

			count = pstmt.executeUpdate();

			// 결과 처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return count;
	}

}
