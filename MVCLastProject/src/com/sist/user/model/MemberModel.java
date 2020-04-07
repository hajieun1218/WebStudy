package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/join.jsp");  // include
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request, HttpServletResponse response) {
		
		return "../member/postfind.jsp";
	}
	
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String dong=request.getParameter("dong");
		List<ZipcodeVO> list=MemberDAO.postfindData(dong);
		
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		
		return "../member/postfind_result.jsp";
	}
}
