package com.sist.temp;
import java.util.*;
import java.io.*;
// 패키지만 넘기면 패키지에 있는 클래스를 applicationContext.xml에 자동으로 등록
public class MainClass {
	public static void main(String[] args) {
		componentScan("com.sist.model");
	}
	
	// 패키지단위로 메모리할당 =>  componentScan(Spring)
	public static List<String> componentScan(String pack) {
		List<String> list=new ArrayList<String>();
		try {
			String path="C:\\Users\\sist186\\git\\WebStudy\\MVCProject04\\src";
			path=path+"\\"+pack.replace(".", "\\");
			File dir=new File(path);
			File[] files=dir.listFiles();
			for(File f:files) {
//				System.out.println(f.getName());
				
				// 확장자가 .java 가 아닌 파일은 제외
				String ext=f.getName().substring(f.getName().lastIndexOf(".")+1);
				if(!ext.equals("java"))
					continue;
				
				String p=pack+"."+f.getName().substring(0,f.getName().lastIndexOf("."));
				System.out.println(p);
			}
		} catch(Exception ex) {}
		return list;
	}
}
