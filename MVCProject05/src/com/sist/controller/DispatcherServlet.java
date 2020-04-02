package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> list=new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		// web.xml에 있는 path 읽기
		String path=config.getInitParameter("contextConfigLocation");
		String defaultPath=config.getInitParameter("defaultPath");
		// HandlerMapping 으로 넘겨줌
		HandlerMapping hm=new HandlerMapping(path, defaultPath);
		list=hm.getList(); // list 안에는 com.sist.model.BoardModel, ... 이 있음
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI(); // /MVCProject05/main/main.do
		cmd=cmd.substring(request.getContextPath().length()+1); // /main/main.do
		try {  // 메모리 할당
			for(String cls:list) {
				Class clsName=Class.forName(cls);
				// 인터페이스, 추상클래스는 메모리 할당이 안되기 때문에 오류가 날 수 있다, VO는 메모리 할당 하지 않는다
				// => @Controller 어노테이션이 없으면 메모리 할당 제외
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;  // 제외
				Object obj=clsName.newInstance();
				
				// 메소드를 찾아서 호출
				Method[] methods=clsName.getDeclaredMethods(); // 클래스에 저장되어 있는 모든 메소드를 가져옴
				
				for(Method m:methods) {
					RequestMapping rm=m.getAnnotation(RequestMapping.class); // 
					if(cmd.equals(rm.value())) { // rm.value()가 null일 수 있으니까 cmd를 앞에 써야한다
						String jsp=(String)m.invoke(obj, request,response);  // 메소드 호출
						// return "redirect:list.jsp"
						// return "list.jsp"
						if(jsp.startsWith("redirect")) {
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else {
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;  
					}
				}
			}
		} catch(Exception ex) {}
	}

}
