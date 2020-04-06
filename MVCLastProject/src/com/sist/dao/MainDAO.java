package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class MainDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<CategoryVO> mainImageData() {
		// Mybatis 에러 잡기 어렵기 때문에 try~catch문 사용
		SqlSession session=null;
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			session=ssf.openSession(); // getConnection()
			list=session.selectList("mainImageData"); // try안에 있던 코드들(PrepareStatement, Resultset, ...)
		} catch(Exception ex) {
			System.out.println("mainImageData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int recipeCount() {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("recipeCount");
		} catch(Exception ex) {
			System.out.println("recipeCount(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	
	public static List<RecipeVO> mainRecipeData() {
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("mainRecipeData");
		} catch(Exception ex) {
			System.out.println("mainRecipeData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static FoodVO mainFoodDetailData(int no) {
		FoodVO vo=new FoodVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("mainFoodDetailData", no);
		} catch(Exception ex) {
			System.out.println("mainFoodDetailData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
