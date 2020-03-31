package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class ReserveModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "영화예매");
		request.setAttribute("main_jsp", "../movie/reserve.jsp");
		return "../main/main.jsp";
	}

}
