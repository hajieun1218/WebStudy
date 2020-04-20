package com.sist.dao;
/*
 *  TNO     NUMBER       
	TIME    VARCHAR2(20) 
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveTimeVO {
	private int tno;
	private String time;
}
