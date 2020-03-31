package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;

public class MiddleModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int cno=Integer.parseInt(request.getParameter("cno"));
		List<FoodVO> list=FoodDAO.middleListData(cno);
		request.setAttribute("list", list);
		return "food/middle.jsp";
	}

}
