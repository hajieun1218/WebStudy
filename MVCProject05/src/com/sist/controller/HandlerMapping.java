package com.sist.controller;
import java.util.*;
import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/*
		DispatcherServlet 부터 시작 (서블릿이 메인)
						    
						    XML(Path)
		DispatcherServlet ------------> HandlerMapping <---------------> XMLParser
														List<String>:package명
 */

public class HandlerMapping {
	private List<String> list=new ArrayList<String>();

	public HandlerMapping(String path, String defaultPath) {
		try {
			SAXParserFactory spf=SAXParserFactory.newInstance();
			// SAX 파서기
			SAXParser sp=spf.newSAXParser();
			XMLParser xp=new XMLParser();
			sp.parse(new File(path), xp);
			List<String> pList=xp.getList();  // 패키지 명을 받음
			
			ComponentScan cs=new ComponentScan();
			for(String pack:pList) {
				List<String> fNames=cs.getClassNameRead(pack, defaultPath);
				for(String name:fNames) {
					list.add(name);  // => 이 list를 DispatcherServlet에서 읽어감
				}
			}
		} catch(Exception ex) {}
	}
	
	public List<String> getList() {
		return list;
	}
	
}
