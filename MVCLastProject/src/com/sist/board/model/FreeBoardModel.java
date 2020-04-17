package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;

@Controller
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		
		// page
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		FreeBoardDAO dao=new FreeBoardDAO();
		List<BoardVO> list=dao.freeboradListData(curpage);
		int totalpage=dao.freeboarTotalPage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // 오늘작성한 글 뒤에 new하기 위해
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		
		// 사용자가 보낸 데이터를 받는다
		// 매개변수가 3개 이상이면 클래스로 묶어서 전송 (VO)
		// 구조체(여러변수를 묶어준다)-C언어 ==> 클래스-자바
		try {
			request.setCharacterEncoding("UTF-8"); // 디코딩 (송신:인코딩, 수신:디코딩)
		} catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO로 전송 => DAO에서 오라클로 보내준다
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.freeBoardInsert(vo);
		
		return "redirect:../freeboard/list.do";
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {
		
		String no=request.getParameter("no");
		
		FreeBoardDAO dao=new FreeBoardDAO();
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 1); // type=1 상세보기, type=2 수정하기
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse response) {
		
		String no=request.getParameter("no");
		
		FreeBoardDAO dao=new FreeBoardDAO();
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 2); // type=1 상세보기, type=2 수정하기
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
}
