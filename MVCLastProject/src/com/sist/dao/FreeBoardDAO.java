package com.sist.dao;
import java.util.*;

import com.sist.vo.BoardVO;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class FreeBoardDAO {
	private Connection conn;
	private CallableStatement cs; // PROCEDURE 호출시 전송 객체
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FreeBoardDAO() {
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// 연결
	// SqlSession session=ssf.openSession();
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch(Exception ex) {}
	}
	
	// 해제
	// session.close();
	public void disConnection() {
		try {
			if(cs!=null)
				cs.close();
			if(conn!=null)
				conn.close();
		} catch(Exception ex) {}
	}
	
	
	/*
	  	CREATE OR REPLACE PROCEDURE boardListData(
		    pStart NUMBER,
		    pEnd NUMBER,
		    pResult OUT SYS_REFCURSOR -- 목록을 통째로 받아올 수 있다
	    )
	 */
	// session.selectList("");
	// 게시판 목록
	public List<BoardVO> freeboradListData(int page) {
		List<BoardVO> list=new ArrayList<BoardVO>();
		try {
			getConnection();
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			String sql="{CALL boardListData(?,?,?)}"; // 함수 호출할 때는 {}안에
			cs=conn.prepareCall(sql); // 프로시저 호출
			
			// ?에 값을 채운다
			cs.setInt(1, start);
			cs.setInt(2, end);
			cs.registerOutParameter(3, OracleTypes.CURSOR); // 저장공간을 만들어준다, OUT변수는 임시저장장소(registerOutParameter)에 저장
			
			// 실행요청
			cs.executeUpdate(); // boardListData(?,?,?) 함수를 호출해라. 무조건 executeUpdate()
			
			// 결과값 받기
			// 자바에는 CURSOR가 없으니까 Object로 받고 ResultSet으로 형변환 한다
			ResultSet rs=(ResultSet)cs.getObject(3); // CURSOR ==> ResultSet으로 받아야한다
			
			//  SELECT no,subject,name,regdate,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
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
	
	
	/*
	    CREATE OR REPLACE PROCEDURE boardTotalPage(
		    pTotal OUT NUMBER
		)
	 */
	// 총페이지
	public int freeboarTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="{CALL boardTotalPage(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER); // 정수를 저장할 수 있는 공간(메모리)을 만들어라
			
			// 실행
			cs.executeUpdate();
			
			// 메모리에서 결과값 읽기
			total=cs.getInt(1);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
	
	
	/*
		CREATE OR REPLACE PROCEDURE boardInsert(
		    pName board.name%TYPE,
		    pSubject board.subject%TYPE,
		    pContent board.content%TYPE,
		    pPwd board.pwd%TYPE
		)
	 */
	// 게시판 데이터 추가 insert
	public void freeBoardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="{CALL boardInsert(?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
			
			cs.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	
	/*
		CREATE OR REPLACE PROCEDURE boardInfoData(
		    pno board.no%TYPE,
		    pType NUMBER, 
		    pResult OUT SYS_REFCURSOR 
		)
	 */
	// 상세보기 detail, 수정하기 update 데이터 가져오기
	public BoardVO freeboardInfoData(int no, int type) {
		BoardVO vo=new BoardVO();
		try {
			getConnection();
			String sql="{CALL boardInfoData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setInt(2, type);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.executeUpdate();
			
			ResultSet rs=(ResultSet)cs.getObject(3);
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	
}
