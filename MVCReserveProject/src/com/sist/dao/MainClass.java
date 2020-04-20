package com.sist.dao;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=1;i<=24;i++) { // 영화개수만큼
			String res=movieRandomData(15);
			System.out.println(i+":"+res);
			MovieVO vo=new MovieVO();
			vo.setMno(i);
			vo.setTheaterNo(res);
			MovieDAO.movieTheaterUpdate(vo);
		}
	}
	
	// THEATERNO 랜덤으로 넣기
	public static String movieRandomData(int count) {
		String result="";
		int no=(int)(Math.random()*8)+8; // 8~15개 사이
		int[] com=new int[no];
		int rand=0;
		boolean bCheck=false;
		for(int i=0;i<no;i++) { 
			bCheck=true;
			while(bCheck) {
				rand=(int)(Math.random()*count)+1; // 1~15 영화관 수
				bCheck=false;
				for(int j=0;j<i;j++) {
					if(com[j]==rand) { // 중복제거
						bCheck=true;
						break;
					}
				}
			}
			com[i]=rand;
		}
		
		Arrays.sort(com); // 정렬
		
		for(int i=0;i<com.length;i++) {
			result+=com[i]+",";
		}
		result=result.substring(0,result.lastIndexOf(","));
		return result;
	}
}
