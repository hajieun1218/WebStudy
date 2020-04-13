package com.sist.board.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class ReplyBoardModel {
	
	@RequestMapping("reply/list.do")
	public String reply_list(HttpServletRequest request, HttpServletResponse response) {
		
		// 요청한 데이터 가져오기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=15;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		// DAO
		List<BoardVO> list=ReplyBoardDAO.replyListData(map);
		int totalpage=ReplyBoardDAO.replyTotalPage();
		
		/*
		 *		class Model{
					HttpServletRequest request;
					public Model(HttpServletRequest request) {
						this.request=request;
					}
					public void addAttribute(String key,Object obj) {
						request.setAttribute(key,obj);
					}
				}
				
				Model model=new Model(request);
				model.addAttribute("list",list);
				
									 <Spring>
				DispatcherServlet -> Front Controller
				Model             -> Controller
				View              -> JSP
				request           -> Model
		 */
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("main_jsp", "../reply/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/detail.do")
	public String reply_detail(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		
		ReplyBoardDAO.replyHitIncrement(Integer.parseInt(no));
		BoardVO vo=ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../reply/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/insert_ok.do")
	public String reply_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
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
		
		ReplyBoardDAO.replyInsert(vo);
		
		return "redirect:../reply/list.do";
	}
	
	@RequestMapping("reply/update.do")
	public String reply_update(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		BoardVO vo=ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/password_check.do")
	public String reply_password_check(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		String db_pwd=ReplyBoardDAO.replyGetPwd(Integer.parseInt(no));
		int res=0;
		if(db_pwd.equals(pwd)) 
			res=1;
		else 
			res=0;
		
		request.setAttribute("result", res);
		return "../reply/password_check.jsp";
	}
	
	@RequestMapping("reply/update_ok.do")
	public String reply_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO.replyUpdate(vo);
			
		return "redirect:../reply/detail.do?no="+no;
	}
	
	@RequestMapping("reply/reply.do")
	public String reply_reply(HttpServletRequest request, HttpServletResponse response) {
		String pno=request.getParameter("no");
		
		request.setAttribute("pno", pno); // 상위번호 parent no
		request.setAttribute("main_jsp", "../reply/reply.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/reply_ok.do")
	public String reply_reply_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String pno=request.getParameter("pno");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO 연결
		ReplyBoardDAO.replyReplyInsert(Integer.parseInt(pno), vo);
		
		//reply_list(request, response); // request가 계속 바뀌기 때문에 직접 호출 X
		return "redirect:../reply/list.do"; // .do : 메소드 재호출
	}
	
	@RequestMapping("reply/delete.do")
	public String reply_delete(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		
		request.setAttribute("no", no);
		request.setAttribute("main_jsp", "../reply/delete.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/delete_ok.do")
	public String reply_delete_ok(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		// DAO
		boolean bCheck=ReplyBoardDAO.replyDelete(Integer.parseInt(no), pwd);
		
		request.setAttribute("bCheck", bCheck);
		return "../reply/delete_ok.jsp";
	}
}
