package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader); // SAX
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// 영화목록
	public static List<MovieVO> movieListData() {
		List<MovieVO> list=new ArrayList<MovieVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("movieListData");
		} catch(Exception ex) {
			System.out.println("movieListData(): "+ex.getMessage());
		} finally {
			if(session!=null) 
				session.close();
		}
		return list;
	}
	
	// 극장목록
	public static void movieTheaterUpdate(MovieVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("movieTheaterUpdate",vo);
		} catch(Exception ex) {
			System.out.println("movieTheaterUpdate(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// 영화마다 어디극장에서 상영하는지
	public static ReserveTheaterVO movieTheaterData(int tno) {
		ReserveTheaterVO vo=new ReserveTheaterVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("movieTheaterData", tno);
		} catch(Exception ex) {
			System.out.println("movieTheaterData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static void movieDateUpdate(ReserveTheaterVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("movieDateUpdate",vo);
		} catch(Exception ex) {
			System.out.println("movieDateUpdate(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static void movieTimeUpdate(ReserveDateVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("movieTimeUpdate",vo);
		} catch(Exception ex) {
			System.out.println("movieTimeUpdate(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static String movieTimeData(int tno) {
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			result=session.selectOne("movieTimeData", tno);
		} catch(Exception ex) {
			System.out.println("movieTimeData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return result;
	}
	
	public static String movieTimeData2(int tno) {
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			result=session.selectOne("movieTimeData2", tno);
		} catch(Exception ex) {
			System.out.println("movieTimeData2(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return result;
	}
	
	// 로그인 ----------------------------------------------------------------
	public static MemberVO movieLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("movieIdCount", id);
			if(count==0) {
				vo.setMsg("NOID");
			}
			else {
				MemberVO mvo=session.selectOne("movieGetPwd", id);
				if(pwd.equals(mvo.getPwd())) {
					vo.setMsg("OK");
					vo.setId(mvo.getId());
					vo.setAdmin(mvo.getAdmin());
					vo.setName(mvo.getName());
				}
				else {
					vo.setMsg("NOPWD");
				}
			}
		} catch(Exception ex) {
			System.out.println("movieLogin(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	
	
	public static void movieReserveOk(ReserveVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("movieReserveOk", vo);
		} catch(Exception ex) {
			System.out.println("movieReserveOk(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// 일반사용자 admin - 예약한 목록 가져오기
	public static List<ReserveVO> movieMyPage(String id) {
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("movieMyPage", id);
		} catch(Exception ex) {
			System.out.println("movieMyPage(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// admin 마이페이지
	public static List<ReserveVO> movieAdmin() {
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("movieAdmin");
		} catch(Exception ex) {
			System.out.println("movieAdmin(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// 예약 승인
	public static void adminUpdate(int rno) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("adminUpdate", rno);
		} catch(Exception ex) {
			System.out.println("adminUpdate(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// 예약 완료 상세정보
	public static MovieVO reserveResultData(int mno) {
		MovieVO vo=new MovieVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("reserveResultData", mno);
		} catch(Exception ex) {
			System.out.println("reserveResultData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
