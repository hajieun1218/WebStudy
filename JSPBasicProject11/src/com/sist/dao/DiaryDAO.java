package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class DiaryDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public DiaryDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 연결
	public void getConnection(){
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch(Exception ex) {}
	}
	
	// 연결 해제
	public void disConnection(){
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch(Exception ex) {}
	}
	
	// 우편번호 검색
	public List<ZipcodeBean> postfind(String dong) {
		List<ZipcodeBean> list=new ArrayList<ZipcodeBean>();
		try {
			getConnection();
			String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
					  +"FROM zipcode "
					  +"WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ZipcodeBean vo=new ZipcodeBean();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	// ID 중복체크
	public int idCheck(String id) {
		int count=0;
		try {
			getConnection();
			String sql="SELECT COUNT(*) FROM member "
					  +"WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}
	
	// 회원가입 insert
	public void memberInsert(MemberBean vo) {
		try {
			getConnection();
			String sql="INSERT INTO member VALUES("
					  +"?,?,?,?,?,?,?,?,?,?,?,SYSDATE,'n')";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getSex());
			ps.setString(6, vo.getBirthday());
			ps.setString(7, vo.getPost());
			ps.setString(8, vo.getAddr1());
			ps.setString(9, vo.getAddr2());
			ps.setString(10, vo.getTel());
			ps.setString(11, vo.getContent());
			
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	// 로그인
	public String isLogin(String id, String pwd) {
		String result="";
		try {
			getConnection();
			// id 확인
			String sql="SELECT COUNT(*) FROM member "
					  +"WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setNString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0) { // ID가 없는 상태
				result="NOID";
			} 
			else { // ID 존재
				sql="SELECT pwd,name FROM member "
				   +"WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_pwd=rs.getString(1);
				String name=rs.getString(2);
				rs.close();
				
				if(db_pwd.equals(pwd)) { //로그인
					result=name;
				}
				else { // 비밀번호 틀림
					result="NOPWD";
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return result;
	}
}
