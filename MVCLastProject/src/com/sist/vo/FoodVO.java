package com.sist.vo;
/*
NO      NOT NULL NUMBER         
CNO              NUMBER         
TITLE   NOT NULL VARCHAR2(200)  
SCORE   NOT NULL NUMBER(2,1)    
ADDRESS NOT NULL VARCHAR2(200)  
TEL     NOT NULL VARCHAR2(30)   
TYPE    NOT NULL VARCHAR2(100)  
PRICE            VARCHAR2(100)  
IMAGE   NOT NULL VARCHAR2(2000) 
GOOD             NUMBER         
SOSO             NUMBER         
BAD              NUMBER         
TAG              VARCHAR2(2000)
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int no;
	private int cno;
	private String title,address,tel,type,price,image;
	private double score;
	private int good,soso,bad;
	private String tag;
}
