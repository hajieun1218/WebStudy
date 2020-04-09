package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

// 게시판 전체를 하나의 VO로 사용
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
	private String filename;
	private String filesize;
	private int type; // 어떤 게시판인지 확인하기 위해
}
