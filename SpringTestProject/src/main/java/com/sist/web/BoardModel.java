package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardModel {
	@RequestMapping("main/board.do")
	public String main_board(HttpServletRequest request) {
		request.setAttribute("msg", "게시판");
		return "board";
	}
}
