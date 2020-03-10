package com.sist.dao;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

// 전체가 필요하면 클래스 위에, 하나만 필요하면 변수 위에
@Getter
@Setter
public class BoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
}
