package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
import com.sist.temp.Controller;

import javax.xml.parsers.*;

import org.apache.catalina.mbeans.ClassNameMBean;
import org.w3c.dom.*;

/*
 							// Model      // DAO       // 일반클래스    // Manager
 		@ => TYPE : 클래스 구분 (@Controller, @Repository, @Component, @Service)
 		public class A {
			@ => FIELD : 메모리 주소 전송 (@Autowired) - 메모리 주소를 자동으로 찾고싶을 때, 자동메모리 할당
			private B b;
			
			public void setB(@ B b) { => PARAMETER : 값을 채워주거나 값을 가져올 때, 매개변수 뭐가 있는지 확인(@Resource)
				this.b=b;
			}
			@ => CONSTRUCTOR
			public A() {
			}
			@ => METHOD (@RequestMapping)
			public void display() {
			}
		}
		
		클래스는 메모리 할당할지 말건지만 구분하고
		메소드는 어떤걸 호출할지 정해줘야함 => 문자열 (@RequestMapping(""))
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();

	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation"); // 설정파일읽기
		System.out.println(path);
		try {
			// xml 데이터 읽을때
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			Element beans=doc.getDocumentElement();
			System.out.println(beans.getTagName());
			NodeList list=beans.getElementsByTagName("bean");
			
			for(int i=0;i<list.getLength();i++) {
				Element bean=(Element)list.item(i);
				System.out.println(bean.getTagName());
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance(); // 객체 생성,메모리 할당
				
//				Controller con=(Controller)clsName.getAnnotation(Controller.class);
				
				// 클래스 위에 @Controller가 없으면 메모리 할당 하지마라
				if(clsName.isAnnotationPresent(Controller.class)==false) 
					continue;
				
				System.out.println("id="+id);
				System.out.println("model="+obj);
				
				clsMap.put(id, obj);
			}
		} catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 요청
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf("."));
		
		// 사용자 요청 처리 => Model
		Model model=(Model)clsMap.get(cmd);
		
		// 요청처리
		String jsp=model.handlerRequest(request);
		
		// 화면 이동(sendRedirect) | Request전송(forward)
		if(jsp.startsWith("redirect")) {
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}

}
