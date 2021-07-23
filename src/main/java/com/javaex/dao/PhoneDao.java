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

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
//	private String driver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String id = "phonedb";
//	private String pw = "phonedb";
	
	private void getConnection() {
		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, id, pw);
			conn = dataSource.getConnection();
//		} catch (ClassNotFoundException e) {
//			System.out.println("ERROR - 드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	private void getClose() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	public int insert(PersonVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" INSERT INTO "
					+ " 		person "
					+ " VALUES "
					+ " 	( seq_person_id.NEXTVAL, ?, ?, ? ) "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		getClose();
		
		return count;
	}
	
	public List<PersonVo> getList() {
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		getConnection();

		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id 번호, "
					+ " 	name 이름, "
					+ " 	hp 휴대전화, "
					+ " 	company 회사전화 "
					+ " FROM "
					+ " 	person "
					+ " ORDER BY "
					+ " 	person_id ASC "
					);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				phoneList.add(new PersonVo(rs.getInt("번호"), rs.getString("이름"), rs.getString("휴대전화"), rs.getString("회사전화")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return phoneList;
	}
	
	public List<PersonVo> getList(String k) {
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id I, "
					+ " 	name N, "
					+ " 	hp H, "
					+ " 	company C "
					+ " FROM "
					+ " 	person "
					+ " WHERE "
					+ " 	name LIKE '%" + k + "%' "
					+ " 	OR hp LIKE '%" + k + "%' "
					+ " 	OR company LIKE '%" + k + "%' "
					);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				phoneList.add(new PersonVo(rs.getInt("I"), rs.getString("N"), rs.getString("H"), rs.getString("C")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return phoneList;
	}
	
	public int update(PersonVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" UPDATE "
					+ " 	person "
					+ " SET "
					+ " 	name = ?, "
					+ " 	hp = ?, "
					+ " 	company = ? "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			pstmt.setInt(4, p.getPersonID());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
	public int delete(int no) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 		person "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setInt(1, no);
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
	public PersonVo personInfo(int i) {
		PersonVo p = null;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id, "
					+ " 	name, "
					+ " 	hp, "
					+ " 	company "
					+ " FROM "
					+ " 	person "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setInt(1, i);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				p = new PersonVo(rs.getInt("person_id"), rs.getString("name"), rs.getString("hp"), rs.getString("company"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return p;
	}

}
