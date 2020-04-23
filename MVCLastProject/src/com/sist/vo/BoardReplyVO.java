package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
/*
	NO         NOT NULL NUMBER       
	BNO                 NUMBER       
	ID                  VARCHAR2(20) 
	NAME       NOT NULL VARCHAR2(32) 
	MSG        NOT NULL CLOB         
	REGDATE             DATE         
	GROUP_ID            NUMBER       
	GROUP_STEP          NUMBER       
	GROUP_TAB           NUMBER       
	ROOT                NUMBER       
	DEPTH               NUMBER 
 */

@Getter 
@Setter
public class BoardReplyVO {
	private int no;
	private int bno;
	private String id;
	private String name;
	private String msg;
	private Date regdate;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	// 시간을 가져올때는 오라클에서 TO_CHAR() 해서 받아야함 , 자바에서 변경하면 다 12시
	private String dbday;
}
