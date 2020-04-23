package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardReplyVO;

public class FreeBoardReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<BoardReplyVO> replyListData(Map map) {
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyListData2", map); // 프로시저 호출
			list=(ArrayList<BoardReplyVO>)map.get("pResult"); // CURSOR
			// mybatis CURSOR => list로 받아야한다 , ResultSet(X)
			
			/*ResultSet rs=(ResultSet)map.get("pResult"); 
			// SELECT no,bno,id,name,msg,regdate,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,group_tab
			while(rs.next()) {
				BoardReplyVO vo=new BoardReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setBno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setRegdate(rs.getDate(6));
				vo.setDbday(rs.getString(7));
				vo.setGroup_tab(rs.getInt(8));
				list.add(vo);
			}
			rs.close();*/
		} catch(Exception ex) {
			System.out.println("replyListData2(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void replyInsert(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyInsert2", map); // 프로시저 호출
		} catch(Exception ex) {
			System.out.println("replyInsert2(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// 매개변수가 클래스 => 값변경,추가가 가능하다 (주소에 의한 전달 Call By Reference)
	public static int replyTotalPage(Map map) {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("replyTotalPage2", map); // 프로시저 호출
			total=(int)map.get("pTotal");
		} catch(Exception ex) {
			System.out.println("replyTotalPage2(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
}
