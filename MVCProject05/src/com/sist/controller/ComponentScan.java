package com.sist.controller;

import java.io.*;
import java.util.*;

// 패키지에 있는 클래스를 가져옴
public class ComponentScan {
	// controller를 jar파일 만들어서 다른 프로젝트에서 쓸때는 path를  web.xml에 지정한 defaultPath에서 가져온다
	public List<String> getClassNameRead(String pack, String path) {
		List<String> list=new ArrayList<String>();
		try {
//			String path="C:\\Users\\sist186\\git\\WebStudy\\MVCProject05\\src"; // 모든 조원이 같은 path를 가지고있어야한다
			path=path+"\\"+pack.replace(".","\\"); // com.sist.model => com\\sist\\model
			File dir=new File(path); // 디렉토리 읽기
			File[] files=dir.listFiles(); // 폴더의 파일 가져오기
			for(File f:files) {
				String name=f.getName();
				if(name.endsWith("java")) {
					String s=pack+"."+name.substring(0, name.indexOf("."));
					// name="BoardModel.java"
					// s="com.sist.model"+"."+"BoardModel"
					// ===> com.sist.model.BoardModel
					list.add(s);
				}
			}
		} catch(Exception ex) {}
		return list;
	}
}
