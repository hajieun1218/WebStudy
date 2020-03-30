package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class InsertOkModel implements Model{
	
	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			// 한글변환
			request.setCharacterEncoding("UTF-8");
			
			// 여기는 setProperty 존재 안하므로 하나씩 받아야 한다(setProperty는 jsp에만 존재)
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			BoardDAO.boardInsert(vo);
			
		} catch(Exception ex) {}
		
		return "redirect:list.do";
	}
}