package com.sist.dao;
/*
 *  TNO     NUMBER        
	TIME    VARCHAR2(100) 
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveDateVO {
	private int tno;
	private String time;
}
