package com.KoreaIT.demo.vo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	
	public Rq(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}
}
