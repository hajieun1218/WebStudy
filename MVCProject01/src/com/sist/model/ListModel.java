package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/*
 * 	   	Controller => 요청을 받는다, 처리 결과값을 JSP로 전송
 * 		Model      => 처리
 * 		View       => 결과값 출력
 *            request               request         request                request    매개변수를 이용해 request를 넘겨줌
 * 		사용자 ========== Controller =========> Model =========> Controller =========> JSP
 * 			             => ListModel model=new ListModel();
 * 							model.execute(request);
 */
public class ListModel {
	public void execute(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		
		request.setAttribute("list", list);
		// Controller 전송 => JSP로 전송
		
	}
}
