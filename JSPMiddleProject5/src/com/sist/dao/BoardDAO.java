package com.sist.dao;
import java.util.*;
import java.io.*; // XML을 읽기 위해

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
 * 		자바
 * 		  클래스의 구성요소
 * 			1) 멤버변수
 * 				= 인스턴스 : new를 이용해서 메모리 할당 -> 생성
 * 				= 정적변수 (static) : JVM이 클래스를 읽기 시작 -> 생성
 * 			2) 메소드
 * 			3) 생성자
 * 		
 * 		=============================
 * 			필요시에 멤버변수에 대한 초기화
 * 			= 명시적 방법
 * 				public class A {
 * 					int a=10
 * 					static int b=20  ==> 선언만 가능 (메소드호출,제어문,연산자 사용이 불가능)
 * 					====================> 외부나 메소드를 이용해서 초기화를 할 수 없다
 * 				}
 			= 생성자를 이용하는 방식 : 외부에 파일 읽기, 네트워크 연결, DB연결, XML파싱
 			= 초기화 블록 이용
 			
 			
 			순서 : 명시적 방법 => 초기화 블록 => 생성자
 						   ========
 						      인스턴스 블록 => 변수가 인스턴스 일 경우
 						      public class A {
 						      	int a;
 						      	{
 						      		a=getNumber();
 						      	}
 						      	=========> 인스턴스  => 생성자 사용하는 것이 더 편함
 						      }	
 						      
 						   static 블록 => static일 경우에 사용
 						   	  public class A {
 						   	  	static int b;
 						   	  	static {
 						   	  		b=getNumber();
 						   	  	}
 						   	  }
 */
public class BoardDAO {
	private static SqlSessionFactory ssf; // 파싱을 해주는 역할
	// 시작하자마자 XML을 읽고 시작해야함
	static {
		// 파싱 => getConnection(), disConnection()
		// id, sql문장을 Map에 저장
		// id를 입력하고 sql문장을 실행한 결과를 달라
		try {
			// XML 파일 읽기
			// Spring,Mybatis ==> classpath:src(src는 자동 인식/ 패키지에 넣으면 인식 안됨)
			Reader reader=Resources.getResourceAsReader("Config.xml");
			
			// 파싱
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// 목록읽기
	public static List<BoardVO> boardListData(Map map) {
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=ssf.openSession();
		list=session.selectList("boardListData",map);  // (id,parameterType)
		// connection 반환
		session.close(); // ps,conn 반환(disConnection())
		return list;
	}
	
	// 상세보기
	public static BoardVO boardDetailData(int no) {
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession();
		session.update("hitIncrement",no);
		
		// auto commit이 아니므로 반드시 commit 해줘야한다
		// select는 commit을 하지 않으므로 openSession(true) 사용하지 않고 session.commit() 사용
		session.commit(); 
		
		// 값을 받아올 때 자동으로 형변환 하여 vo에 넣어준다
		vo=session.selectOne("boardDetailData",no);
		
		// connection 반환
		session.close();
		return vo;
	}
	
	// 수정하기 (값 가져오기)
	public static BoardVO boardUpdateData(int no) {
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession();
		
		// 값을 받아올 때 자동으로 형변환 하여 vo에 넣어준다
		vo=session.selectOne("boardDetailData",no);
		
		// connection 반환
		session.close();
		return vo;
	}
	// 총페이지
	public static int boardTotalPage() {
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		// connection 반환
		session.close();
		return total;
	}
	
	// 데이터 추가
	public static void boardInsert(BoardVO vo) {
		SqlSession session=ssf.openSession(true); // setAutoCommit(true)
		session.insert("boardInsert",vo);
		session.close();
	}
	
	// 수정
	public static void boardUpdate(BoardVO vo) {
		SqlSession session=ssf.openSession(true);
		session.update("boardUpdate",vo);
		session.close();
	}
}
