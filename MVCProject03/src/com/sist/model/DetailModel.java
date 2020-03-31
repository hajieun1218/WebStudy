package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;

public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int no=Integer.parseInt(request.getParameter("no"));
		FoodVO vo=FoodDAO.detailData(no);
		request.setAttribute("vo", vo);
		return "food/detail.jsp";
	}

}
