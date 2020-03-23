package com.sist.xml;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class SawonXML {
	public static void main(String[] args) {
		SawonXML xml=new SawonXML();
		xml.xmlParser();
	}
	
	public void xmlParser() {
		try {
			// 파서기(parser : xml 데이터를 읽어오는 것)
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			
			// 문서 저장 
			// 파일을 읽어서 document에 저장
			Document doc=db.parse(new File("C:\\webDev\\webStudy\\JSPMiddleProject4\\WebContent\\WEB-INF\\sawon.xml"));
			
			// 테이블 읽기
			Element table=doc.getDocumentElement(); // 최상위 태그를 읽는다
			System.out.println(table.getTagName());
			
			// list라는 이름의 태그를 모아줌 => for문 돌리기 위해
			NodeList list=table.getElementsByTagName("list");
			System.out.println(list.getLength());
			
			for(int i=0;i<list.getLength();i++) {
				list=table.getElementsByTagName("name");
				String name=list.item(i).getFirstChild().getNodeValue();
				System.out.println("name="+name);
				
				list=table.getElementsByTagName("addr");
				String addr=list.item(i).getFirstChild().getNodeValue();
				System.out.println("addr="+addr);
				
				list=table.getElementsByTagName("sex");
				String sex=list.item(i).getFirstChild().getNodeValue();
				System.out.println("sex="+sex);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
