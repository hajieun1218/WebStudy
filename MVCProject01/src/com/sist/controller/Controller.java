package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
// Controller?cmd=list
// Controller?cmd=detail
// Controller?cmd=insert

@WebServlet("*.do")
// list.do  detail.do  insert.do
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청받기
//		String cmd=request.getParameter("cmd");
		String uri=request.getRequestURI();
		/*
		 *   http://localhost/MVCProject1/list.do
		 *   uri => /MVCProject1/list.do
		 *   getContextPath() => /MVCProject1
		 */
		
		// 처리 -> Model
		String cmd=uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		System.out.println(cmd);
		String jsp=uri.substring(request.getContextPath().length(), uri.lastIndexOf("."));
		System.out.println(jsp);
		jsp=jsp+".jsp";
		System.out.println(jsp);
		
		/*
		 *    map.put("list", new ListModel())
		 *    map.put("insert", new InsertModel())
		 *    map.put("update", new UpdateModel())
		 */
		// 이렇게 하면 if문이 너무 많아짐 ===> 어노테이션 사용!!!!(자동호출이 가능)
		if(cmd.equals("list")) {
			ListModel model=new ListModel();
			model.execute(request);
//			jsp="member/list.jsp";
		}
		else if(cmd.equals("detail")) {
			DetailModel model=new DetailModel();
			model.execute(request);
//			jsp="member/detail.jsp";
		}
		else if(cmd.equals("insert")) {
			InsertModel model=new InsertModel();
			model.execute(request);
//			jsp="member/insert.jsp";
		}
		else if(cmd.equals("update")) {
			UpdateModel model=new UpdateModel();
			model.execute(request);
//			jsp="board/update.jsp";
		}
		
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}
