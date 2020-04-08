package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<ZipcodeVO> postfindData(String dong) {
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("postfindData",dong);
		} catch(Exception ex) {
			System.out.println("postfindData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int idcheckData(String id) {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("idcheckData",id);
		} catch(Exception ex) {
			System.out.println("idcheckData(): "+ex.getMessage());
		} finally {
			if(session!=null) 
				session.close();
		}
		return count;
	}
	
	public static void memberInsert(MemberVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true); // autocommit
			session.insert("memberInsert", vo);
		} catch(Exception ex) {
			System.out.println("memberInsert(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static MemberVO memberLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("idCount",id);
			if(count==0) {
				vo.setMsg("NOID");
			}
			else {
				MemberVO mvo=session.selectOne("getPwd",id);
				if(pwd.equals(mvo.getPwd())) {
					vo.setMsg("OK");
					vo.setAdmin(mvo.getAdmin());
					vo.setName(mvo.getName());
				}
				else {
					vo.setMsg("NOPWD");
				}
			}
		} catch(Exception ex) {
			System.out.println("memberLogin(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
