package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;
/*
 * 		프로그램
 * 		1) 공통모듈 : 중복제거
 * 		2) 핵심모듈 ==============> 핵심만 코딩이 가능하게 만든다 (Spring)
 * 		=========================================================> AOP
 */
public class ReplyBoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<BoardVO> replyListData(Map map) {
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession(); //  getConnection
			list=session.selectList("replyListData",map);
		} catch(Exception ex) {
			System.out.println("replyListData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();    // disConnection
			/*
			 *   session => Connection, PreparedStatement
			 *   if(ps!=null) ps.close()
			 *   if(conn!=null) conn.close()
			 */
		}
		return list;
	}
	
	public static int replyTotalPage() {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("replyTotalPage");
		} catch(Exception ex) {
			System.out.println("replyTotalPage(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static void replyHitIncrement(int no) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("replyHitIncrement",no);
		} catch(Exception ex) {
			System.out.println("replyHitIncrement(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static BoardVO replyDetailData(int no) {
		BoardVO vo=new BoardVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("replyDetailData",no);
		} catch(Exception ex) {
			System.out.println("replyDetailData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static void replyInsert(BoardVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("replyInsert",vo);
		} catch(Exception ex) {
			System.out.println("replyInsert(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static String replyGetPwd(int no) {
		String pwd="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			pwd=session.selectOne("replyGetPwd", no);
		} catch(Exception ex) {
			System.out.println("replyGetPwd(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return pwd;
	}
	
	public static void replyUpdate(BoardVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("replyUpdate", vo);
		} catch(Exception ex) {
			System.out.println("replyUpdate(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static void replyReplyInsert(int pno, BoardVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession();
			BoardVO pvo=session.selectOne("replyParentInfoData", pno);
			session.update("replyGroupStepIncrement", pvo);
			
			// replyReplyInsert
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			
			session.insert("replyReplyInsert", vo);
			session.update("replyDepthIncrement", pno);
			
			session.commit();
			
		} catch(Exception ex) {
			System.out.println("replyReplyInsert(): "+ex.getMessage());
			session.rollback(); // mybatis에서의 트랜젝션 프로그램
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static boolean replyDelete(int no, String pwd) {
		boolean bCheck=false;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			String db_pwd=session.selectOne("replyGetPwd", no);
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				BoardVO vo=session.selectOne("replyDeleteInfoData", no);
				if(vo.getDepth()==0) {
					session.delete("replyDelete", no);
				}
				else {
					vo.setSubject("관리자가 삭제한 게시물입니다.");
					vo.setContent("관리자가 삭제한 게시물입니다.");
					vo.setNo(no);
					session.update("replySubjectUpdate", vo);
				}
				// 상위번호(root)의 depth 감소 
				session.update("replyDepthDecrement", vo.getRoot());
			}
			else {
				bCheck=false;
			}
			
			session.commit();
		} catch (Exception ex) {
			System.out.println("replyDelete(): "+ex.getMessage());
			session.rollback();
		} finally {
			if(session!=null)
				session.close();
		}
		return bCheck;
	}
}
