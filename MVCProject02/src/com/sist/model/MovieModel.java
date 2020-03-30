package com.sist.model;

import java.util.*;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;

public class MovieModel implements Model {
	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		/*String strpage=request.getParameter("page");
		int page=Integer.parseInt(strpage);
		
		int rowSize=9;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MovieBean> list=BoardDAO.movieListData(map);*/
		
		
		String page=request.getParameter("page");
		if(page==null) 
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=9;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		MovieDAO dao=new MovieDAO();
		List<MovieBean> list=dao.movieListData(curpage);
		int totalpage=dao.movieTotalPage();
		
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		
		return "board/movie.jsp";
	}
}
