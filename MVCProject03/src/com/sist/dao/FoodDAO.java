package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class FoodDAO {
	
	private static SqlSessionFactory ssf;
	static {
		try {
			// XML 읽기
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// XML 파싱
			ssf=new SqlSessionFactoryBuilder().build(reader);  // ssf : 파싱한 데이터를 저장하는 공간
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static List<CategoryVO> categoryListData() {
		SqlSession session=ssf.openSession();  // Connection
		List<CategoryVO> list=session.selectList("categoryListData");
		session.close(); // connection 반환
		return list;
	}
	
	public static List<FoodVO> middleListData(int cno) {
		SqlSession session=ssf.openSession();  // Connection
		List<FoodVO> list=session.selectList("middleListData",cno);
		session.close(); // connection 반환
		return list;
	}
	
	public static FoodVO detailData(int no) {
		SqlSession session=ssf.openSession();  // Connection
		FoodVO list=session.selectOne("detailData",no);
		session.close(); // connection 반환
		return list;
	}
}
