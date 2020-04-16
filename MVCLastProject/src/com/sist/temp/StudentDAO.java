package com.sist.temp;
import java.util.*;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.*;

public class StudentDAO {
	private Connection conn;
	private CallableStatement cs; // Procedure를 사용할 때
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch(Exception ex) {}
	}
	
	public void disConnection() {
		try {
			if(cs!=null)
				cs.close();
			if(conn!=null)
				conn.close();
		} catch (Exception ex) {}
	}
	
	public StudentVO studentInfoData(int no) {
		StudentVO vo=new StudentVO();
		try {
			getConnection();
			String sql="{CALL pro_test_select(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no); // 첨부하는 변수
			// 데이터를 받을 변수 설정
			cs.registerOutParameter(2, OracleTypes.VARCHAR); // 언어가 다른언어일 수 있기때문에 VARCHAR (VARCHAR2는 오라클에서만 사용)
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
			// OracleTypes.CURSOR => ResultSet으로 받는다
			
			// 실행 => 프로시저는 모든 SQL  executeUpdate()
			// select문장이라고 executeQuery 아님. 무조건 executeUpdate();
			cs.executeUpdate(); 
			
			String name=cs.getString(2);
			int kor=cs.getInt(3);
			int eng=cs.getInt(4);
			int math=cs.getInt(5);
			
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMath(math);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
}

