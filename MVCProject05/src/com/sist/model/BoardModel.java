package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

// POJO
@Controller
public class BoardModel {
	// list.jsp
	@RequestMapping("board/list.do")
	public String boardListData(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);

		List<BoardVO> list = BoardDAO.boardListData(map);
		int totalpage = BoardDAO.boardTotalPage();

		/*
		 * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); Date
		 * date=new Date(); String today=sdf.format(date);
		 */
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		// JSP로 결과값 전송
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("today", today);
		
		return "list.jsp";
	}

	// datail.jsp
	@RequestMapping("board/detail.do")
	public String boardDetailData(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));

		// datail.jsp로 전송
		request.setAttribute("vo", vo);
		
		return "detail.jsp";
	}

	// update.jsp
	@RequestMapping("board/update.do")
	public void boardUpdateData(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));

		// datail.jsp로 전송
		request.setAttribute("vo", vo);
	}

	// insert_ok.jsp
	// 화면 이동 해야하기 때문에 response 필요
	@RequestMapping("board/insert_ok.do")
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 한글변환
			request.setCharacterEncoding("UTF-8");

			// 여기는 setProperty 존재 안하므로 하나씩 받아야 한다(setProperty는 jsp에만 존재)
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");

			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			BoardDAO.boardInsert(vo);

			// 이동
//			response.sendRedirect("list.jsp");
		} catch (Exception ex) {
		}
	}

	// update_ok.jsp
	@RequestMapping("board/update_ok.do")
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");

			BoardVO vo = new BoardVO();
			vo.setNo(Integer.parseInt(no));
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			BoardDAO.boardUpdate(vo);

			// 이동
//			response.sendRedirect("detail.jsp?no=" + no);
		} catch (Exception ex) {
		}

	}
}
