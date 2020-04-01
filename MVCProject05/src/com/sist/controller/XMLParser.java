package com.sist.controller;
/*
 * 		동작 순서
 *     1) applicationContext.xml 읽기
 *     		package 읽어서 가져오는게 XMLParser
 *     2) ComponentScan -> Class.forName(class명)
 *     3) 클래스 관리자 -> HandlerMapping (메모리 할당된 클래스를 가지고 있다)
 *     4) DispatcherServlet <-> HandlerMapping 
 *     5) ViewResolver : JSP 찾기
 *     6) Model을 통해서 데이터를 JSP로 전송
 *     7) forward|sendRedirect
 */

/*
 * 		SAX => 읽기 전용
 * 			=> 한줄씩 읽기
 * 
 * 		<?xml version="1.0" ?> => startDocument
 * 		<book>                 => startElement
 * 			<list>             => startElement
 * 				<author>       => startElement
 * 					sss        => characters
 * 				</author>      => endElement
 * 				<title>        => startElement
 * 					aaaa       => characters
 * 				</title>       => endElement
 * 			</list>            => endElement
 * 		</book>                => endElement
 * 							   => endDocumnet
 */
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler{
	private List<String> list=new ArrayList<String>();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("context:component-scan")) {  // qName : 태그 이름
				String pack=attributes.getValue("base-package");  // attribues : 속성명
				System.out.println(pack);
				list.add(pack);
			}
		} catch(Exception ex) {}
	}
	
	public List<String> getList() {
		return list;
	}
	
}
