package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<RecipeVO> recipeListData(Map map) {
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("recipeListData", map);
		} catch(Exception ex) {
			System.out.println("recipeListData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int recipeTotalPage() {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("recipeTotalPage");
		} catch(Exception ex) {
			System.out.println("recipeTotalPage(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	
	
	public static List<ChefVO> chefListData(Map map) {
		List<ChefVO> list=new ArrayList<ChefVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("chefListData", map);
		} catch(Exception ex) {
			System.out.println("chefListData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int chefTotalPage() {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("chefTotalPage");
		} catch(Exception ex) {
			System.out.println("chefTotalPage(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static RecipeDetailVO recipeDetailData(int no) {
		RecipeDetailVO vo=new RecipeDetailVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("recipeDetailData",no);
		} catch(Exception ex) {
			System.out.println("recipeDetailData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	public static int recipeCount(int no) {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("recipeCount2",no);
		} catch(Exception ex) {
			System.out.println("recipeCount2(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<RecipeVO> chefDetailData(Map map) {
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("chefDetailData", map);
		} catch(Exception ex) {
			System.out.println("chefDetailData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int chefDataTotalPage(String chef) {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("chefDataTotalPage", chef);
		} catch(Exception ex) {
			System.out.println("chefDataTotalPage(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<RecipeVO> recipeFindData(String fd) {
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			Map map=new HashMap();
			map.put("fd", fd);
			list=session.selectList("recipeFindData", map);
		} catch(Exception ex) {
			System.out.println("recipeFindData(): "+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
}
