package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id;
	private String name;
	private String msg; // 로그인 되었는지 확인하기 위해
	private int admin;
	private String pwd;
}
