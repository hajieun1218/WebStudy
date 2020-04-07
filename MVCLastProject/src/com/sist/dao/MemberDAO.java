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
}
