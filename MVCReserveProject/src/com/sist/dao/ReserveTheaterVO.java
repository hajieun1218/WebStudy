package com.sist.dao;
/*
 *  TNO      NUMBER        
	TNAME    VARCHAR2(100) 
	TLOC     VARCHAR2(100) 
	TDATE    VARCHAR2(100) 
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveTheaterVO {
	private int tno;
	private String tname;
	private String tloc;
	private String tdate;
}
